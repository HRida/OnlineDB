package com.example.hrida.onlinedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class GetAllStudents extends AppCompatActivity {

    private ListView lsAllStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_students);
        lsAllStudents = (ListView) findViewById(R.id.lsAllStudents);
        University db = new University(this);
        db.updateAllStudents(lsAllStudents);
    }
}
