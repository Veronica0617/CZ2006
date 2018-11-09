package com.example.se_project;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewMapFragment extends Fragment {
    FrameLayout home_frame;
    BottomNavigationView nav_map;
    PM25MapFragment pm25MapFragment;
    PSIMapFragment psiMapFragment;
    GymMapFragment gymMapFragment;
    HealthierCaterersMap healthierCaterersMap;

    public ViewMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_view_map, container, false);
        nav_map = (BottomNavigationView)RootView.findViewById(R.id.map_nav);
        home_frame = (FrameLayout)RootView.findViewById(R.id.view_map);
        pm25MapFragment = new PM25MapFragment();
        psiMapFragment = new PSIMapFragment();
        gymMapFragment = new GymMapFragment();
        healthierCaterersMap = new HealthierCaterersMap();
        setFragment(pm25MapFragment);
        nav_map.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_pm25 : {
                        setFragment(pm25MapFragment);
                        return true;
                    }

                    case R.id.nav_psi :{
                        setFragment(psiMapFragment);
                        return true;
                    }

                    case R.id.nav_gym :{
                        setFragment(gymMapFragment);
                        return true;
                    }

                    case R.id.nav_healthier_cateriers :{
                        setFragment(healthierCaterersMap);
                        return true;
                    }


                    default: return false;

                }
            }
        });
        return RootView;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_frame,fragment);
        fragmentTransaction.commit();
    }

}
