package com.example.se_project;

import android.location.Location;

public class PSI {
    private String name;
    private double latitude;
    private double longitude;
    private double psi_twenty_four_hourly;

    public PSI(String name, double longitude,double latitude,double pm25_one_hourly){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.psi_twenty_four_hourly = pm25_one_hourly;
    }

    public String getName(){
        String name2 = new String(name);
        return name2;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getPm25_one_hourly(){
        return psi_twenty_four_hourly;
    }


    public float distance (double latitude,double longitude){
        float[] results = new float[1];
        Location.distanceBetween(this.latitude, this.longitude,
                latitude, longitude,
                results);
        return results[0];
    }

    //debug
    public void print(){
        System.out.println("name: "+name + " " + "lat: " + latitude + " "+ "long: " + longitude+ " " + "PSI: " + psi_twenty_four_hourly);
    }
}
