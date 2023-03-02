package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ManagerHomePage extends AppCompatActivity {
    private Employee CurrentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CurrentManager=(Employee) getIntent().getSerializableExtra("ManagerClass");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home_page);
        TextView WelcomeMsg=(TextView)findViewById(R.id.MgrWelcome);
       WelcomeMsg.setText(String.format("Welcome %s", CurrentManager.getName()));

    }
    public void ExitApp(View view){

        System.exit(0);
    }



    public  void AddAmbulanceActivity(View view){
        Intent i=new Intent(this,ManagerAddAmbulance.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void DelAmbulanceActivity(View view){
        Intent i=new Intent(this,ManagerRemoveAmbulance.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void AddEmployeeActivity(View view){
        Intent i=new Intent(this,ManagerAddEmployee.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void DelEmployeeActivity(View view){
        Intent i=new Intent(this,ManagerDeleteEmployee.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void EditEmployeeActivity(View view){
        Intent i=new Intent(this,ManagerEditEmployee.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void ViewEmployeesActivity(View view){
        Intent i=new Intent(this,ManagerViewEmployees.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void ViewAmbulanceActivity(View view){
        Intent i=new Intent(this,ManagerViewAmbulance.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void ViewTasksActivity(View view){
        Intent i=new Intent(this,ManagerViewTasks.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void VerifyTasksActivity(View view){
        Intent i=new Intent(this,ManagerVerifyTask.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public  void ManageAmbulanceActivity(View view){
        Intent i=new Intent(this,ManagerManageAmbulance.class);
        i.putExtra("ManagerClass",CurrentManager);
        startActivity(i);
    }

    public void ManagerViewProfile(View view){
        Intent i=new Intent(this,EmployeeViewProfile.class);
        i.putExtra("EmpClass",CurrentManager);
        startActivity(i);
    }
}