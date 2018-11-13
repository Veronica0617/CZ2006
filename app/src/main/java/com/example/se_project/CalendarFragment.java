package com.example.se_project;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import	java.util.Date;
import java.text.SimpleDateFormat;

import org.w3c.dom.Text;

import java.util.Calendar;


public class CalendarFragment extends Fragment {
    Button set_date,set_time,set_add;
    EditText sport_1;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    TimePickerDialog.OnTimeSetListener mTimeSetListener;
    int hour_1;
    int minute_1;
    int year_1;
    int month_1;
    int date_of_month_1;
    boolean already_date = false;
    boolean already_time = false;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        sport_1=(EditText) RootView.findViewById(R.id.sport);
        set_date=(Button)RootView.findViewById(R.id.set_date);
        set_time=(Button)RootView.findViewById(R.id.set_time);
        set_add = (Button)RootView.findViewById(R.id.add_remainder);
        set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), mTimeSetListener, hour, minute, DateFormat.is24HourFormat(getActivity()));
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });

        set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                year_1=year;
                month_1=month;
                date_of_month_1 = day;
                //Toast.makeText(getActivity(),"month_1: " + Integer.toString(month_1)+" Date: " + Integer.toString(date_of_month_1) ,Toast.LENGTH_LONG).show();
                already_date = true;
            }
        };

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour_1 = i;
                minute_1 = i1;
                already_time = true;
            }
        };

        set_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exercise = String.valueOf(sport_1.getText());
               if (already_date && already_time){
                   Date now = new Date();
                   SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                   //Calendar.set(year_1, month_1, date_of_month_1, hour_1, minute_1, 0);
                   Calendar cal = Calendar.getInstance();
                   cal.set(year_1, month_1, date_of_month_1, hour_1, minute_1, 0);
                   String time = Integer.toString(year_1)+"-"+Integer.toString(month_1)+"-"+Integer.toString(date_of_month_1)+" "+Integer.toString(hour_1)+":"+Integer.toString(minute_1)+":"+"00";
                   //Toast.makeText(getActivity(),time,Toast.LENGTH_LONG).show();
                   Date parsedDate = null;
                   try {
                       parsedDate = format.parse(time);
                       //Toast.makeText(getActivity(),parsedDate.toString()+" "+now.toString(),Toast.LENGTH_LONG).show();

                   } catch (ParseException e) {
                       e.printStackTrace();
                   }
                   long alertTime = cal.getTime().getTime() - now.getTime();
                   if (alertTime > 0) {
                       Toast.makeText(getActivity(), Long.toString(alertTime), Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(getActivity().getApplicationContext(), Notification_reciever_exercise.class);
                       intent.putExtra("exercise", exercise);
                       PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                       AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().getApplicationContext().ALARM_SERVICE);
                       alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + alertTime, pendingIntent);
                       Toast.makeText(getActivity(),"You exercise has been scheduled on " + cal.getTime().toString(),Toast.LENGTH_LONG).show();


                   }
                   else{
                       Toast.makeText(getActivity(),"Don't set event in the past",Toast.LENGTH_LONG).show();

                   }
               }
               else{
                   Toast.makeText(getActivity(),"Please set both date and time",Toast.LENGTH_LONG).show();

               }
            }
        });

        return RootView;
    }




}
