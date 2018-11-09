package com.example.se_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;


public class Healthy_DietFragment extends Fragment {
    FrameLayout main_frame;
    ImageButton cook;
    ImageButton out;
    HealthierCaterersMap healthierCaterersMap;
    CookYourselfFragment cookYourselfFragment;
    public Healthy_DietFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_healthy__diet, container, false);
        main_frame = (FrameLayout)RootView.findViewById(R.id.main_frame);
        healthierCaterersMap = new HealthierCaterersMap();
        cookYourselfFragment = new CookYourselfFragment();
        cook = (ImageButton)RootView.findViewById(R.id.cook_yourself);
        out = (ImageButton)RootView.findViewById(R.id.dining_out);
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(cookYourselfFragment);
            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(healthierCaterersMap);
            }
        });
        return RootView;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

}
