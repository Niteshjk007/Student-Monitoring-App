package com.android.attendance.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidattendancesystem.R;

import java.util.ArrayList;

public class ViewMarksActivity extends Activity {

    ListView marksListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_marks);

        marksListView = findViewById(R.id.marksListView);

        // Retrieve marksList from intent
        ArrayList<String> marksList = getIntent().getStringArrayListExtra("marksList");

        // Create and set ArrayAdapter to display marks in ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, marksList);
        marksListView.setAdapter(adapter);
    }
}
