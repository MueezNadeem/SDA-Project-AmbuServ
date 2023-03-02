package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ManagerViewEmployees extends AppCompatActivity {
    private List<String> EmpList=new ArrayList<>();
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();
    private ListView LV;
    ArrayAdapter<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_employees);

        EmpList=DB.GetAllEmployees();
        LV=(ListView) findViewById(R.id.listEmp);
        arr=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,EmpList);
        LV.setAdapter(arr);

        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent i=new Intent(ManagerViewEmployees.this,EmployeeViewProfile.class);
                String S = (String) (LV.getItemAtPosition(position));
                Employee temp = getprofiledata(S);
                i.putExtra("EmpClass",temp);
                startActivity(i);
            }
        });
    }

    public Employee getprofiledata(String S)
    {
        Employee Emp=new Employee();
        String Name = S.substring(S.indexOf("\n\n Name:") + 9, S.indexOf("\n\n CNIC:"));
        String DOB = S.substring(S.indexOf("\n\n DOB:") + 8, S.indexOf("\n\n Address:"));
        String Address = S.substring(S.indexOf("\n\n Address:") + 12, S.indexOf("\n\n Employee Type:"));
        String CNIC = S.substring(S.indexOf("\n\n CNIC:") + 9, S.indexOf("\n\n Phone #:"));
        String Phone = S.substring(S.indexOf("\n\n Phone #:") + 10, S.indexOf("\n\n DOB:"));
        String ET = S.substring(S.indexOf("\n\n Employee Type:") + 18, S.indexOf("\n\n Enroll Date:"));
        String EnrollDate = S.substring(S.indexOf("\n\n Enroll Date:") + 16);

        EmployeeType EmpType;
        if (ET.equals("AD"))
        {
            EmpType=EmployeeType.Admin;
        } else if (ET.equals("MG"))
        {
            EmpType=EmployeeType.Manager;
        } else if (ET.equals("DP"))
        {
            EmpType=EmployeeType.Dispatcher;
        } else if (ET.equals("DR"))
        {
            EmpType=EmployeeType.Driver;
        } else
        {
            EmpType=EmployeeType.Worker;
        }

        Emp.setName(Name);
        Emp.setDOB(DOB);
        Emp.setAddress(Address);
        Emp.setCNIC(CNIC);
        Emp.setPhoneNum(Phone);
        Emp.setEmpType(EmpType);
        Emp.setEnrollDate(EnrollDate);

        return Emp;
    }


}
