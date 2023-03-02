package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EmployeeViewProfile extends AppCompatActivity {
    //TODO: add code // Done!
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view_profile);
        User CurrUser=(User) getIntent().getSerializableExtra("EmpClass");
        TextView Name=(TextView) findViewById(R.id.ProfName);
        TextView DOB=(TextView) findViewById(R.id.ProfDOB);
        TextView Add=(TextView) findViewById(R.id.ProfAdd);
        TextView CNIC=(TextView) findViewById(R.id.ProfCNIC);
        TextView PhoneNum=(TextView) findViewById(R.id.ProfPhone);
        TextView EnrollDate=(TextView) findViewById(R.id.ProfEnroll);


        Name.setText("Name: "+CurrUser.getName());
        DOB.setText("DOB: "+CurrUser.getDOB());
        CNIC.setText("CNIC: "+CurrUser.getCNIC());
        PhoneNum.setText("Phone: "+CurrUser.getPhoneNum());
        Add.setText("Address: "+CurrUser.getAddress());
        EnrollDate.setText("Enrolled: "+CurrUser.getEnrollDate());

    }
}