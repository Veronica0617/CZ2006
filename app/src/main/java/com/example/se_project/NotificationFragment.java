package com.example.se_project;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.Calendar;


public class NotificationFragment extends Fragment {

    SwitchCompat alertpm25,alertpsi,alertuvi;
    boolean state_pm25,state_psi,state_uvi;
    //SharedPreferences preferences;
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_notification, container, false);
        //final SharedPreferences preferences = getActivity().getSharedPreferences("PRES",0);
        //state_pm25 = preferences.getBoolean("alertpm25",false);
        //state_psi = preferences.getBoolean("alertpsi",false);
        //state_uvi = preferences.getBoolean("alertuvi",false);
        alertpm25 = (SwitchCompat)RootView.findViewById(R.id.alertPM25);
        //alertpm25.setChecked(false);
        alertpm25.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (alertpm25.isChecked()) {
                    Log.e("Alert PM25","On");
                    Intent intent = new Intent(getActivity().getApplicationContext(),Notification_reciever_pm25.class);
                    intent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent =PendingIntent.getBroadcast(getActivity().getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT
                    );
                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().getApplicationContext().ALARM_SERVICE);
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime() + 1*60*1000,
                            1*60*1000, pendingIntent);

                    Toast.makeText(getActivity(),"Alert PM2.5 has been set. It will alert you every hour",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getActivity().getApplicationContext(),Notification_reciever_pm25.class);
                    intent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent =PendingIntent.getBroadcast(getActivity().getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT
                    );
                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().getApplicationContext().ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(getActivity(),"Alert PM2.5 has been disabled",Toast.LENGTH_LONG).show();
                }
            }
        });

        alertpsi = (SwitchCompat)RootView.findViewById(R.id.alertPSI);
        alertpsi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (alertpsi.isChecked()) {
                    Log.e("Alert PSI","On");
                    Intent intent = new Intent(getActivity().getApplicationContext(),Notification_reciever_psi.class);
                    intent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent =PendingIntent.getBroadcast(getActivity().getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT
                    );
                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().getApplicationContext().ALARM_SERVICE);
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime() + 1*60*1000,
                            1*60*1000, pendingIntent);

                    Toast.makeText(getActivity(),"Alert PSI has been set. It will alert you every hour",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getActivity().getApplicationContext(),Notification_reciever_psi.class);
                    intent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent =PendingIntent.getBroadcast(getActivity().getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT
                    );
                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().getApplicationContext().ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(getActivity(),"Alert PSI has been disabled",Toast.LENGTH_LONG).show();

                }
            }
        });
        alertuvi = (SwitchCompat)RootView.findViewById(R.id.alertUVI);
        alertuvi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (alertuvi.isChecked()) {
                    Log.e("Alert UVI","On");
                    Intent intent = new Intent(getActivity().getApplicationContext(),Notification_reciever_uvi.class);
                    intent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent =PendingIntent.getBroadcast(getActivity().getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT
                    );
                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().getApplicationContext().ALARM_SERVICE);

                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime() + 1*60*1000,
                            1*60*1000, pendingIntent);

                    Toast.makeText(getActivity(),"Alert UVI has been set. It will alert you every hour",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getActivity().getApplicationContext(),Notification_reciever_uvi.class);
                    intent.setAction("MY_NOTIFICATION_MESSAGE");
                    PendingIntent pendingIntent =PendingIntent.getBroadcast(getActivity().getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT
                    );
                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().getApplicationContext().ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(getActivity(),"Alert UVI has been disabled",Toast.LENGTH_LONG).show();

                }
            }
        });

        return RootView;
    }

}
