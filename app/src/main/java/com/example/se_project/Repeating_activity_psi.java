package com.example.se_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Repeating_activity_psi extends AppCompatActivity implements AsyncTaskListenerPSI{
    TextView psi_index,psi_precaution,psi_safety_level;
    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeating_activity_psi_layout);
        Log.e("Repeating:","On");

        psi_index=(TextView)findViewById(R.id.psi_index);
        psi_precaution=(TextView)findViewById(R.id.psi_precaution);
        psi_safety_level=(TextView)findViewById(R.id.psi_safety_level);

        new GetPSI(Repeating_activity_psi.this,TaskType.GetPSI).execute();


    }

    public void onTaskCompletedPSI(ArrayList<PSI> result, TaskType taskType){
        if (taskType == TaskType.GetPSI){
            double latitude = 1.344456;
            double longitude = 103.680971;
            int i = 0;

            double min = result.get(0).distance(latitude,longitude);
            for (int j = 0 ; j < result.size();j++){
                if (min > result.get(j).distance(latitude,longitude)){
                    min = result.get(j).distance(latitude,longitude);
                    i = j;
                }
            }
            if (psi_safety_level != null) Log.e("Not null","k");
            Log.e("Pass","ll");
            String dex = "The current PSI index is " + Double.toString(result.get(i).getPsi_twenty_four_hourly_one_hourly()) + " which is " +result.get(i).safety_levels() ;
            psi_index.setText(dex);
            String con = "It is suitable for " + result.get(i).precaution();
            psi_safety_level.setText(con);
        }
    }

}
