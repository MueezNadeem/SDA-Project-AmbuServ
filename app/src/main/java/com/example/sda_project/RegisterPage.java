package com.example.sda_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterPage extends AppCompatActivity {
   private AccountRegisterer AR=new AccountRegisterer();
   EditText DOB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        DOB=(EditText) findViewById(R.id.DateOfBirth);
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR,-17); //set the date 17 years ago.
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterPage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                DOB.setText(String.format("%d/%d/%d", dayOfMonth, monthOfYear + 1, year));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    public void RegAccount(View view){


        Patient Pat=new Patient();
        EditText Name=(EditText) findViewById(R.id.NameOfUser);
        EditText DOB=(EditText) findViewById(R.id.DateOfBirth);
        EditText Address=(EditText) findViewById(R.id.address);
        EditText CNIC=(EditText) findViewById(R.id.CNIC);
        EditText Phone=(EditText) findViewById(R.id.PhoneNum);
        EditText UName=(EditText) findViewById(R.id.UserName);
        EditText PWord=(EditText) findViewById(R.id.Password);
        EditText Email=(EditText) findViewById(R.id.Email);

        if (IsValid(Name.getText().toString(), DOB.getText().toString(),CNIC.getText().toString(), Address.getText().toString(), Phone.getText().toString(), Email.getText().toString(), UName.getText().toString(),PWord.getText().toString())) {

            Pat.setName(Name.getText().toString());
            Pat.setDOB(DOB.getText().toString());
            Pat.setAddress(Address.getText().toString());
            Pat.setCNIC(CNIC.getText().toString());
            Pat.setPhoneNum(Phone.getText().toString());
            Pat.setUserName(UName.getText().toString());
            Pat.setPassword(PWord.getText().toString());
            Pat.setEmail(Email.getText().toString());

            AR.CreateAccount(Pat);

            if (AR.CheckEmployeeExistenceByUN(UName.getText().toString())) {
                Toast Success = Toast.makeText(getApplicationContext(), "SUCCESS!", Toast.LENGTH_SHORT);
                Success.show();
            }
        }
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
        if (AR.CheckEmployeeExistenceByCNIC(CNIC))
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
        if(UserName.isEmpty() || AR.CheckEmployeeExistenceByUN(UserName))
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