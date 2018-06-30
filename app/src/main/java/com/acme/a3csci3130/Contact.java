package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {


    public String uid;
    public String name;
    public String email;
    public String businessNumber;
    public String primaryBusiness;
    public String address;
    public String province;
    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }
    /**
     * The Contact constructor
     * @param uid This is the id in the database
     * @param name This is the name of the contact
     * @param email this is the email of the contact
     * @param businessNumber this is the number of the the contact
     * @param primaryBusiness this is the primary business the contact is in
     * @param address this is the address of the contact
     * @param province this is the province the contact is in
     */
    public Contact(String uid, String name, String email, String businessNumber,String primaryBusiness, String address, String province){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.businessNumber = businessNumber;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    /**
     *  This is the getter for the uid it's needed when you need to get the id
     * @return this is the unique id in the data base
     */
    public String getUid() {
        return uid;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("email", email);
        result.put("number", businessNumber);
        result.put("business",primaryBusiness);
        result.put("address",address);
        result.put("province", province);
        return result;
    }
}
