package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DispatcherViewAmbulance extends AppCompatActivity {

    private List<String> AmbList=new ArrayList<>();
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();
    private ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher_view_ambulance);

        AmbList=DB.GetAvailableAmb();
        LV=(ListView) findViewById(R.id.listAvailAmb);
        if (AmbList!=null){
        ArrayAdapter<String> arr=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,AmbList);
        LV.setAdapter(arr);
        }
    }
}