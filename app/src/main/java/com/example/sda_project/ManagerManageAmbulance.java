package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ManagerManageAmbulance extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    AmbulanceManager AM=new AmbulanceManager();
    String[] options=new String[]{"Ready","Maintenance"};
    Spinner Dropdown;
    AmbulanceStatus AStat=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_manage_ambulance);
       Dropdown = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        Dropdown.setAdapter(adapter);
        Dropdown.setOnItemSelectedListener(this);
    }

    public void UpdateAmb(View view){
        EditText AmbID=(EditText) findViewById(R.id.ManageAmbNo);
       // EditText Status=(EditText) findViewById(R.id.ManageAmbStatus);
//        if (Status.getText().toString().equals("Ready")){
//            AStat=AmbulanceStatus.Ready;
//        }else if (Status.getText().toString().equals("Maintenance")){
//            AStat=AmbulanceStatus.Maintainance;
//        }
        if(AM.SetStatus(AmbID.getText().toString(),AStat)){
            Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Unsuccessful!", Toast.LENGTH_SHORT).show();

        };
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