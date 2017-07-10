package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 */

public class Business implements Serializable {

    public  String businessID; //ID set by firebase
    public  String businessNumber; //required, 9-digit number
    public  String name; //required, 2-48 characters
    public  String primaryBusiness; //required,  {Fisher, Distributor, Processor, Fish Monger}
    public  String address; //less than 50 characters
    public  String location; //2 letters representing province/territory

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Business(String ID, String businessNumber, String name,
                    String primaryBusiness, String address, String location){
        businessID = ID;
        this.businessNumber = businessNumber;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.location = location;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("number", businessNumber);
        result.put("name", name);
        result.put("primary", primaryBusiness);
        result.put("address", address);
        result.put("location", location);

        return result;
    }

}
