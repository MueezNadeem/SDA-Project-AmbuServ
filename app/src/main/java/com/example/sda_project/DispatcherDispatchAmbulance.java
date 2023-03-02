package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DispatcherDispatchAmbulance extends AppCompatActivity {
    private AvailabilityChecker AC=new AvailabilityChecker();
    private Dispatcher CurrDispatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher_dispatch_ambulance);
        CurrDispatcher=(Dispatcher) getIntent().getSerializableExtra("DispatcherClass");
    }

    public void Dispatch(View view){
        EditText TaskID=(EditText) findViewById(R.id.DispTaskID);
        EditText AmbID=(EditText) findViewById(R.id.DispAmbID);
        EditText DriverID=(EditText) findViewById(R.id.DispDriverID);

        if (AC.CheckAvailability(DriverID.getText().toString(),AmbID.getText().toString())){


            //TODO: Write code
            AC.AssignToTask(TaskID.getText().toString(),DriverID.getText().toString(),AmbID.getText().toString());

        }else {
            Toast ErrMsg=Toast.makeText(this,"Driver or Ambulance not available",Toast.LENGTH_SHORT);
            ErrMsg.show();
            //TODO: Generate err msg // DONE!!
        };

    }


    //TODO: make list views
    public void ViewTasks(View view){
            Intent i=new Intent(this,DispatcherViewTasks.class);
            startActivity(i);
    }

    public void ViewAmbulances(View view){
        Intent i=new Intent(this,DispatcherViewAmbulance.class);
        startActivity(i);
    }

    public void ViewDrivers(View view){
        Intent i=new Intent(this,DispatcherViewDrivers.class);
        startActivity(i);
    }
}