package com.example.se_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMedicineFragment extends Fragment {

    ListView lv;
    TextView name;
    SearchView sv;
    ListViewAdapter adapter;
    String []title;
    String [] description;
    int [] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();
    ArrayList<Therapeutic> therapeutics = new ArrayList<Therapeutic>();
    String a1,a2,a3;

    public SearchMedicineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_search_medicine, container, false);
        readMedicine();
        title=new String[]{"One","Two","Three","Four","Five"};
       description=new String[]{"123","234","345","456,","567"};
        icon=new int[]{R.drawable.medicine1,R.drawable.medicine2,R.drawable.medicine3,R.drawable.medicine4,R.drawable.medicine5,R.drawable.medicine6,
        R.drawable.medicine7,R.drawable.medicine8};
        fillData();
       lv= (ListView) RootView.findViewById(R.id.lv);
       for(int i=0;i<50;i++)
        {
            Random random = new Random();
            int rand =random.nextInt(8);
            Model model=new Model(therapeutics.get(i).getProduct_name(),therapeutics.get(i).getLicense_no(),i,icon[rand]);
            arrayList.add(model);
        }
        adapter=new ListViewAdapter(this.getContext(),arrayList);
        lv.setAdapter(adapter);

        sv = (SearchView)RootView.findViewById(R.id.sv);
      // name =( TextView) RootView.findViewById(R.id.name);
       //name.setText(a1);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String search) {
                adapter.filter(search);
                return false;
            }
        });
        return RootView;
    }
    private void fillData(){
        readMedicine();
       /*for(int i=0;i<therapeutics.size();i++)
        {
            Model model=new Model(therapeutics.get(i).getProduct_name(), therapeutics.get(i).getLicense_no(),icon[i/5]);
            arrayList.add(model);
        }*/
    }

    private void readMedicine() {
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
        /*
        try{
            reader.readLine();
            line = reader.readLine();
            a1=line;
            a2="";
            a3="";

        }catch (IOException e){
            e.printStackTrace();
        }*/

       /* String line = "";
        try {
            //Step over headers
            reader.readLine();
            line = reader.readLine();
            a1=line;
            a2="";
            a3="";*/
           // while ((line = reader.readLine()) != null) {
                //Split by ','
                /*String[] tokens = line.split(",");
                a1=tokens[0];
                a2=tokens[1];
                a3=tokens[2];*/
                //Read the data
                //Therapeutic t = new Therapeutic(tokens[0], tokens[1], tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8],tokens[9],tokens[10],tokens[11]);
                //trial2.setText(licensedPharmacy.getPharmacy_address());
                //therapeutics.add(t);
           // }
      /*  } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
