package com.example.se_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PM25MapFragment extends Fragment implements OnMapReadyCallback,AsynTaskListener{
    SupportMapFragment mapFragment;
    GoogleMap mMap;
    ArrayList<PM25> pm25_list;

    public PM25MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_pm25_map, container, false);
        new GetPM25(PM25MapFragment.this,TaskType.GetPM25).execute();
        mapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return RootView;
    }

    public void onTaskCompleted(ArrayList<PM25> result, TaskType taskType){
        if (taskType == TaskType.GetPM25){
           pm25_list = result;
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (pm25_list != null) {
            //Log.e("successful", Integer.toString(pm25_list.size()));
            ArrayList<MarkerOptions> maker = new ArrayList<MarkerOptions>();
            for (int i = 0; i < pm25_list.size(); i++) {
                LatLng latLng = new LatLng(pm25_list.get(i).getLatitude(), pm25_list.get(i).getLongitude());
                //Log.e("Lat", Double.toString(pm25_list.get(i).getLatitude()));
                maker.add(new MarkerOptions().position(latLng).title(pm25_list.get(i).getName() + ": " + Double.toString(pm25_list.get(i).getPm25_one_hourly())));
                mMap.addMarker(new MarkerOptions().position(latLng).title(pm25_list.get(i).getName() + ": " + Double.toString(pm25_list.get(i).getPm25_one_hourly())));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


            }
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(maker.get(0).getPosition());
            builder.include(maker.get(1).getPosition());
            builder.include(maker.get(2).getPosition());
            builder.include(maker.get(3).getPosition());
            builder.include(maker.get(4).getPosition());
            LatLngBounds bounds = builder.build();

            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen

            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

            mMap.animateCamera(cu);
        }
    }
}
