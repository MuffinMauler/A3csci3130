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
        String businessNum = mNumField.getText().toString();
        String name = mNameField.getText().toString();
        String primary = mPrimarySpinner.getSelectedItem().toString();
        String address = mAddressField.getText().toString();
        String location = mLocationSpinner.getSelectedItem().toString();

        /*
            Tracks errors caused by incorrect input
         */
        String error = "";

        if (!businessNum.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d")) { //if businessNum is not 9 digits
            error += "Business Number is not 9 digits\n";
        }

        if (name.length() > 48 || name.length() < 2) { //if name is not a proper length
            error += "Invalid name length";
        }

        if (primary.equals("Select a Primary Business")) {
            error += "Primary Business not selected\n";
        }

        if (address.length() > 49) {
            error += "Address too long\n";
        }

        //this is not an error
        if (location.equals("Select a Province/Territory (Optional)")) { //if location not selected
            location = "Not specified";
        }

        //remove me or use me later
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), location, Toast.LENGTH_SHORT).show();

        Business bus = new Business(businessID, businessNum, name, primary, address, location);


        //https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java

        //if ()

        //if a primary business has not been chosen
        if (location.equals("Select a Primary Business")) {
            error += "You must select a primary business\n";
        }

        if (error.isEmpty()) { //if there are no errors
            mAppState.firebaseReference.child(businessID).setValue(bus); //add the business
            finish();
        }

    }
}
