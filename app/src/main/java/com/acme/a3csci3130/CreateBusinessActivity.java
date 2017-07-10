package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateBusinessActivity extends Activity {

    private Button mSubmitButton;
    private EditText mNumField, mNameField, mAddressField;
    private Spinner mPrimarySpinner, mLocationSpinner;
    private MyApplicationData mAppState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_activity);
        //Get the app wide shared variables
        mAppState = ((MyApplicationData) getApplicationContext());

        mSubmitButton    = (Button) findViewById(R.id.submitButton);
        mNumField       = (EditText) findViewById(R.id.num);
        mNameField      = (EditText) findViewById(R.id.name);
        mAddressField   = (EditText) findViewById(R.id.address);

        //populate a spinner with primary business
        mPrimarySpinner   = (Spinner) findViewById(R.id.primary);
        mLocationSpinner  = (Spinner) findViewById(R.id.location);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.primary_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPrimarySpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource
                (this, R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLocationSpinner.setAdapter(adapter);

    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = mAppState.firebaseReference.push().getKey();
        String businessNum = mNumField.getText().toString(); //num
        String name = mNameField.getText().toString();
        String primary = mPrimarySpinner.getSelectedItem().toString();
        String address = mAddressField.getText().toString();
        String location = mLocationSpinner.getSelectedItem().toString();

        //if location was not entered, account for this
        if (location.equals("Select a Province/Territory (Optional)")) {
            location = "Not specified";
        }

        //remove me or use me later
        Toast.makeText(getApplicationContext(), location, Toast.LENGTH_SHORT).show();

        Business bus = new Business(businessID, name, primary, address, location);

        String error = "";
        //https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java

        //if a primary business has not been chosen
        if (location.equals("Select a Primary Business")) {
            error += "You must select a primary business\n";
        }

        //if (name.isEmpty() || )

        mAppState.firebaseReference.child(businessID).setValue(bus);

        finish();

    }
}
