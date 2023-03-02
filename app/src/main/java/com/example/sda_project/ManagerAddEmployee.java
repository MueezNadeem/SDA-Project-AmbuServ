package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ManagerAddEmployee extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    final Calendar myCalendar= Calendar.getInstance();
    private EmployeeManager EM=new EmployeeManager();
    Spinner Dropdown;
    String[] options=new String[]{"Admin","Manager","Dispatcher","Driver"};
    EmployeeType Etype=null;
    EditText DOB;
    EditText DateOfBirth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_employee);
        Dropdown = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        Dropdown.setAdapter(adapter);
        Dropdown.setOnItemSelectedListener(this);

        DateOfBirth=(EditText) findViewById(R.id.AddEmpDOB);
        DateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR,-17); //set the date 17 years ago.
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(ManagerAddEmployee.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                DateOfBirth.setText(String.format("%d/%d/%d", dayOfMonth, monthOfYear + 1, year));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    public void AddEmployee(View view){
        //ToDo: Create Factory method for creation of employee if time allow
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Date CurrDateFormat=new Date();
        EditText Name=(EditText) findViewById(R.id.AddEmpName);
    //    EditText DOB=(EditText) findViewById(R.id.AddEmpDOB);
        EditText CNIC=(EditText) findViewById(R.id.AddEmpCNIC);
        EditText Address=(EditText) findViewById(R.id.AddEmpAddress);
        EditText Phone=(EditText) findViewById(R.id.AddEmpPhone);
        EditText Email=(EditText) findViewById(R.id.AddEmpEmail);
        EditText UserName=(EditText) findViewById(R.id.AddEmpUName);
        EditText Password=(EditText) findViewById(R.id.AddEmpPWord);
       // EditText Etype=(EditText) findViewById(R.id.AddEmpEType);

        if (IsValid(Name.getText().toString(), DateOfBirth.getText().toString(),CNIC.getText().toString(), Address.getText().toString(), Phone.getText().toString(), Email.getText().toString(), UserName.getText().toString(),Password.getText().toString())) {
            String dob_var = DateOfBirth.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String CurrDate=sdf.format(new Date());
            if (Etype == EmployeeType.Admin) {
                Admin x = new Admin();
                x.setName(Name.getText().toString());
                x.setDOB(dob_var);
                x.setCNIC(CNIC.getText().toString());
                x.setAddress(Address.getText().toString());
                x.setPhoneNum(Phone.getText().toString());
                x.setEmail(Email.getText().toString());
                x.setUserName(UserName.getText().toString());
                x.setPassword(Password.getText().toString());
                x.setEnrollDate(CurrDate);
                x.setEmpType(EmployeeType.Admin);
                EM.AddEmployeeToDB(x);

            } else if (Etype == EmployeeType.Manager) {
                Manager x = new Manager();
                x.setName(Name.getText().toString());
                x.setDOB(dob_var);
                x.setCNIC(CNIC.getText().toString());
                x.setAddress(Address.getText().toString());
                x.setPhoneNum(Phone.getText().toString());
                x.setEmail(Email.getText().toString());
                x.setUserName(UserName.getText().toString());
                x.setPassword(Password.getText().toString());
                x.setEnrollDate(CurrDate);
                x.setEmpType(EmployeeType.Manager);
                EM.AddEmployeeToDB(x);

            } else if (Etype == EmployeeType.Dispatcher) {
                Dispatcher x = new Dispatcher();
                x.setName(Name.getText().toString());
                x.setDOB(dob_var);
                x.setCNIC(CNIC.getText().toString());
                x.setAddress(Address.getText().toString());
                x.setPhoneNum(Phone.getText().toString());
                x.setEmail(Email.getText().toString());
                x.setUserName(UserName.getText().toString());
                x.setPassword(Password.getText().toString());
                x.setEnrollDate(CurrDate);
                x.setEmpType(EmployeeType.Dispatcher);

                EM.AddEmployeeToDB(x);

            } else if (Etype == EmployeeType.Worker) {
                Worker x = new Worker();
                x.setName(Name.getText().toString());
                x.setDOB(dob_var);
                x.setCNIC(CNIC.getText().toString());
                x.setAddress(Address.getText().toString());
                x.setPhoneNum(Phone.getText().toString());
                x.setEmail(Email.getText().toString());
                x.setUserName(UserName.getText().toString());
                x.setPassword(Password.getText().toString());
                x.setEnrollDate(CurrDate);
                x.setEmpType(EmployeeType.Worker);

                EM.AddEmployeeToDB(x);

            } else if (Etype == EmployeeType.Driver) {
                Driver x = new Driver();
                x.setName(Name.getText().toString());
                x.setDOB(dob_var);
                x.setCNIC(CNIC.getText().toString());
                x.setAddress(Address.getText().toString());
                x.setPhoneNum(Phone.getText().toString());
                x.setEmail(Email.getText().toString());
                x.setUserName(UserName.getText().toString());
                x.setPassword(Password.getText().toString());
                x.setEnrollDate(CurrDate);
                x.setEmpType(EmployeeType.Driver);

                EM.AddEmployeeToDB(x);

            }
            if (EM.CheckEmployeeExistenceByUN(UserName.getText().toString())) {
                Toast Success = Toast.makeText(getApplicationContext(), "SUCCESS!", Toast.LENGTH_SHORT);
                Success.show();
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                Etype=EmployeeType.Admin;
                break;
            case 1:
                Etype=EmployeeType.Manager;
                break;
            case 2:
                Etype=EmployeeType.Dispatcher;
                break;
            case 3:
                Etype=EmployeeType.Driver;
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        DOB.setText(dateFormat.format(myCalendar.getTime()));
    }


    public boolean IsValid(String Name, String DateofBirth, String CNIC, String HomeAdd, String Phone, String Email, String UserName, String Password)
    {
        Toast Fail;
        if (Name.isEmpty())
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Please Enter a Name in the Name field.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if (DateofBirth.isEmpty())
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Please Enter a Date in the DOB field.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        Date DCheck, OldDate;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            DCheck = df.parse(DateofBirth);
            OldDate = df.parse("1/1/1900");
        } catch (ParseException e) {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Date Format is Incorrect.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if (DCheck.before(OldDate))
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Please Select a date after the year 1899.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -17);
        if (DCheck.after(c.getTime()))
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Your DOB should be before the year " + c.get(1), Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if (CNIC.length() != 13)
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, CNIC should contain 13 digits.", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if (EM.CheckEmployeeExistenceByCNIC(CNIC))
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
        if (Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, InValid Email Address. Please Enter a Email Address like: example@domain.com", Toast.LENGTH_LONG);
            Fail.show();
            return false;
        }
        if(UserName.isEmpty() || EM.CheckEmployeeExistenceByUN(UserName))
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Same UserName already exists.", Toast.LENGTH_SHORT);
            Fail.show();
            return false;
        }
        if (Password.length() < 5)
        {
            Fail = Toast.makeText(getApplicationContext(), "Failed!, Password must have at least 5 characters.", Toast.LENGTH_SHORT);
            Fail.show();
            return false;
        }
        return true;
    }
}



