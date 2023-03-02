package com.example.sda_project;

public class Driver extends Employee{
    private int DriverID;
    private int LicenseID;
    private boolean Availability;

    public int getLicenseID() {
        return LicenseID;
    }

    public void setLicenseID(int licenseID) {
        LicenseID = licenseID;
    }

    public boolean isAvailability() {
        return Availability;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }

    public int getDriverID() {
        return DriverID;
    }

    public void setDriverID(int driverID) {
        DriverID = driverID;
    }
}
