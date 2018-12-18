package com.example.hrida.onlinedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btGetAllStudents, btGetStudentName, btOpenAddStudents, btGetImage;
    private EditText edStudentID;
    private ImageView iv1;
    private University db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btGetAllStudents = (Button)findViewById(R.id.btGetAllStudents);
        btGetStudentName = (Button)findViewById(R.id.btGetStudentName);
        btOpenAddStudents = (Button)findViewById(R.id.btOpenAddStudents);
        btGetImage = (Button)findViewById(R.id.btGetImage);
        edStudentID = (EditText)findViewById(R.id.edStudentID);
        iv1 = (ImageView)findViewById(R.id.iv1);

        db = new University(MainActivity.this);

        btGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateImage(iv1);
            }
        });

        btOpenAddStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddStudents.class));
            }
        });

        btGetAllStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, GetAllStudents.class));
            }
        });

        btGetStudentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id = Integer.parseInt(edStudentID.getText().toString());
                    University db = new University(MainActivity.this);
                    db.getStudentName(id);
                }
                catch (Exception ex) {
                    Log.w("error", ex.getMessage());
                }

            }
        });
    }
}
