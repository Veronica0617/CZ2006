package com.example.se_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsynTaskListener,AsyncTaskListenerPSI,AsyncTaskListenerUVI{

    ArrayList<PM25> pm25_list;
    ArrayList<PSI> psi_list;
    ArrayList<LicensedPharmacy> licensedPharmacies = new ArrayList<LicensedPharmacy>();
    private BottomNavigationView bottomNavigationView;
    private FrameLayout main_frame;
    private HomeFragment homeFragment;
    private ExerciseFragment exerciseFragment;
    private SearchFragment searchFragment;
    private Healthy_DietFragment healthy_dietFragment;
    private NotificationFragment notificationFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        exerciseFragment= new ExerciseFragment();
        searchFragment = new SearchFragment();
        healthy_dietFragment = new Healthy_DietFragment();
        notificationFragment = new NotificationFragment();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.main_nav);
        main_frame = (FrameLayout)findViewById(R.id.main_frame);
        setFragment(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home : {
                        setFragment(homeFragment);
                        return true;
                    }

                    case R.id.exercise :{
                        setFragment(exerciseFragment);
                        return true;
                    }

                    case R.id.search :{
                        setFragment(searchFragment);
                        return true;
                    }

                    case R.id.healthy_diet :{
                        setFragment(healthy_dietFragment);
                        return true;
                    }

                    case R.id.notification :{
                        setFragment(notificationFragment);
                        return true;
                    }

                    default: return false;

                }
            }
        });


        //new GetPM25(MainActivity.this,TaskType.GetPM25).execute();
            //new GetPSI(MainActivity.this,TaskType.GetPSI).execute();
            //new GetUVI(MainActivity.this,TaskType.GetUVI).execute();
            readLicensedPharmacy();

    }

    public void onTaskCompleted(ArrayList<PM25> result, TaskType taskType){
        if (taskType == TaskType.GetPM25){
            //debug
            pm25_list = result;
        }
    }

    public void onTaskCompletedPSI(ArrayList<PSI> result, TaskType taskType){
        if (taskType == TaskType.GetPSI){
            //debug
            psi_list = result;
        }
    }

    public void onTaskCompletedUVI(UVI result, TaskType taskType){
        if (taskType == TaskType.GetUVI){
            //debug
        }
    }

    /*trial for getting CSV file (LicensedPharmacy)*/
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
        Geocoder gc = new Geocoder(this);
        /*trial to get the long & lat using geocoder*/
        /*try {
            List<Address>list = gc.getFromLocationName(licensedPharmacies.get(10).getPharmacy_address(),1);
            trial2.setText(Double.toString(list.get(0).getLongitude()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void setFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
    //jjjjjjjjkkkkkkk
}
