package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PatientViewRecord extends AppCompatActivity {
    //TODO: Make List view
    private List<String> TaskList=new ArrayList<>();
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();
    private ListView LV;
    private Patient CurrentPatient=new Patient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_record);

        CurrentPatient=(Patient) getIntent().getSerializableExtra("PatientClass");


        TaskList=DB.GetPatRecords(String.valueOf( CurrentPatient.getPatientID() ) );
        LV=(ListView) findViewById(R.id.PatRecords);
        if(TaskList!=null) {
            ArrayAdapter<String> arr = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, TaskList);
            LV.setAdapter(arr);
        }
    }
}