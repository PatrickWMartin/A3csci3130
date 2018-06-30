package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This is the class that dictates what happens when a user wants to update or delete a contact
 * record.
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, numberField, addressField;
    private Spinner businessSpinner, provinceSpinner;
    Contact receivedPersonInfo;
    DatabaseReference dbReference;
    /**
     * This method initializes all the variables used to get information
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        numberField = (EditText) findViewById(R.id.number);
        businessSpinner = (Spinner) findViewById(R.id.spinner);
        addressField = (EditText) findViewById(R.id.address);
        provinceSpinner = (Spinner) findViewById(R.id.province);
        //set up the Spinner Adapters
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.business_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.province_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            numberField.setText(receivedPersonInfo.businessNumber);
            businessSpinner.setSelection(adapter.getPosition(receivedPersonInfo.primaryBusiness));
            addressField.setText(receivedPersonInfo.address);
            provinceSpinner.setSelection(adapter2.getPosition(receivedPersonInfo.province));

        }
    }

    /**
     * This method is what happens when a user presses the update button
     * @param v The activity view
     */
    public void updateContact(View v){
        dbReference = FirebaseDatabase.getInstance().getReference();

        String personID = receivedPersonInfo.getUid();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String number = numberField.getText().toString();
        String business = String.valueOf(businessSpinner.getSelectedItem());
        String address = addressField.getText().toString();
        String province = String.valueOf(provinceSpinner.getSelectedItem());
        String businessNumber = numberField.getText().toString();

        Contact person = new Contact(personID, name, email,number,business,address,province);
        dbReference.child("contacts").child(receivedPersonInfo.getUid()).setValue(person);


        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void eraseContact(View v)
    {
        dbReference = FirebaseDatabase.getInstance().getReference();
        dbReference.child("contacts").child(receivedPersonInfo.getUid()).removeValue();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
