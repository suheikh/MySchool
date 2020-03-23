package com.example.user.myschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeworkActivity extends AppCompatActivity implements View.OnClickListener {

    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==addButton){
            Intent i = new Intent(HomeworkActivity.this,AddPage.class);
            startActivity(i);
        }
    }
}
