package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class DetailViewActivity extends Activity {

    private EditText mNumField, mNameField, mPrimaryField, mAddressField, mLocationField;
    Business mReceivedBusinessInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        mReceivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");

        //initialize text fields
        mNumField       = (EditText) findViewById(R.id.num);
        mNameField      = (EditText) findViewById(R.id.name);
        mPrimaryField   = (EditText) findViewById(R.id.primary);
        mAddressField   = (EditText) findViewById(R.id.address);
        mLocationField  = (EditText) findViewById(R.id.location);

        if(mReceivedBusinessInfo != null){
            mNumField.setText(mReceivedBusinessInfo.businessNumber);
            mNameField.setText(mReceivedBusinessInfo.name);
            mPrimaryField.setText(mReceivedBusinessInfo.primaryBusiness);
            mAddressField.setText(mReceivedBusinessInfo.address);
            mLocationField.setText(mReceivedBusinessInfo.location);
        }
    }

    /**
     * Update the info for the selected business.
     *
     * The business will not update if any of the info is invalid.
     *
     * @param v View of the selected business' info.
     */
    public void updateBusiness(View v){

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        DatabaseReference ref = appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

        String key = mReceivedBusinessInfo.businessID; //key to refer to the business in firebase
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

        //create a new business with the specified values
        Business updatedBusiness = new Business(key, businessNum, name, primary, address, location);

        if (error.isEmpty()) { //if there are no errors
            //add the business to Firebase
            ref.child(key).setValue(updatedBusiness);
            finish(); //exit the activity
        } else { //otherwise
            Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_SHORT).show(); //send toast
        }

    }

    /**
     * Delete the current business from Firebase and the app.
     *
     * @param v View of the selected business' info.
     */
    public void eraseBusiness(View v) {
        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        DatabaseReference ref = appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

        String key = mReceivedBusinessInfo.businessID; //key of the business to be deleted
        ref.child(key).removeValue();
        finish(); //exit the activity
    }
}
