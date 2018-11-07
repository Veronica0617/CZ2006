package com.example.se_project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetPSI extends AsyncTask<Void,Void,ArrayList<PSI>> {
    ArrayList<PSI> list = new ArrayList<>();
    AsyncTaskListenerPSI taskListener;
    TaskType taskType;

    public GetPSI( AsyncTaskListenerPSI taskListener, TaskType taskType){
        this.taskListener = taskListener;
        this.taskType = taskType;
    }

    @Override
    protected ArrayList<PSI> doInBackground(Void...voids) {
        HttpHandler sh = new HttpHandler();
        String url = "https://api.data.gov.sg/v1/environment/psi";
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

                    JSONObject hour = read.getJSONObject("psi_twenty_four_hourly");
                    String psi_twenty_four_hourly;
                    switch (i){
                        case 0: {
                            psi_twenty_four_hourly = hour.getString("west");
                            //System.out.println("HHHHHHHHHH: " + "WEST");
                            break;
                        }

                        case 1: {
                            psi_twenty_four_hourly = hour.getString("national");
                            //System.out.println("HHHHHHHHHH: " + "WEST");
                            break;
                        }

                        case 2: {
                            psi_twenty_four_hourly = hour.getString("east");
                            //System.out.println("HHHHHHHHHH: " + "EAST");

                            break;
                        }

                        case 3: {
                            psi_twenty_four_hourly = hour.getString("central");
                            //System.out.println("HHHHHHHHHH: " + "CENTRAL");

                            break;
                        }


                        case 4: {
                            psi_twenty_four_hourly = hour.getString("south");
                            //System.out.println("HHHHHHHHHH: " + "SOUTH");

                            break;
                        }


                        case 5: {
                            psi_twenty_four_hourly = hour.getString("north");
                            //System.out.println("HHHHHHHHHH: " + "NORTH");

                            break;
                        }

                        default:{
                            psi_twenty_four_hourly = "";
                        }
                    }

                    list.add(new PSI(name,Double.parseDouble(longitude),Double.parseDouble(latitude),Double.parseDouble(psi_twenty_four_hourly)));
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
        return list;
    }
    @Override

    protected void onPostExecute(ArrayList<PSI> result) {
        super.onPostExecute(result);
        taskListener.onTaskCompletedPSI(result, taskType);
        //MainActivity.trial.setText(list.get(1).getName());

    }
}
