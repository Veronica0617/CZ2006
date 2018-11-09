package com.example.se_project;


import android.location.Address;
import android.location.Geocoder;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.kml.KmlLayer;


import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GymMapFragment extends Fragment implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    GoogleMap mMap;


    public GymMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_gym_map, container, false);
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

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            KmlLayer layer = new KmlLayer(mMap, R.raw.gym, getActivity().getApplicationContext());
            layer.addLayerToMap();
        } catch (XmlPullParserException e) {
            Log.e("Unsuccessful","sadddd");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Unsuccessful","sadddd");
            e.printStackTrace();
        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 15));
        //1.4590907, 103.8249025
        //LatLng north = new LatLng(1.362041, 104.031583);
        //LatLng south = new LatLng(1.370271, 103.651115);
        //LatLngBounds bounds = new LatLngBounds(north,south);
        //CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 0);
        //googleMap.animateCamera(cu);
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocationName("Singapore", 1);
                if(addresses.size() > 0) {
                double latitude= addresses.get(0).getLatitude();
                double longitude= addresses.get(0).getLongitude();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10));

                }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
