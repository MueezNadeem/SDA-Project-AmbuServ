package com.example.sda_project;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationHandler  {
    private FusedLocationProviderClient FLPC;
    Activity a;
    public String getLoc(Activity act){
        a=act;
    FLPC= LocationServices.getFusedLocationProviderClient(a);

    if (ActivityCompat.checkSelfPermission(a, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
        return getLocation();
    }else{
        ActivityCompat.requestPermissions(a,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
    }
        return  null;
    }

    @SuppressLint("MissingPermission")
    private  String getLocation(){
        final String[] ans = {null};
        FLPC.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location loc =task.getResult();
                if (loc!=null){
                    Geocoder geocoder= new Geocoder(a, Locale.getDefault());
                    try {
                        List<Address> addresses= geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                       ans[0] = addresses.get(0).getAddressLine(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return ans[0];
    }
}
