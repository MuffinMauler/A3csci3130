package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;

public class DetailViewActivity extends Activity {

    private EditText mNumField, mNameField, mPrimaryField, mAddressField, mLocationField;
    Business mReceivedBusinessInfo;
    private ListView mBusinessListView;
    private FirebaseListAdapter<Business> firebaseAdapter;

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

    public void updateBusiness(View v){

    }

    public void eraseBusiness(View v)
    {
        //TODO: Erase contact functionality
    }
}
