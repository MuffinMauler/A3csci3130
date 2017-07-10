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

public class Business implements Serializable {

    private  String mBusinessID; //ID set by firebase
    private  String mBusinessNumber; //required, 9-digit number
    private  String mName; //required, 2-48 characters
    private  String mPrimaryBusiness; //required,  {Fisher, Distributor, Processor, Fish Monger}
    private  String mAddress; //less than 50 characters
    private  String mLocation; //2 letters representing province/territory

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Business(String ID, String businessNumber, String name,
                    String primaryBusiness, String address, String location){
        mBusinessID         = ID;
        mBusinessNumber     = businessNumber;
        mName               = name;
        mPrimaryBusiness    = primaryBusiness;
        mAddress            = address;
        mLocation           = location;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("businessNumber", mBusinessNumber);
        result.put("name", mName);
        result.put("primaryBusiness", mPrimaryBusiness);
        result.put("address", mAddress);
        result.put("location", mLocation);

        return result;
    }

    //get methods to avoid a pet peeve
    public String getBusinessID () { return mBusinessID; }
    public String getBusinessNumber() { return mBusinessNumber; }
    public String getName() { return mName; }
    public String getPrimaryBusiness () { return mPrimaryBusiness; }
    public String getAddress () { return mAddress; }
    public String getLocation () { return mLocation; }

}
