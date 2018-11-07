package com.example.se_project;

import android.location.Location;

public class PSI {
    private String name;
    private double latitude;
    private double longitude;
    private double psi_twenty_four_hourly;

    public PSI(String name, double longitude,double latitude,double psi_twenty_four_hourly){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.psi_twenty_four_hourly = psi_twenty_four_hourly;
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

    public double getPsi_twenty_four_hourly_one_hourly(){
        return psi_twenty_four_hourly;
    }

    /*https://blissair.com/psi-sg*/
    public String safety_levels(){
        if (psi_twenty_four_hourly<=50 ) return "Good";
        if (51<=psi_twenty_four_hourly && psi_twenty_four_hourly<=100) return "Moderate";
        if (101<=psi_twenty_four_hourly && psi_twenty_four_hourly<=200) return "Unhealthy";
        if (201<=psi_twenty_four_hourly && psi_twenty_four_hourly<=300) return "Very Unhealthy";
        if (psi_twenty_four_hourly>=300) return "Hazardous";
        return "impossible";
    }

    /*https://sg.theasianparent.com/singapore-haze-facts-and-precautions/*/
    public String precaution(){
        if (psi_twenty_four_hourly<=50 ) return "Normal activities";
        if (51<=psi_twenty_four_hourly && psi_twenty_four_hourly<=100) return "Normal activities";
        if (101<=psi_twenty_four_hourly && psi_twenty_four_hourly<=200) return "Reduce prolonged or strenuous outdoor physical exertion";
        if (201<=psi_twenty_four_hourly && psi_twenty_four_hourly<=300) return "Avoid prolonged or strenuous outdoor physical exertion";
        if (psi_twenty_four_hourly>=300) return "Minimise outdoor activity";
        return "impossible";
    }

    /*Cannot find the health effects of different level of PSI*/
    public String health_effects(){
        return "impossible";
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
