package com.example.user.myschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    List<String> list;
    int[] image = {
            R.drawable.homeworkicon,
            R.drawable.groupchaticon,
            R.drawable.examicon,
            R.drawable.notesicon,
            R.drawable.addfriendsicon,
    };
    String[] text = {
            "Homeworks",
            "Group Chat",
            "Exams",
            "Notes",
            "Add Friends",

    } ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView grid=(GridView)findViewById(R.id.gridView);

        ImageAdapter adapter = new ImageAdapter(MainActivity.this,text, image);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(text[position].equals("Homeworks")){
                    Intent i = new Intent(MainActivity.this,HomeworkActivity.class);
                    startActivity(i);
                }
                if(text[position].equals("Group Chat")){
                    Intent i = new Intent(MainActivity.this,GroupchatActivity.class);
                    startActivity(i);
                }
                if(text[position].equals("Exams")){
                    Intent i = new Intent(MainActivity.this,ExamsActivity.class);
                    startActivity(i);
                }
                if(text[position].equals("Notes")){
                    Intent i = new Intent(MainActivity.this,NotesActivity.class);
                    startActivity(i);
                }
                if(text[position].equals("Add Friends")){
                    Intent i = new Intent(MainActivity.this,AddfriendsActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
