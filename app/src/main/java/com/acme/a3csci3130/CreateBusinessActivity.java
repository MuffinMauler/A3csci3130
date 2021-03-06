package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

import static java.util.Arrays.sort;

public class CreateBusinessActivity extends Activity {

    private EditText mNumField, mNameField, mPrimaryField, mAddressField, mLocationField;
    private MyApplicationData mAppState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_activity);
        //Get the app wide shared variables
        mAppState = ((MyApplicationData) getApplicationContext());

        mNumField       = (EditText) findViewById(R.id.num);
        mNameField      = (EditText) findViewById(R.id.name);
        mPrimaryField   = (EditText) findViewById(R.id.primary);
        mAddressField   = (EditText) findViewById(R.id.address);
        mLocationField  = (EditText) findViewById(R.id.location);
    }

    /**
     * Submits the info for a newly defined business.
     *
     * The business will not be submitted if any of the info is invalid.
     *
     * @param v Current view
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = mAppState.firebaseReference.push().getKey();
        String businessNum = mNumField.getText().toString();
        String name = mNameField.getText().toString();
        String primary = mPrimaryField.getText().toString();
        String address = mAddressField.getText().toString();
        String location = mLocationField.getText().toString();

        /*
            Tracks errors caused by incorrect input
         */
        String error = "";
        String primaries[] = {"Fisher", "Distributor", "Processor", "Fish Monger"};
        Arrays.sort(primaries);
        String locations[] = {"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT", ""};
        Arrays.sort(locations);

        if (!businessNum.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d")) { //if businessNum is not 9 digits
            error += "Business Number is not 9 digits\n";
        }

        if (name.length() > 48 || name.length() < 2) { //if name is not a proper length
            error += "Invalid name length\n";
        }

        if (Arrays.binarySearch(primaries, primary) < 0) {
            error += "Primary Business invalid\n";
        }

        if (address.length() > 49) {
            error += "Address too long\n";
        }

        if (Arrays.binarySearch(locations, location) < 0) {
            error += "Invalid location\n";
        }

        Business bus = new Business(businessID, businessNum, name, primary, address, location);

        if (error.isEmpty()) { //if there are no errors
            mAppState.firebaseReference.child(businessID).setValue(bus); //add the business
            finish();
        } else { //otherwise
            Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_SHORT).show(); //send toast
        }

    }
}
