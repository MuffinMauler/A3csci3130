package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText mNameField, mPrimaryField, mAddressField, mLocationField;
    private MyApplicationData mAppState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        mAppState = ((MyApplicationData) getApplicationContext());

        submitButton    = (Button) findViewById(R.id.submitButton);
        mNameField      = (EditText) findViewById(R.id.name);
        mPrimaryField   = (EditText) findViewById(R.id.primary);
        mAddressField   = (EditText) findViewById(R.id.address);
        mLocationField  = (EditText) findViewById(R.id.location);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = mAppState.firebaseReference.push().getKey();
        String name = mNameField.getText().toString();
        String primary = mPrimaryField.getText().toString();
        String address = mAddressField.getText().toString();
        String location = mLocationField.getText().toString();
        Business bus = new Business(businessID, name, primary, address, location);

        mAppState.firebaseReference.child(businessID).setValue(bus);

        finish();

    }
}
