package com.example.se_project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetUVI extends AsyncTask<Void,Void,UVI> {
    UVI uvi;
    AsyncTaskListenerUVI taskListener;
    TaskType taskType;

    public GetUVI( AsyncTaskListenerUVI taskListener, TaskType taskType){
        this.taskListener = taskListener;
        this.taskType = taskType;
    }

    @Override
    protected UVI doInBackground(Void...voids) {
        HttpHandler sh = new HttpHandler();
        String url = "https://api.data.gov.sg/v1/environment/uv-index";
        String jsonStr = sh.makeServiceCall(url);

        Log.e(MainActivity.class.getSimpleName(),"Response from url"+jsonStr);
        if(jsonStr != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray items = jsonObject.getJSONArray("items");
                JSONObject first = items.getJSONObject(0);
                JSONArray index = first.getJSONArray("index");
                JSONObject inside = index.getJSONObject(0);
                String value = inside.getString("value");
                uvi = new UVI(Double.parseDouble(value));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return uvi;
    }
    @Override

    protected void onPostExecute(UVI result) {
        super.onPostExecute(result);
        taskListener.onTaskCompletedUVI(result, taskType);

    }

}
