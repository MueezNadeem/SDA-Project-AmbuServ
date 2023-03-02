package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManagerDeleteEmployee extends AppCompatActivity {
    EmployeeManager EM=new EmployeeManager();
    Employee CurrentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_delete_employee);
        CurrentManager=(Employee) getIntent().getSerializableExtra("ManagerClass");

    }

    public void DelEmp(View view){
        EditText EmpID=(EditText) findViewById(R.id.DelEmpID);
        if (EM.CheckEmployeeExistence(EmpID.getText().toString()) && (!EmpID.getText().toString().equals(String.valueOf(CurrentManager.getEmployeeID())))){
            EM.DeleteEmp(EmpID.getText().toString()); //ToDO: define function // can be done via execSql //DONE;

            if (!EM.CheckEmployeeExistence(EmpID.getText().toString()))
            {
                //Todo success msg //Done;
                Toast ErrMsg=Toast.makeText(this,"Deletion Successful",Toast.LENGTH_SHORT);
                ErrMsg.show();
            }
        }else{
            Toast ErrMsg=Toast.makeText(this,"Invalid ID",Toast.LENGTH_SHORT);
            ErrMsg.show();
            //todo del msg// DONE;
        }

    }
}