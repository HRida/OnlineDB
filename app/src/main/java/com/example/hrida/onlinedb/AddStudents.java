package com.example.hrida.onlinedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudents extends AppCompatActivity {

    private EditText edId, edName;
    private Button btAddStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);
        edId = (EditText)findViewById(R.id.edId);
        edName = (EditText)findViewById(R.id.edName);
        btAddStudent = (Button)findViewById(R.id.btAddStudent);

        btAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                University db = new University(AddStudents.this);
                // key is used to prevent unauthorized write to database
                // its better that the user enters the key each time and not stored in the code
                db.setKey("aulubcDE");
                try {
                    int id = Integer.parseInt(edId.getText().toString());
                    String name = edName.getText().toString();
                    db.addStudent(new Student(id, name));
                }
                catch (Exception ex) {
                    Toast.makeText(AddStudents.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
