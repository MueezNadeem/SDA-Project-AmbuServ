package com.example.sda_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Patient_Order extends AppCompatActivity implements OnMapReadyCallback {
    //TODO: Add code for Order //DONE
    EditText EText;


    boolean isPermissionGranted;
    MapView  MV;
    private Patient CurrPatient;
    private LocationHandler LocHandler=new LocationHandler();
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_order);

        EText=(EditText) findViewById(R.id.LocTextBox);
        EText.setText("FAST-NUCES");

        MV=(MapView) findViewById(R.id.mapView);
        CurrPatient=(Patient) getIntent().getSerializableExtra("PatientClass");
        MV.getMapAsync(this);
        MV.onCreate(savedInstanceState);

    }

    public  void GetLoc(View view){
        String location=LocHandler.getLoc(this);
        EText.setText(location);
    }

    public void AddOrder(View view){
        String loc=EText.getText().toString();
        String nextTaskID=DB.getMaxTaskID();
        SQLiteDatabase db=DB.getWritableDatabase();
        ContentValues v1=new ContentValues();
        v1.put("TaskID",nextTaskID);
        v1.put("Name","Task"+nextTaskID);
        v1.put("Location",loc);
        v1.put("Status",0);
        v1.put("TaskID",nextTaskID);
        v1.put("PatientID",CurrPatient.getPatientID());
        v1.put("AmbulanceID", (String) null);
        v1.put("DriverID", (String) null);
        db.insert("Tasks",null,v1);
        db.close();
        //String cmd="INSERT INTO \"main\".\"Tasks\" (\"TaskID\", \"Name\", \"Location\", \"Status\", \"DriverID\", \"AmbulanceID\", \"PatientID\") VALUES ('"+nextTaskID+"', 'Task"+nextTaskID+"', '"+loc+"', '1', '1', '1', '"+String.valueOf( CurrPatient.getPatientID() )+"');";
        //DB.ExecuteSQLQuery(cmd);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(31.48150642111404, 74.30257146083095))
                .title("Marker"));
    }

    @Override
    protected  void onStart(){
        super.onStart();
        MV.onStart();
    }

    @Override
    protected  void onResume(){
        super.onResume();
        MV.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MV.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MV.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MV.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        MV.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        MV.onLowMemory();
    }
}