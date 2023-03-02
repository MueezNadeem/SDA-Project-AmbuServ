package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DispatcherViewDrivers extends AppCompatActivity {

    private List<String> DriverList=new ArrayList<>();
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();
    private ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher_view_drivers);

        DriverList=DB.GetAllDrivers();
        LV=(ListView) findViewById(R.id.listAvailDriver);
        if(DriverList!=null) {
            ArrayAdapter<String> arr = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, DriverList);
            LV.setAdapter(arr);
        }
    }
}