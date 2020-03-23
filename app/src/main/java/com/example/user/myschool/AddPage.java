package com.example.user.myschool;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddPage extends AppCompatActivity implements View.OnClickListener{

    EditText editTextName;
    EditText TeacherNameEditText;
    EditText NotesEditText;
    public static int hour;
    public static int min;
    static Button pickTimeButton;
    public static int dayy;
    public static int monthh;
    public static int yearr;
    static Button pickDateButton, confirmButton;
    Spinner spinnerHW;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users/"+user.getUid()+"/HW/");
    private int SHWposition=0;
    final String[] HWSubjectsArr = getResources().getStringArray(R.array.HWSubjectsArr);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        editTextName = findViewById(R.id.editTextName);
        TeacherNameEditText = findViewById(R.id.TeacherNameEditText);
        NotesEditText = findViewById(R.id.NotesEditText);
        pickTimeButton = findViewById(R.id.pickTimeButton);
        pickDateButton = findViewById(R.id.pickDateButton);
        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(this);

        spinnerHW = findViewById(R.id.spinnerHW);

        ArrayAdapter<CharSequence> HWSubjectsAdapter = ArrayAdapter.createFromResource(this,
                R.array.HWSubjectsArr, android.R.layout.simple_spinner_item);
  //      HWSubjectsArr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHW.setAdapter(HWSubjectsAdapter);
        spinnerHW.setSelection(0);

        spinnerHW.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), HWSubjectsArr[position], Toast.LENGTH_LONG).show();
                SHWposition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), HWSubjectsArr[0], Toast.LENGTH_LONG).show();
                SHWposition = 0;

            }
        });


    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {
        //public HomeWork(String name, String time, String date, String subject, String notes, String teacher) {

        HomeWork homeWork = new HomeWork(editTextName.getText().toString(),pickTimeButton.getText().toString(),pickDateButton.getText().toString(),HWSubjectsArr[SHWposition]
        ,NotesEditText.getText().toString(),TeacherNameEditText.getText().toString());
        myRef.push().setValue(homeWork);

    }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            hour = hourOfDay;
            min = minute;
            pickTimeButton.setText(hour+":"+min);
        }
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            dayy=day;
            monthh=month+1;
            yearr=year;
            pickDateButton.setText(dayy+"/"+monthh+"/"+yearr);
        }
    }

}
