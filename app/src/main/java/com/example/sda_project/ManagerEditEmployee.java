package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ManagerEditEmployee extends AppCompatActivity {
    EmployeeManager EM=new EmployeeManager();
    Employee tempEmp=null;
    EditText EmpID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_edit_employee);
    }

    public void EditEmp(View view){
        // TODO: Complete code
         EmpID=(EditText) findViewById(R.id.EditEmpID);
        EditText NewName=(EditText) findViewById(R.id.NewName);
        EditText NewAdd=(EditText) findViewById(R.id.NewAdd);
        EditText NewPhone=(EditText) findViewById(R.id.NewPhone);
        EditText NewCNIC=(EditText) findViewById(R.id.NewCNIC);
        if (EM.CheckEmployeeExistence(EmpID.getText().toString())){
            String getUname= EM.getEmployeeUName(EmpID.getText().toString());
            tempEmp=new Employee();
            tempEmp=EM.getEmp(getUname);
            NewName.setText(tempEmp.getName());
            NewAdd.setText(tempEmp.getAddress());
            NewPhone.setText(tempEmp.getPhoneNum());
            NewCNIC.setText(tempEmp.getCNIC());
        }else{
            NewName.setText("");
            NewAdd.setText("");
            NewPhone.setText("");
            NewCNIC.setText("");
            Toast ErrMsg=Toast.makeText(this,"Invalid ID",Toast.LENGTH_SHORT);
            ErrMsg.show();

        }

    }

    public void UpdateEmp(View view){
        if (tempEmp!=null){
            EditText NewName=(EditText) findViewById(R.id.NewName);
            EditText NewAdd=(EditText) findViewById(R.id.NewAdd);
            EditText NewPhone=(EditText) findViewById(R.id.NewPhone);
            EditText NewCNIC=(EditText) findViewById(R.id.NewCNIC);

            if (IsValid(NewName.getText().toString(), NewCNIC.getText().toString(), NewAdd.getText().toString(), NewPhone.getText().toString())) {
                tempEmp.setUserName(EM.getEmployeeUName(EmpID.getText().toString()));
                tempEmp.setName(NewName.getText().toString());
                tempEmp.setPhoneNum(NewPhone.getText().toString());
                tempEmp.setCNIC(NewCNIC.getText().toString());
                tempEmp.setAddress(NewAdd.getText().toString());
                EM.UpdateEmployee(tempEmp);
                Toast ErrMsg=Toast.makeText(this,"Info Updated",Toast.LENGTH_SHORT);
                ErrMsg.show();
            }
        }else {
            Toast ErrMsg=Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT);
            ErrMsg.show();

        }

    }

    public boolean IsValid(String Name, String CNIC, String HomeAdd, String Phone)
    {
        Toast Fail;
        if (Name.isEmpty())
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Please Enter a Name in the Name field.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if (CNIC.length() != 13)
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, CNIC should contain 13 digits.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if (!Objects.equals(tempEmp.getCNIC(), CNIC) && EM.CheckEmployeeExistenceByCNIC(CNIC))
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Another Account has the same CNIC.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if (HomeAdd.isEmpty())
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Please Enter a Home Address in the Address field.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if (Phone.length() != 13 || !Patterns.PHONE.matcher(Phone).matches())
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, InValid Phone Number. Please Enter a phone number like: +923001234111.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        return true;
    }

}