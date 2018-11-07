package com.example.se_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsynTaskListener,AsyncTaskListenerPSI,AsyncTaskListenerUVI{
    public static TextView trial;
    public static TextView trial2;
    ArrayList<PM25> pm25_list;
    ArrayList<PSI> psi_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hi
        trial = (TextView)findViewById(R.id.trial);
        trial2 = (TextView)findViewById(R.id.trial2);
           //new GetPM25().execute();
            //GetPM25 pm25 = new GetPM25();
            //new GetPM25().execute();
            //trial.setText(pm25.list.get(1).getName());
            new GetPM25(MainActivity.this,TaskType.GetPM25).execute();
            new GetPSI(MainActivity.this,TaskType.GetPSI).execute();
            new GetUVI(MainActivity.this,TaskType.GetUVI).execute();

    }

    public void onTaskCompleted(ArrayList<PM25> result, TaskType taskType){
        if (taskType == TaskType.GetPM25){
            //debug
            trial.setText(result.get(1).getName());
            pm25_list = result;
        }
    }

    public void onTaskCompletedPSI(ArrayList<PSI> result, TaskType taskType){
        if (taskType == TaskType.GetPSI){
            //debug
            //trial2.setText(Double.toString(result.get(1).getPsi_twenty_four_hourly_one_hourly()));
            psi_list = result;
        }
    }

    public void onTaskCompletedUVI(UVI result, TaskType taskType){
        if (taskType == TaskType.GetUVI){
            //debug
            trial2.setText(Double.toString(result.getUvi_index()));
        }
    }


}
