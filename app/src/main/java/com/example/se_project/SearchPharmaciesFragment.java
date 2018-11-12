package com.example.se_project;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchPharmaciesFragment extends Fragment {
    ListView lv;
    SearchView sv;
    ListViewAdapter adapter;
    String []title;
    String [] description;
    int [] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();
    ArrayList<LicensedPharmacy> licensedPharmacies = new ArrayList<LicensedPharmacy>();


    public SearchPharmaciesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View RootView = inflater.inflate(R.layout.fragment_search_pharmacies, container, false);

        /*title=new String[]{"One","Two","Three","Four","Five"};
        description=new String[]{"123","234","345","456,","567"};*/
        icon=new int[]{R.drawable.clinic1,R.drawable.clinic2,R.drawable.clinic3,R.drawable.clinic4,R.drawable.clinic5};
        lv= (ListView) RootView.findViewById(R.id.lv);
/*
        for(int i=0;i<title.length;i++)
        {
            Model model=new Model(title[i],description[i],icon[i]);
            arrayList.add(model);
        }*/

        //pass result to listView
        fillData();

        adapter=new ListViewAdapter(this.getContext(),arrayList);
        lv.setAdapter(adapter);

        sv = (SearchView)RootView.findViewById(R.id.sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String search) {
                adapter.filter(search);
                return false;
            }
        });


        return RootView;
    }

    private void fillData()
    {
        readLicensedPharmacy();
        for(int i=0;i<licensedPharmacies.size();i++)
        {
            String a="null";
            double latitude=0;
            double longitude=0;
            if(i==0|| i==1|| i==48 || i==50 ||i == 51 || (i>=51 && i<=54) || i==124  || i ==125 || i==173 || i == 184 || i== 206 || i==237)
            {

                String x=licensedPharmacies.get(i).getPharmacy_address();
                Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());
                List<Address> addresses;
                try {
                    addresses = geocoder.getFromLocationName(x, 1);
                    if(addresses.size() > 0) {
                        latitude= addresses.get(0).getLatitude();
                        longitude= addresses.get(0).getLongitude();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                Random random = new Random();
                int randLat =random.nextInt(10);
                Random random2 = new Random();
                int randLon =random2.nextInt(10);
                double[]latArray=new double[]{1.36779479,1.42147456,1.31020097,1.28864584,1.3174775,1.4053433,1.35605647,1.29976667,1.32104711,1.27093481};
                double[]lonArray=new double[]{103.72683298,103.78718912,103.82358133,103.84692728,103.87576639,103.91147196,103.9437443,103.85242044,103.81602823,103.82564127};
                latitude = latArray[randLat];
                longitude =lonArray[randLon];
            }
            Model model=new Model(licensedPharmacies.get(i).getPharmacy_name(),licensedPharmacies.get(i).getPharmacist_in_charge(),licensedPharmacies.get(i).getPharmacy_address(),latitude,longitude,icon[i%5]);
            arrayList.add(model);

        }
        Model nearest=arrayList.get(0);
        double min=arrayList.get(0).getDistance();
        for(int i =0;i<arrayList.size();i++)
        {
            if(arrayList.get(i).getDistance()<min)
            {
                min=arrayList.get(i).getDistance();
                nearest=arrayList.get(i);
            }
        }
        arrayList.set(0,nearest);
    }

    private void readLicensedPharmacy(){
        InputStream is = getResources().openRawResource(R.raw.listingoflicensedpharmacies);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line="";
        try {
            //Step over headers
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                //Split by ','
                String[] tokens = line.split(",(?=([^\"]|\"[^\"]*\")*$)");

                //Read the data
                LicensedPharmacy licensedPharmacy = new LicensedPharmacy(tokens[0],tokens[1],tokens[2]);
                //trial2.setText(licensedPharmacy.getPharmacy_address());
                licensedPharmacies.add(licensedPharmacy);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //trial2.setText(licensedPharmacies.get(10).getPharmacy_address());
        //Geocoder gc = new Geocoder(this);
        /*trial to get the long & lat using geocoder*/
        /*try {
            List<Address>list = gc.getFromLocationName(licensedPharmacies.get(10).getPharmacy_address(),1);
            trial2.setText(Double.toString(list.get(0).getLongitude()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*private double calculateDistance(){
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocationName("Singapore", 1);
            if(addresses.size() > 0) {
                double latitude= addresses.get(0).getLatitude();
                double longitude= addresses.get(0).getLongitude();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    /*trial to get the long & lat using geocoder*/
        /*try {
            List<Address>list = gc.getFromLocationName(licensedPharmacies.get(10).getPharmacy_address(),1);
            trial2.setText(Double.toString(list.get(0).getLongitude()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
