package com.android.attendance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidattendancesystem.R;

public class StudentMenuActivity extends Activity {

    Button addattendance;
    Button addmarks;


    Button logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmenu);

        addattendance =(Button)findViewById(R.id.button2);
        addmarks =(Button)findViewById(R.id.button4);
        logout =(Button)findViewById(R.id.button3);

        addattendance.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(StudentMenuActivity.this,AddAttandanceSessionActivity.class);
                startActivity(intent);

            }
        });
        addmarks.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(StudentMenuActivity.this,AddMarksActivity.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(StudentMenuActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });




    }


}
