package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class SignInPage extends AppCompatActivity {
    EditText t1;
    EditText t2;
    DatabaseHelper myDB;
    DatabaseClass myC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        myDB= new DatabaseHelper(SignInPage.this);
//        try {
//            myDB.createDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        myDB.openDataBase();
//
        myC=new DatabaseClass(this);
        setContentView(R.layout.signinpage);
        t1=(EditText) findViewById(R.id.UName);
        t2=(EditText) findViewById(R.id.PWord);
    }

//TODO add functions // Done

    public void sendmsg(View view){
//<<<<<<< Updated upstream
//        boolean ans=myDB.CheckUserName(t1.getText().toString(),t2.getText().toString());
//        TextView a=(TextView) findViewById(R.id.Results);
//        String abc=new Boolean(ans).toString();
//        a.setText(abc);
//=======
//        boolean ans=myDB.CheckUserName(t1.getText().toString(),t2.getText().toString());
//        TextView a=(TextView) findViewById(R.id.Results);
//        String abc=Boolean.valueOf(ans).toString();
//        a.setText(abc);
        LoginManager LM=new LoginManager();
        if (LM.VerifyCredentials(t2.getText().toString(),t1.getText().toString())==true){
            AccountType UserType=LM.getTypeUser(t1.getText().toString());

            if (UserType==AccountType.Consumer){
                Patient CurrentUser=LM.GetPatient(t1.getText().toString());
                Intent PatientMenu=new Intent(this,Patient_Homepage.class);
                PatientMenu.putExtra("PatientClass",CurrentUser);
                startActivity(PatientMenu);
            }else if (UserType==AccountType.Employee) {
                EmployeeType EmpType= LM.getEmpType(t1.getText().toString());

                if (EmpType==EmployeeType.Admin) {
                        Admin CurrentAdmin =new Admin();
                    CurrentAdmin=LM.GetAdmin(t1.getText().toString());
                        Intent AdminMenu = new Intent(this, ManagerHomePage.class);
                        AdminMenu.putExtra("ManagerClass",CurrentAdmin);
                        startActivity(AdminMenu);
                }

                else if (EmpType==EmployeeType.Driver){
                Driver CurrentDriver=LM.GetDriver(t1.getText().toString());
                        Intent DriverMenu=new Intent(this, DriverHomepage.class);
                        DriverMenu.putExtra("DriverClass",CurrentDriver);
                        startActivity(DriverMenu);
                }
                     // case Worker:Worker CurrentWorker=LM.GetWorker(t1.getText().toString());
//                        Intent WorkerMenu=new Intent(this, DriverHomepage.class);
//                        WorkerMenu.putExtra("WorkerClass",CurrentWorker);
//                        break;
                else if (EmpType==EmployeeType.Manager){
                    Manager CurrentManager=LM.GetManager(t1.getText().toString());
                        Intent ManagerMenu=new Intent(this,ManagerHomePage.class);
                        ManagerMenu.putExtra("ManagerClass",CurrentManager);
                        startActivity(ManagerMenu);
                } else if (EmpType==EmployeeType.Dispatcher){
                   Dispatcher CurrentDispatcher=LM.GetDispatcher(t1.getText().toString());
                        Intent DispatcherMenu=new Intent(this,DispatcherHomePage.class);
                        DispatcherMenu.putExtra("DispatcherClass",CurrentDispatcher);
                        startActivity(DispatcherMenu);
                }
            }
        }else {
            Toast ErrMsg=Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT);
            ErrMsg.show();

        }

    }

    public void RegisterAccount(View view){
        Intent RegisterMenu = new Intent(this, RegisterPage.class);
        startActivity(RegisterMenu);

    }

}