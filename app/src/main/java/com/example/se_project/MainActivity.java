package com.example.se_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsynTaskListener {
    public static TextView trial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hi
        trial = (TextView)findViewById(R.id.trial);
           //new GetPM25().execute();
            //GetPM25 pm25 = new GetPM25();
            //new GetPM25().execute();
            //trial.setText(pm25.list.get(1).getName());
            new GetPM25(MainActivity.this,TaskType.GetPM25).execute();

    }

    public void onTaskCompleted(ArrayList<PM25> result, TaskType taskType){
        if (taskType == TaskType.GetPM25){
            trial.setText(result.get(1).getName());
        }
    }

}
