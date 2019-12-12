package com.example.user.myschool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity  {


    private static final String TAG ="FIREBASE" ;
    private FirebaseAuth mAuth;
    EditText editTextFullName;
    EditText editTextEmail;
    EditText editTextPassword;
    Spinner spinnerClass;
    Spinner spinnerFMajor;
    Spinner spinnerSMajor;
    EditText editTextID;
    Button buttonConfirm;
    int classesPosition;
    int FMajorPosition;
    int SMajorPosition;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);





        mAuth = FirebaseAuth.getInstance();
        Spinner spinnerGrade = (Spinner) findViewById(R.id.spinnerClass);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterGrade = ArrayAdapter.createFromResource(this,
                R.array.class_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(adapterGrade);
        spinnerGrade.setSelection(0);
// Apply the adapter to the spinner
        Spinner spinnerFMajor = (Spinner) findViewById(R.id.spinnerFMajor);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterFMajor = ArrayAdapter.createFromResource(this,
                R.array.Fmajor_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterFMajor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFMajor.setAdapter(adapterFMajor);
        spinnerFMajor.setSelection(0);

// Apply the adapter to the spinner
        Spinner spinnerSMajor = (Spinner) findViewById(R.id.spinnerSMajor);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSMajor = ArrayAdapter.createFromResource(this,
                R.array.Smajor_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterSMajor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSMajor.setAdapter(adapterSMajor);
        spinnerSMajor.setSelection(0);

// Apply the adapter to the spinner

        final String[] classes = getResources().getStringArray(R.array.class_array);
        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), classes[position], Toast.LENGTH_LONG).show();
                classesPosition = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classesPosition = 0;
            }
        });
        final String[] FMajor = getResources().getStringArray(R.array.Fmajor_array);
        spinnerFMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), FMajor[position], Toast.LENGTH_LONG).show();
                FMajorPosition = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                FMajorPosition = 0;
            }
        });
        final String[] SMajor = getResources().getStringArray(R.array.Smajor_array);
        spinnerSMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), SMajor[position], Toast.LENGTH_LONG).show();
                SMajorPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SMajorPosition = 0;

            }
        });

        editTextFullName=findViewById(R.id.editTextFullName);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextID=findViewById(R.id.editTextID);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextFullName.getText().equals("")&&!editTextEmail.getText().equals("")
                        &&!editTextPassword.getText().equals("")&&!editTextID.getText().equals("")) {
                        if (classesPosition != 0 && FMajorPosition != 0 && SMajorPosition != 0) {
                            Toast.makeText(SignUpActivity.this, "Success",
                                    Toast.LENGTH_SHORT).show();
                            SignUp(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "Empty Spinners",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Empty Fields",
                                Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }
    public void SignUp(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                addUserDetails(user.getUid());
                                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                                startActivity(i);
//                            user.getUid()
//                            updateUI(user);

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    public void addUserDetails(String UID){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User/"+UID+"/Profile");
        myRef.child("name").setValue(editTextFullName.getText().toString());

    }

}
