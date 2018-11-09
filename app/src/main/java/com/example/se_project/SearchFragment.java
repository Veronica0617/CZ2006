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


public class SearchFragment extends Fragment {
    FrameLayout main_frame;
    ImageButton pharmacy;
    ImageButton medicine;
    SearchPharmaciesFragment searchPharmaciesFragment;
    SearchMedicineFragment searchMedicineFragment;
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_search, container, false);
        main_frame = (FrameLayout)RootView.findViewById(R.id.main_frame);
        pharmacy = (ImageButton)RootView.findViewById(R.id.search_pharmacies);
        medicine = (ImageButton)RootView.findViewById(R.id.search_medicine);
        searchPharmaciesFragment = new SearchPharmaciesFragment();
        searchMedicineFragment = new SearchMedicineFragment();
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(searchPharmaciesFragment);
            }
        });
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(searchMedicineFragment);
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
