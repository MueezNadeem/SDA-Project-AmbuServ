package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DriverHomepage extends AppCompatActivity {
    private  Employee CurrentDriver;
    private  AvailabilityChecker AC=new AvailabilityChecker();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CurrentDriver=(Employee)getIntent().getSerializableExtra("DriverClass");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_worker_homepage);
        TextView WelcomeMsg=(TextView) findViewById(R.id.DriverWelcome);
        WelcomeMsg.setText(String.format("Welcome, %s", CurrentDriver.getName()));

    }

    public void ViewCurrAssignment(View view){
        Intent i=new Intent(this,DriverViewAssignment.class);
        i.putExtra("DriverClass",CurrentDriver);
        startActivity(i);
    }

    public void MarkDriverAttendance(View view){
        Intent i=new Intent(this,EmployeeMarkAttendance.class);
        i.putExtra("EmpClass",CurrentDriver);
        startActivity(i);
    }

    public void ViewDriverProfile(View view){
        Intent i=new Intent(this,EmployeeViewProfile.class);
        i.putExtra("EmpClass",CurrentDriver);
        startActivity(i);
    }
}