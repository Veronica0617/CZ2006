package com.example.se_project;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class Model {
    String title;
    String info1;
    String info2;
    int icon;
    double distance=-1,latitude, longitude;
    int num;

    public Model(String title,String info1,String info2,int icon)
    {
        this.title=title;
        this.info1=info1;
        this.info2=info2;
        this.icon=icon;
    }


    public Model(String title, String info1, String info2, double latitude, double longitude, int icon)
    {
        this.title=title;
        this.info1=info1;
        this.info2=info2;
        this.icon=icon;
        this.latitude=latitude;
        this.longitude=longitude;
        distance=calculateDistance(1.3407,103.6808);
    }

    public Model(String title,String info1,int num,int icon)
    {
        this.title=title;
        this.info1=info1;
        this.icon=icon;
        this.num=num;
    }


    //getters


    public int getNum() {
        return num;
    }

    public String getTitle() {
        return this.title;
    }

    public String getInfo1() {
        return this.info1;
    }

    public String getInfo2() {
        return this.info2;
    }

    public int getIcon() {
        return this.icon;
    }

    public float calculateDistance (double latitude,double longitude){
        float[] results = new float[1];
        Location.distanceBetween(this.latitude, this.longitude,
                latitude, longitude,
                results);
        return results[0];
    }
    public double getDistance() {
        return this.distance;
    }


}
