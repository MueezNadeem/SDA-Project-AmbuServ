package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Patient_Homepage extends AppCompatActivity {
    private Patient CurrentPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_homepage);
        CurrentPatient=(Patient) getIntent().getSerializableExtra("PatientClass");
        String Name=CurrentPatient.getName();
        TextView Welcome=(TextView) findViewById(R.id.WelcomeMsg);
        Welcome.setText(String.format("Welcome,%s", Name));
    }

    public void OrderActivity(View view){
        Intent i=new Intent(this,Patient_Order.class);
        i.putExtra("PatientClass",CurrentPatient);
        startActivity(i);
    }
    public void TrackRecordActivity(View view){
        Intent i=new Intent(this,PatientViewRecord.class);
        i.putExtra("PatientClass",CurrentPatient);
        startActivity(i);
    }

    public void ExitApp(View view){
        finish();
        System.exit(0);
    }

    public void PatientViewProfile(View view){
        Intent i=new Intent(this,EmployeeViewProfile.class);
        i.putExtra("EmpClass",CurrentPatient);
        startActivity(i);
    }
}