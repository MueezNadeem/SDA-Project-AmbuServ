package com.example.sda_project;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ManagerAddAmbulance extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private AmbulanceManager AM;
    String[] options=new String[]{"Ready","Maintenance"};
    Spinner Dropdown;
    AmbulanceStatus AStat=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_ambulance);
        AM=new AmbulanceManager();
        Dropdown = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        Dropdown.setAdapter(adapter);
        Dropdown.setOnItemSelectedListener(this);
    }

    public void AddAmbulance(View view){
        Ambulance CurrentAmb=new Ambulance();
        EditText AmbNo=(EditText) findViewById(R.id.AmbulanceNo);


        int AmbulanceNo=Integer.parseInt(AmbNo.getText().toString());
        CurrentAmb.setAmbulanceNo(AmbulanceNo);
        int AmbulanceID=AM.GetNextID();
        CurrentAmb.setAmbulanceID(AmbulanceID);
//        if (AmbStatus.getText().toString().equals("Ready")){
//            CurrentAmb.setCurrentStatus(AmbulanceStatus.Ready);
//        }else if (AmbStatus.getText().toString().equals("Maintenance")) {
//            CurrentAmb.setCurrentStatus(AmbulanceStatus.Maintainance);
//        }
        CurrentAmb.setCurrentStatus(AStat);
        AM.AddAmbulancetoDB(CurrentAmb);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                AStat=AmbulanceStatus.Ready;
                break;
            case 1:
                AStat=AmbulanceStatus.Maintainance;

                // Whatever you want to happen when the second item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}