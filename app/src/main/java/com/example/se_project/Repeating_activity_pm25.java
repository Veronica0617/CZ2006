package com.example.se_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Repeating_activity_pm25 extends AppCompatActivity implements AsynTaskListener{

    //ArrayList<PM25> pm25_list;
    TextView index,precaution,safety_level,health_risk;
    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeating_activity_pm25_layout);
        Log.e("Repeating:","On");

        index=(TextView)findViewById(R.id.pm25_index);
        precaution=(TextView)findViewById(R.id.pm25_precaution);
        safety_level=(TextView)findViewById(R.id.pm25_safety_levels);
        health_risk=(TextView)findViewById(R.id.pm25_health_effect);

        new GetPM25(Repeating_activity_pm25.this,TaskType.GetPM25).execute();
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
            String dex = "Current PM2.5 index is " + Double.toString(result.get(i).getPm25_one_hourly());
            index.setText(dex);
            Log.e("HI",result.get(i).precaution());
            String o = "Precaution: " + result.get(i).precaution();
            precaution.setText(o);
            String k = "Safety level: " + result.get(i).safety_levels();
            safety_level.setText(k);
            String p = "Health risk :" + result.get(i).health_effects();
            health_risk.setText(result.get(i).health_effects());

        }
    }
}
