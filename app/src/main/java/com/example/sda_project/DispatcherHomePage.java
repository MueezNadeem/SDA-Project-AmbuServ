package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DispatcherHomePage extends AppCompatActivity {
    private Dispatcher CurrDispatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher_home_page);
        CurrDispatcher=(Dispatcher) getIntent().getSerializableExtra("DispatcherClass");
        TextView WelcomeMsg=(TextView) findViewById(R.id.DispWelcome);
        WelcomeMsg.setText(String.format("Welcome, %s", CurrDispatcher.getName()));
    }

    public void DispatchAmbActivity(View view){
        Intent i=new Intent(this,DispatcherDispatchAmbulance.class);
        i.putExtra("DispatcherClass",CurrDispatcher);
        startActivity(i);
    }

    public void MarkDispAttendance(View view){
        Intent i=new Intent(this,EmployeeMarkAttendance.class);
        i.putExtra("EmpClass",CurrDispatcher);
        startActivity(i);
    }

    public void ExitApp(View view){
        finish();
        System.exit(0);
    }


    //TODO: create view profile// Done!
    public void ViewDispProfile(View view){
        Intent i=new Intent(this,EmployeeViewProfile.class);
        i.putExtra("EmpClass",CurrDispatcher);
        startActivity(i);
    }


    //Todo: create team if time allows

}