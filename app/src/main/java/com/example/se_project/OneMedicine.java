package com.example.se_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class OneMedicine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_medicine);
        ArrayList<Therapeutic> therapeutics = new ArrayList<Therapeutic>();
        TextView tv1 =findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);
        TextView tv6 = findViewById(R.id.tv6);
        TextView tv7 = findViewById(R.id.tv7);
        TextView tv8 = findViewById(R.id.tv8);
        TextView tv9 = findViewById(R.id.tv9);
        TextView tv10 = findViewById(R.id.tv10);
        TextView tv11 = findViewById(R.id.tv11);
        TextView tv12 = findViewById(R.id.tv12);
        Intent intent =getIntent();
        String num=intent.getStringExtra("Number");
        int number=Integer.parseInt(num);
        InputStream is = getResources().openRawResource(R.raw.medicine);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            //Step over headers
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("~");
                Therapeutic t=new Therapeutic(tokens[0], tokens[1], tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8],tokens[9],tokens[10],tokens[11]);
                therapeutics.add(t);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        tv1.setText("Product Name: "+therapeutics.get(number).getProduct_name());
        tv2.setText("License No.: "+therapeutics.get(number).getLicense_no());
        tv3.setText("Manufacturer: "+therapeutics.get(number).getManufacturer());
        tv4.setText("Country of Manufacturer: "+therapeutics.get(number).getCountry_of_manufacturer());
        tv5.setText("Active Ingredients: "+therapeutics.get(number).getActive_ingredients());
        tv6.setText("Approval Date: "+therapeutics.get(number).getApproval_date());
        tv7.setText("Act Code: "+therapeutics.get(number).getAtc_code());
        tv8.setText("Dosage Form: "+therapeutics.get(number).getDosage_form());
        tv9.setText("Forensic Classification: "+therapeutics.get(number).getForensic_classification());
        tv10.setText("License Holder: "+therapeutics.get(number).getLicense_holder());
        tv11.setText("Route of Administration: "+therapeutics.get(number).getRoute_of_administration());
        tv12.setText("Strength: "+therapeutics.get(number).getStrength());


        //actionBar.setTitle(name);
    }

}
