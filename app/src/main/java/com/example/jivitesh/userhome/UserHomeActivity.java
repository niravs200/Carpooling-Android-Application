package com.example.jivitesh.userhome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

public class UserHomeActivity extends Activity implements AdapterView.OnItemSelectedListener {
    EditText etUserName, etPickup, etDrop;
    Button btnRequestRide;
    Spinner spinner;
    DatePicker datePicker;

    // library: AwesomeValidation, Link: https://github.com/thyrlian/AwesomeValidation


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
  /*      *//*      Declare validation style;
        Add validations;
        Set a point when to trigger validation.*//*

        // Declare validation style
        final AwesomeValidation mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);
        //mAwesomeValidation.setTextInputLayoutErrorTextAppearance(R.style.TextInputLayoutErrorStyle); // optional, default color is holo_red_light if not set
        AwesomeValidation.disableAutoFocusOnFirstFailure();

        // Step 2: add validations
        // support regex string, java.util.regex.Pattern and Guava#Range
        // you can pass resource or string
        mAwesomeValidation.addValidation(this, R.id.etPickUp, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);

        // Step 3: set a trigger
        btnRequestRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAwesomeValidation.validate();
            }
        });*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

      /*  etUserName = (EditText) findViewById(R.id.layout_userName);
        etPickup = (EditText) findViewById(R.id.layout_pickup);
        etDrop = (EditText) findViewById(R.id.layout_drop);*/
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPickup = (EditText) findViewById(R.id.etPickUp);
        etDrop = (EditText) findViewById(R.id.etDrop);
        btnRequestRide = (Button)findViewById(R.id.btnRequestRide);
        spinner = (Spinner)findViewById(R.id.spnCapacity);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        datePicker.findViewById(getResources().getIdentifier("year","id","android")).setVisibility(View.GONE); // hiding year

        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,  R.array.capacity, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);
        // add listener
        spinner.setOnItemSelectedListener(this);



        etDrop.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
               // if(etDrop.getEditText().getText().toString().length() < 5 ){
                if(etDrop.getText().toString().length() == 0 ){
                    etDrop.setError("Please enter a drop location");
                }
                else {

                }
            }
        });

        etPickup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //if(etPickup.getEditText().getText().toString().length() < 5 ){
                if(etPickup.getText().toString().length() == 0 ){
                    etPickup.setError("Please enter a pick up location");
                }
                else{

                }

            }
        });


        btnRequestRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etDrop.getError() != null || etDrop.getText().length() == 0 || etPickup.getError() != null || etPickup.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"No error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Calendar ca = Calendar.getInstance();
        datePicker.setMinDate(ca.getTimeInMillis());
        ca.add(Calendar.DAY_OF_MONTH, 6); // add 7 days from now
        datePicker.setMaxDate(ca.getTimeInMillis());



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
       // etUserName.getEditText().setText(parent.getItemAtPosition(position).toString());
        //etUserName.setText(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
