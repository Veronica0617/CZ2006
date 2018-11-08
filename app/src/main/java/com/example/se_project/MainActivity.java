package com.example.se_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsynTaskListener,AsyncTaskListenerPSI,AsyncTaskListenerUVI{
    public static TextView trial;
    public static TextView trial2;
    ArrayList<PM25> pm25_list;
    ArrayList<PSI> psi_list;
    ArrayList<LicensedPharmacy> licensedPharmacies = new ArrayList<LicensedPharmacy>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hi
        trial = (TextView)findViewById(R.id.trial);
        trial2 = (TextView)findViewById(R.id.trial2);

            new GetPM25(MainActivity.this,TaskType.GetPM25).execute();
            new GetPSI(MainActivity.this,TaskType.GetPSI).execute();
            new GetUVI(MainActivity.this,TaskType.GetUVI).execute();
            readLicensedPharmacy();

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
            //trial2.setText(Double.toString(result.getUvi_index()));
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
                trial2.setText(licensedPharmacy.getPharmacy_address());
                licensedPharmacies.add(licensedPharmacy);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @author Veronica
     * The button, we can see the search interface alfter click it.
     */
    public void onClick(View view)
    {
        try
        {
            //startActivity(new Intent("com.AndroidTest.SecondActivity"));//隐式intent
            Intent intent = new Intent(this, List_activity.class);//显示intent
            startActivity(intent);
        }
        catch (Exception ex)
        {
            // 显示异常信息
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String getList(int i) {
        return pm25_list.get(i).getName();
    }
}
