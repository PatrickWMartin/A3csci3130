package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Class that contains what happens when a user wants to create a new contact
 */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField, numberField, addressField;
    private Spinner businessSpinner, provinceSpinner;
    private MyApplicationData appState;

    /**
     * This method initializes all the variables used to get information
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());


        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        numberField = (EditText) findViewById(R.id.number);
        businessSpinner = (Spinner) findViewById(R.id.business);
        addressField = (EditText) findViewById(R.id.address);
        provinceSpinner = (Spinner) findViewById(R.id.province);
        //set up the Spinner Adapters
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.business_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set up the Spinner Adapters
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.province_arrays, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        businessSpinner.setAdapter(adapter);
        provinceSpinner.setAdapter(adapter2);
    }

    /**
     * This is what happens when the user presses the submit info button
     * @param v the view of the activity
     */

    public void submitInfoButton(View v) {

        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String number = numberField.getText().toString();
        String business = String.valueOf(businessSpinner.getSelectedItem());
        String address = addressField.getText().toString();
        String province = String.valueOf(provinceSpinner.getSelectedItem());
        Contact person = new Contact(personID, name, email,number,business,address,province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
