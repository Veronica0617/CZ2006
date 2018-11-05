package com.example.se_project;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetPM25 extends AsyncTask<Void, Void, Void> {
    private ArrayList<PM25> list = new ArrayList<PM25>();

    /*protected ArrayList<PM25> getlist(){
        //doInBackground();
        return list;
    }*/

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        String url = "https://api.data.gov.sg/v1/environment/pm25";
        String jsonStr = sh.makeServiceCall(url);

        Log.e(MainActivity.class.getSimpleName(),"Response from url"+jsonStr);
        if(jsonStr != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray region_metadata = jsonObject.getJSONArray("region_metadata");
                //System.out.println(region_metadata.length());
                for (int i = 0 ; i < region_metadata.length();i++){
                    JSONObject c = region_metadata.getJSONObject(i);
                    String name = c.getString("name");
                    JSONObject label_location = c.getJSONObject("label_location");
                    String latitude = label_location.getString("latitude");
                    String longitude = label_location.getString("longitude");
                    JSONArray d = jsonObject.getJSONArray("items");
                    JSONObject e = d.getJSONObject(0);
                    JSONObject read = e.getJSONObject("readings");
                    //System.out.println("HHHHHHHHHH: " + name);

                    JSONObject hour = read.getJSONObject("pm25_one_hourly");
                    String pm25_one_hourly;
                    switch (i){
                        case 0: {
                            pm25_one_hourly = hour.getString("west");
                            //System.out.println("HHHHHHHHHH: " + "WEST");
                            break;
                        }


                        case 1: {
                            pm25_one_hourly = hour.getString("east");
                            //System.out.println("HHHHHHHHHH: " + "EAST");

                            break;
                        }

                        case 2: {
                            pm25_one_hourly = hour.getString("central");
                            //System.out.println("HHHHHHHHHH: " + "CENTRAL");

                            break;
                        }


                        case 3: {
                            pm25_one_hourly = hour.getString("south");
                            //System.out.println("HHHHHHHHHH: " + "SOUTH");

                            break;
                        }


                        case 4: {
                            pm25_one_hourly = hour.getString("north");
                            //System.out.println("HHHHHHHHHH: " + "NORTH");

                            break;
                        }

                        default:{
                            pm25_one_hourly = "";
                        }
                    }

                    list.add(new PM25(name,Double.parseDouble(longitude),Double.parseDouble(latitude),Double.parseDouble(pm25_one_hourly)));
                    //System.out.println("HHHHHHHHHH: " + list.size());

                    //debug
                    /*for(int k = 0 ; k < list.size() ; i++){
                        list.get(k).print();
                    }*/

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            //debug
           /*for(int k = 0 ; k < list.size() ; k++){
               list.get(k).print();

               System.out.println("CAUTION: " + list.get(k).precaution());
            }*/
        return null;
    }
    @Override

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.trial.setText(list.get(1).getName());

    }

}

