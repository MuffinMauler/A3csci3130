package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class DetailViewActivity extends Activity {

    private EditText mNumField, mNameField, mPrimaryField, mAddressField, mLocationField;
    Business mReceivedBusinessInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        mReceivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

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
     * @param v View of the selected business' info.
     */
    public void updateBusiness(View v){

    }

    /**
     * Delete the current business from Firebase and the app
     *
     * @param v View of the selected business' info.
     */
    public void eraseBusiness(View v)
    {
        //TODO: Erase contact functionality
    }
}
