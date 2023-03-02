package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class EmployeeMarkAttendance extends AppCompatActivity {
    private AttendanceMarker AM=new AttendanceMarker();
    private  Employee CurrUser;
    TextView CurrStat;
    //TODO : Mark Attendance left // DONE!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_mark_attendance);
         CurrStat=(TextView) findViewById(R.id.CurrAttStatus);


        CurrUser=(Employee) getIntent().getSerializableExtra("EmpClass");
        String currStat=AM.CheckAttendanceStatus(   String.valueOf(CurrUser.getEmployeeID()) );
       CurrStat.setText(String.format("Current Status : %s", currStat));
        }

        public void MarkAtt(View view){
            if (Objects.equals(AM.CheckAttendanceStatus(String.valueOf(CurrUser.getEmployeeID())), "Present")){


            }else {
                if (AM.IsEmployeeCheckedIn( String.valueOf(CurrUser.getEmployeeID()) )){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        AM.AddTimeOut( String.valueOf(CurrUser.getEmployeeID()) );
                        Toast T1= Toast.makeText(this,"Timed Out!", Toast.LENGTH_SHORT);
                        T1.show();
                    }
                }else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        AM.AddTimeIn( String.valueOf(CurrUser.getEmployeeID()) );
                        Toast T1= Toast.makeText(this,"Timed In!", Toast.LENGTH_SHORT);
                        T1.show();
                    }

                }

            }
            CurrStat.setText(String.format("Current Status : %s", AM.CheckAttendanceStatus(String.valueOf(CurrUser.getEmployeeID()))));

        }
}