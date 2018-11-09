package com.example.se_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements AsynTaskListener,AsyncTaskListenerPSI,AsyncTaskListenerUVI{
    TextView PM25;
    TextView PSI;
    TextView UVI;
    Button viewmap;
    FrameLayout main_frame;
    ViewMapFragment viewMapFragmentFragment;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_home, container, false);
        main_frame = (FrameLayout)RootView.findViewById(R.id.main_frame);
        viewMapFragmentFragment = new ViewMapFragment();
        viewmap = (Button)RootView.findViewById(R.id.view_map) ;
        viewmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(viewMapFragmentFragment);
            }
        });
        PM25 = (TextView) RootView.findViewById(R.id.pm25);
        PSI = (TextView)RootView.findViewById(R.id.psi);
        UVI = (TextView)RootView.findViewById(R.id.uvi);
        new GetPM25(HomeFragment.this,TaskType.GetPM25).execute();
        new GetPSI(HomeFragment.this,TaskType.GetPSI).execute();
        new GetUVI(HomeFragment.this,TaskType.GetUVI).execute();
        return RootView;

    }

    public void onTaskCompleted(ArrayList<PM25> result, TaskType taskType){
        if (taskType == TaskType.GetPM25){
            double latitude = 1.344456;
            double longitude = 103.948162;
            int i = 0;
            /*Get the nearest PM2.5 from the user location*/
            double min = result.get(0).distance(latitude,longitude);
            for (int j = 0 ; j < result.size();j++){
                if (min > result.get(j).distance(latitude,longitude)){
                    min = result.get(j).distance(latitude,longitude);
                    i = j;
                }
            }
            String text = "PM2.5 "+ result.get(i).getName() + ": " + Double.toString(result.get(i).getPm25_one_hourly());
            PM25.setText(text);
        }
    }

    public void onTaskCompletedPSI(ArrayList<PSI> result, TaskType taskType){
        if (taskType == TaskType.GetPSI){
            double latitude = 1.344456;
            double longitude = 103.948162;
            int i = 0;
            /*Get the nearest PM2.5 from the user location*/
            double min = result.get(0).distance(latitude,longitude);
            for (int j = 0 ; j < result.size();j++){
                if (min > result.get(j).distance(latitude,longitude)){
                    min = result.get(j).distance(latitude,longitude);
                    i = j;
                }
            }
            String text = "PSI " + result.get(i).getName() + ": " + Double.toString(result.get(i).getPsi_twenty_four_hourly_one_hourly());
            PSI.setText(text);

        }
    }

    public void onTaskCompletedUVI(UVI result, TaskType taskType){
        if (taskType == TaskType.GetUVI){
            String text = "UV Index value averaged :" + Double.toString(result.getUvi_index());
            UVI.setText(text);
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

}
