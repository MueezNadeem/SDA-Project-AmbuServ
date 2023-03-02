package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerVerifyTask extends AppCompatActivity {
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();
    TextView Name;
    TextView Address;
    TextView _status;
    TextView DriverID;
    TextView AmbID;
    CheckBox CB;
    EditText VerifyBox;
    Task temp=new Task();

    //TODO: add code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_verify_task);
        Name=(TextView) findViewById(R.id.VerifyTaskName);
        Address=(TextView) findViewById(R.id.VerifyArea);
        _status=(TextView) findViewById(R.id.VerifyStatus);
        DriverID=(TextView) findViewById(R.id.VerifyDriverID);
        AmbID=(TextView) findViewById(R.id.VerifyAmbID);
        CB=(CheckBox) findViewById(R.id.VerifyCB);
        VerifyBox=(EditText) findViewById(R.id.VerifyIDBox);
    }
    public void UpdateTask(View view){
        if (temp.getCurrentStatus()!=TaskStatus.Completed && temp!=null){
            if (CB.isChecked()){
                temp.setCurrentStatus(TaskStatus.Completed);
                SQLiteDatabase db=DB.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("Status",1);
                db.update("Tasks",values,"TaskID=?",new String[]{VerifyBox.getText().toString()});


                ContentValues v2=new ContentValues();
                ContentValues v3=new ContentValues();
                v2.put("Availability","1");
                db.update("Driver",v2,"DriverID=?",new String[]{DriverID.getText().toString()});

                v3.put("CurrentStatus","RY");
                db.update("Ambulance",v3,"AmbulanceID=?",new String[]{String.valueOf(temp.getAmbulanceID())});

                db.close();
            }

        }


    }
    public  void FindTask(View view){
        EditText TaskID=(EditText) findViewById(R.id.VerifyIDBox);
        if (!TaskID.getText().toString().equals("")){
        temp =DB.FindTask(TaskID.getText().toString());
        if(temp == null){
           Toast T1= Toast.makeText(this,"Invalid ID entered", Toast.LENGTH_SHORT);
           T1.show();
        }else {
            if (!(temp.getAmbulanceID()==0||temp.getDriverID()==0)) {
                Name.setText(String.format("TaskName:%s", temp.getName()));
                Address.setText(String.format("Address:%s", temp.getLocation()));
                _status.setText(String.format("Status:%s", temp.getCurrentStatus().toString()));
                DriverID.setText(String.format("DriverID:%s", String.valueOf(temp.getDriverID())));
                AmbID.setText(String.format("AmbulanceID:%s", String.valueOf(temp.getAmbulanceID())));
            } else {
                Toast.makeText(this, "Task not dispatched", Toast.LENGTH_SHORT).show();
                Name.setText("");
                Address.setText("");
                _status.setText("");
                DriverID.setText("");
                AmbID.setText("");

            }
        }
        }
    }
}