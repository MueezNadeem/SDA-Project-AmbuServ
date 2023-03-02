package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ManagerViewTasks extends AppCompatActivity {
    private List<String> TaskList=new ArrayList<>();
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();
    private ListView LV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_tasks);
        TaskList=DB.GetAllTasks();
        LV=(ListView) findViewById(R.id.list);
        ArrayAdapter<String> arr=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,TaskList);
         LV.setAdapter(arr);
    }


}