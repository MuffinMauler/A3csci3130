package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            mNumField.setText(mReceivedBusinessInfo.getBusinessNumber());
            mNameField.setText(mReceivedBusinessInfo.getName());
            mPrimaryField.setText(mReceivedBusinessInfo.getPrimaryBusiness());
            mAddressField.setText(mReceivedBusinessInfo.getAddress());
            mLocationField.setText(mReceivedBusinessInfo.getLocation());
        }
    }

    public void updateBusiness(View v){
        //TODO: Update contact funcionality
    }

    public void eraseBusiness(View v)
    {
        //TODO: Erase contact functionality
    }
}
