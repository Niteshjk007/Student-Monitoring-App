package com.android.attendance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.attendance.db.DBAdapter1;
import com.example.androidattendancesystem.R;

import java.util.ArrayList;

public class AddMarksActivity extends Activity {

    EditText studentNameEditText;
    EditText marksEditText;
    Spinner branchSpinner;
    Spinner yearSpinner;
    Spinner subjectSpinner;
    Button submitButton;
    Button viewMarksButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_marks);

        studentNameEditText = findViewById(R.id.studentNameEditText);
        marksEditText = findViewById(R.id.marksEditText);
        branchSpinner = findViewById(R.id.branchSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        submitButton = findViewById(R.id.submitButton);
        viewMarksButton = findViewById(R.id.viewMarksButton);

        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = studentNameEditText.getText().toString().trim();
                String marks = marksEditText.getText().toString().trim();
                String branch = branchSpinner.getSelectedItem().toString();
                String year = yearSpinner.getSelectedItem().toString();
                String subject = subjectSpinner.getSelectedItem().toString();

                if (studentName.isEmpty() || marks.isEmpty()) {
                    Toast.makeText(AddMarksActivity.this, "Please enter student name and marks",
                            Toast.LENGTH_SHORT).show();
                } else {
                    DBAdapter1 dbAdapter = new DBAdapter1(AddMarksActivity.this);
                    dbAdapter.addMarks(studentName, marks, branch, year, subject);

                    Toast.makeText(AddMarksActivity.this, "Marks added successfully",
                            Toast.LENGTH_SHORT).show();
                    finish(); // Finish the activity after adding marks
                }
            }
        });

        viewMarksButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String branch = branchSpinner.getSelectedItem().toString();
                String year = yearSpinner.getSelectedItem().toString();
                String subject = subjectSpinner.getSelectedItem().toString();

                DBAdapter1 dbAdapter = new DBAdapter1(AddMarksActivity.this);
                ArrayList<String> marksList = dbAdapter.getMarksByDepartmentYearSubject(branch, year, subject);

                // Start activity to display marks passing the marksList
                Intent intent = new Intent(AddMarksActivity.this, ViewMarksActivity.class);
                intent.putStringArrayListExtra("marksList", marksList);
                startActivity(intent);
            }
        });
    }
}
