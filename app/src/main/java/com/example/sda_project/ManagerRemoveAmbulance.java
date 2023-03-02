package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManagerRemoveAmbulance extends AppCompatActivity {
    private AmbulanceManager AM=new AmbulanceManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_remove_ambulance);
    }
    public void DeleteAmbulance(View view){
        EditText AmbNo=(EditText) findViewById(R.id.DelAmbID);
        if (AM.CheckAmbulanceExistence(AmbNo.getText().toString())){
            AM.DeleteAmbulance(AmbNo.getText().toString());

            //Todo: Print success msg;
            Toast ErrMsg=Toast.makeText(this,"Deletion Successful",Toast.LENGTH_SHORT);
            ErrMsg.show();
        }else{

            //Todo: Print failure msg;
            Toast ErrMsg=Toast.makeText(this,"Invalid ID entered",Toast.LENGTH_SHORT);
            ErrMsg.show();
        }
    }

}