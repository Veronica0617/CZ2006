package com.example.se_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class Repeating_activity_uvi extends AppCompatActivity implements AsyncTaskListenerUVI{
    TextView index,uvi_precaution,uvi_health;
    protected  void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeating_activity_uvi_layout);
        Log.e("Repeating:","On");
        index = (TextView)findViewById(R.id.uvi_index);
        uvi_precaution=(TextView)findViewById(R.id.uvi_precaution);
        uvi_health=(TextView)findViewById(R.id.uvi_health_effect);

        new GetUVI(Repeating_activity_uvi.this,TaskType.GetUVI).execute();


    }

    public void onTaskCompletedUVI(UVI result, TaskType taskType){
        if (taskType == TaskType.GetUVI){
            //kk
            Log.e("UVI he","kkkk");
            String text = "UV Index value averaged " + Double.toString(result.getUvi_index()) + " which is " + result.safety_levels();
            index.setText(text);
            uvi_precaution.setText(result.precaution());
            uvi_health.setText(result.health_effects());
        }
    }
}
