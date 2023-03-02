package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DriverViewAssignment extends AppCompatActivity {
    TextView Name;
    TextView Location;
    TextView T_id;
    TextView AmbID;
    TextView _status;
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();

    Driver CurrentDriver=new Driver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Task currTask=new Task();
        CurrentDriver=(Driver) getIntent().getSerializableExtra("DriverClass");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view_assignment);
        Name=findViewById(R.id.ViewName);
        Location=findViewById(R.id.ViewLoc);
        T_id=findViewById(R.id.ViewTaskID);
        AmbID=findViewById(R.id.ViewAmbID);
        _status=findViewById(R.id.ViewStatus);

         currTask=DB.FindDriverTask(CurrentDriver.getDriverID());
         if (currTask!=null){
             Name.setText(currTask.getName());
             Location.setText(currTask.getLocation());
             T_id.setText(String.valueOf(currTask.getTaskID()));
             AmbID.setText(String.valueOf(currTask.getAmbulanceID()));
             _status.setText(currTask.getCurrentStatus().toString());
         }
    }


}