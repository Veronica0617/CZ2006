package com.example.se_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List_activity extends AppCompatActivity {

    ArrayList<String> pharmacies = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView lv;
    SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activity);

        lv=(ListView)findViewById(R.id.lv);
        sv=(SearchView)findViewById(R.id.sv);

        fillData();

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,pharmacies);
        lv.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(List_activity.this, adapter.getItem(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillData()
    {

        pharmacies.add("apple");
        pharmacies.add("banana");
        pharmacies.add("ice");
        pharmacies.add("kiwi");
        pharmacies.add("orange");
        pharmacies.add("shit");
    }

}
