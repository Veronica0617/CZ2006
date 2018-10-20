package com.example.se_project;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class PM25 {
    private String name;
    private double latitude;
    private double longitude;
    private double pm25_one_hourly;

    public PM25(String name, double longitude,double latitude,double pm25_one_hourly){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pm25_one_hourly = pm25_one_hourly;
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
        return pm25_one_hourly;
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
        System.out.println("name: "+name + " " + "lat: " + latitude + " "+ "long: " + longitude+ " " + "pm25: " + pm25_one_hourly);
    }

    //https://blissair.com/pm25-sg
    public String safety_levels(){
       if (pm25_one_hourly <=12 ) return "Good";
       if (12.1<=pm25_one_hourly && pm25_one_hourly<=35.4) return "Moderate";
       if (35.5<=pm25_one_hourly && pm25_one_hourly<=55.4) return "Unhealthy for sensitive Groups";
       if (55.5<=pm25_one_hourly && pm25_one_hourly<=150.4) return "Unhealthy";
       if (150.5<=pm25_one_hourly && pm25_one_hourly<=250.4) return "Very Unhealthy";
       if (250.5<=pm25_one_hourly && pm25_one_hourly<=500.4) return "Hazardous";
       return "impossible";
    }

    //https://blissair.com/what-is-pm-2-5.htm
    public String precaution(){
        if (pm25_one_hourly <=12 ) return "None";
        if (12.1<=pm25_one_hourly && pm25_one_hourly<=35.4) return "Unusually sensitive people should consider reducing prolonged or heavy exertion.";
        if (35.5<=pm25_one_hourly && pm25_one_hourly<=55.4) return "People with respiratory or heart disease, the elderly and children should limit prolonged exertion.";
        if (55.5<=pm25_one_hourly && pm25_one_hourly<=150.4) return "People with respiratory or heart disease, the elderly and children should avoid prolonged exertion; everyone else should limit prolonged exertion.";
        if (150.5<=pm25_one_hourly && pm25_one_hourly<=250.4) return "People with respiratory or heart disease, the elderly and children should avoid any outdoor activity; everyone else should avoid prolonged exertion.";
        if (250.5<=pm25_one_hourly && pm25_one_hourly<=500.4) return "Everyone should avoid any outdoor exertion; people with respiratory or heart disease, the elderly and children should remain indoors.";
        return "impossible";
    }

    public String health_effects(){
        if (pm25_one_hourly <=12 ) return "Little to no risk.";
        if (12.1<=pm25_one_hourly && pm25_one_hourly<=35.4) return "Unusually sensitive individuals may experience respiratory symptoms.";
        if (35.5<=pm25_one_hourly && pm25_one_hourly<=55.4) return "Increasing likelihood of respiratory symptoms in sensitive individuals, aggravation of heart or lung disease and premature mortality in persons with cardiopulmonary disease and the elderly.";
        if (55.5<=pm25_one_hourly && pm25_one_hourly<=150.4) return "Increased aggravation of heart or lung disease and premature mortality in persons with cardiopulmonary disease and the elderly; increased respiratory effects in general population.";
        if (150.5<=pm25_one_hourly && pm25_one_hourly<=250.4) return "Significant aggravation of heart or lung disease and premature mortality in persons with cardiopulmonary disease and the elderly; significant increase in respiratory effects in general population.";
        if (250.5<=pm25_one_hourly && pm25_one_hourly<=500.4) return "Serious aggravation of heart or lung disease and premature mortality in persons with cardiopulmonary disease and the elderly; serious risk of respiratory effects in general population.";
        return "impossible";
    }


}

