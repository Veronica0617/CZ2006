package com.example.se_project;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.transform.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class CookYourselfFragment extends Fragment {
    ImageButton beverage;
    TextView beverageText,title;
    String [] listitem ;
    boolean [] checkedItems;
    ArrayList<Integer>mUserItems=new ArrayList<>();
    ArrayList<Food>beverageList = new ArrayList<>();
    static String theText ="Empty\n";

    ImageButton cereal;
    TextView cerealText;
    String [] listCereal ;
    boolean [] checkedCereal;
    ArrayList<Integer>theCereal=new ArrayList<>();
    ArrayList<Food>cerealList = new ArrayList<>();
    static String theTextCereal ="Empty\n";
    String a;

    ImageButton diary;
    TextView diaryText;
    String [] listDiary ;
    boolean [] checkedDiary;
    ArrayList<Integer>theDiary=new ArrayList<>();
    ArrayList<Food>diaryList = new ArrayList<>();
    static String theTextDiary ="Empty\n";

    ImageButton egg;
    TextView eggText;
    String [] listEgg ;
    boolean [] checkedEgg;
    ArrayList<Integer>theEgg=new ArrayList<>();
    ArrayList<Food>eggList = new ArrayList<>();
    static String theTextEgg ="Empty\n";

    ImageButton fruit;
    TextView fruitText;
    String [] listFruit ;
    boolean [] checkedFruit;
    ArrayList<Integer>theFruit=new ArrayList<>();
    ArrayList<Food>fruitList = new ArrayList<>();
    static String theTextFruit ="Empty\n";

    ImageButton meat;
    TextView meatText;
    String [] listMeat ;
    boolean [] checkedMeat;
    ArrayList<Integer>theMeat=new ArrayList<>();
    ArrayList<Food>meatList = new ArrayList<>();
    static String theTextMeat ="Empty\n";

    ImageButton seafood;
    TextView seafoodText;
    String [] listSeafood ;
    boolean [] checkedSeafood;
    ArrayList<Integer>theSeafood=new ArrayList<>();
    ArrayList<Food>seafoodList = new ArrayList<>();
    static String theTextSeafood ="Empty\n";

    public CookYourselfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_cook_yourself, container, false);
        beverage=(ImageButton)RootView.findViewById(R.id.beverageButton);
        beverageText=(TextView)RootView.findViewById(R.id.beverageContent);
        title=(TextView) RootView.findViewById(R.id.title);
        //listitem= new String[]{"apple","orange","watermelon","peach","kiwi","tomato","coffee"};
        readBeverage();
        readCereal();
        readDiary();
        readEgg();
        readFruit();
        readMeat();
        readSeafood();
        listitem=new String[45];
        checkedItems=new boolean[listitem.length];
       /* String thing="";
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        beverageText.setText(theText);
        title.setText("My Healthy Food List");

        cereal=(ImageButton)RootView.findViewById(R.id.cerealButton);
        cerealText=(TextView)RootView.findViewById(R.id.cerealContent);

        listCereal=new String[40];
        checkedCereal=new boolean[listCereal.length];
        cerealText.setText(theTextCereal);

        diary=(ImageButton)RootView.findViewById(R.id.diaryButton);
        diaryText=(TextView)RootView.findViewById(R.id.diaryContent);

        listDiary=new String[40];
        checkedDiary=new boolean[listDiary.length];
        diaryText.setText(theTextDiary);

        egg=(ImageButton)RootView.findViewById(R.id.eggButton);
        eggText=(TextView)RootView.findViewById(R.id.eggContent);

        listEgg=new String[15];
        checkedEgg=new boolean[listEgg.length];
        eggText.setText(theTextEgg);

        fruit=(ImageButton)RootView.findViewById(R.id.fruitButton);
        fruitText=(TextView)RootView.findViewById(R.id.fruitContent);

        listFruit=new String[40];
        checkedFruit=new boolean[listFruit.length];
        fruitText.setText(theTextFruit);

        meat=(ImageButton)RootView.findViewById(R.id.meatButton);
        meatText=(TextView)RootView.findViewById(R.id.meatContent);
        listMeat=new String[40];
        checkedMeat=new boolean[listMeat.length];
        meatText.setText(theTextMeat);

        seafood=(ImageButton)RootView.findViewById(R.id.seafoodButton);
        seafoodText=(TextView)RootView.findViewById(R.id.seafoodContent);
        listSeafood=new String[40];
        checkedSeafood=new boolean[listSeafood.length];
        seafoodText.setText(theTextSeafood);
        seafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String []listitem2=new String[40];
                for(int i=0;i<listitem2.length;i++)
                {
                    listitem2[i]=seafoodList.get(i).getInformation();
                }
                for(int i=0;i<listitem2.length;i++)
                {
                    listSeafood[i]=listitem2[i];
                }
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Seafood and Poultry");
                mBuilder.setMultiChoiceItems(listSeafood, checkedSeafood, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(! theSeafood.contains(position))
                            {
                                theSeafood.add(position);
                            }
                            else{
                                theSeafood.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String item ="";
                        for(int i=0;i<theSeafood.size();i++)
                        {
                            item=item+listSeafood[theSeafood.get(i)]+"\n";

                        }
                        seafoodText.setText(item);
                        theTextSeafood=item;

                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        for(int i=0; i<checkedSeafood.length;i++)
                        {
                            checkedSeafood[i]=false;
                            theSeafood.clear();
                            seafoodText.setText("Empty\n");
                            theTextSeafood="Empty\n";
                        }
                    }
                });
                AlertDialog mDialog =mBuilder.create();
                mDialog.show();

            }
        });

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String []listitem2=new String[40];
                for(int i=0;i<listitem2.length;i++)
                {
                    listitem2[i]=meatList.get(i).getInformation();
                }
                for(int i=0;i<listitem2.length;i++)
                {
                    listMeat[i]=listitem2[i];
                }
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Meat and Poultry");
                mBuilder.setMultiChoiceItems(listMeat, checkedMeat, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(! theMeat.contains(position))
                            {
                                theMeat.add(position);
                            }
                            else{
                                theMeat.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String item ="";
                        for(int i=0;i<theMeat.size();i++)
                        {
                            item=item+listMeat[theMeat.get(i)]+"\n";

                        }
                        meatText.setText(item);
                        theTextMeat=item;

                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        for(int i=0; i<checkedMeat.length;i++)
                        {
                            checkedMeat[i]=false;
                            theMeat.clear();
                            meatText.setText("Empty\n");
                            theTextMeat="Empty\n";
                        }
                    }
                });
                AlertDialog mDialog =mBuilder.create();
                mDialog.show();

            }
        });
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String []listitem2=new String[40];
                for(int i=0;i<listitem2.length;i++)
                {
                    listitem2[i]=fruitList.get(i).getInformation();
                }
                for(int i=0;i<listitem2.length;i++)
                {
                    listFruit[i]=listitem2[i];
                }
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Fruit and Vegetables");
                mBuilder.setMultiChoiceItems(listFruit, checkedFruit, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(! theFruit.contains(position))
                            {
                                theFruit.add(position);
                            }
                            else{
                                theFruit.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String item ="";
                        for(int i=0;i<theFruit.size();i++)
                        {
                            item=item+listFruit[theFruit.get(i)]+"\n";

                        }
                        fruitText.setText(item);
                        theTextFruit=item;

                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        for(int i=0; i<checkedFruit.length;i++)
                        {
                            checkedFruit[i]=false;
                            theFruit.clear();
                            fruitText.setText("Empty\n");
                            theTextFruit="Empty\n";
                        }
                    }
                });
                AlertDialog mDialog =mBuilder.create();
                mDialog.show();

            }
        });

        egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String []listitem2=new String[15];
                for(int i=0;i<listitem2.length;i++)
                {
                    listitem2[i]=eggList.get(i).getInformation();
                }
                for(int i=0;i<listitem2.length;i++)
                {
                    listEgg[i]=listitem2[i];
                }
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
                mBuilder.setTitle(R.string.Dialog_title);
                mBuilder.setMultiChoiceItems(listEgg, checkedEgg, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(! theEgg.contains(position))
                            {
                                theEgg.add(position);
                            }
                            else{
                                theEgg.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String item ="";
                        for(int i=0;i<theEgg.size();i++)
                        {
                            item=item+listEgg[theEgg.get(i)]+"\n";

                        }
                        eggText.setText(item);
                        theTextEgg=item;

                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        for(int i=0; i<checkedEgg.length;i++)
                        {
                            checkedEgg[i]=false;
                            theEgg.clear();
                            eggText.setText("Empty\n");
                            theTextEgg="Empty\n";
                        }
                    }
                });
                AlertDialog mDialog =mBuilder.create();
                mDialog.show();

            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String []listitem2=new String[40];
                for(int i=0;i<listitem2.length;i++)
                {
                    listitem2[i]=diaryList.get(i).getInformation();
                }
                for(int i=0;i<listitem2.length;i++)
                {
                    listDiary[i]=listitem2[i];
                }
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
                mBuilder.setTitle(R.string.Dialog_title);
                mBuilder.setMultiChoiceItems(listDiary, checkedDiary, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(! theDiary.contains(position))
                            {
                                theDiary.add(position);
                            }
                            else{
                                theDiary.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String item ="";
                        for(int i=0;i<theDiary.size();i++)
                        {
                            item=item+listDiary[theDiary.get(i)]+"\n";
                            /*if(i!=mUserItems.size()-1){
                                item=item+", ";
                            }*/
                        }
                        diaryText.setText(item);
                        theTextDiary=item;
                        /*try {
                            writeFile(item);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        for(int i=0; i<checkedDiary.length;i++)
                        {
                            checkedDiary[i]=false;
                            theDiary.clear();
                            diaryText.setText("Empty\n");
                            theTextDiary="Empty\n";
                        }
                    }
                });
                AlertDialog mDialog =mBuilder.create();
                mDialog.show();

            }
        });

        cereal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String []listitem2=new String[40];
                for(int i=0;i<listitem2.length;i++)
                {
                    listitem2[i]=cerealList.get(i).getInformation();
                }
                for(int i=0;i<listitem2.length;i++)
                {
                    listCereal[i]=listitem2[i];
                }
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
                mBuilder.setTitle(R.string.cereal_title);
                mBuilder.setMultiChoiceItems(listCereal, checkedCereal, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(! theCereal.contains(position))
                            {
                                theCereal.add(position);
                            }
                            else{
                                theCereal.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String item ="";
                        for(int i=0;i<theCereal.size();i++)
                        {
                            item=item+listCereal[theCereal.get(i)]+"\n";
                            /*if(i!=mUserItems.size()-1){
                                item=item+", ";
                            }*/
                       }
                        cerealText.setText(item);
                        theTextCereal=item;
                        /*try {
                            writeFile(item);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        for(int i=0; i<checkedCereal.length;i++)
                        {
                            checkedCereal[i]=false;
                            theCereal.clear();
                            cerealText.setText("Empty\n");
                            theTextCereal="Empty\n";
                        }
                    }
                });
                AlertDialog mDialog =mBuilder.create();
                mDialog.show();

            }
        });


        beverage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String []listitem2=new String[45];
                for(int i=0;i<listitem2.length;i++)
                {
                    listitem2[i]=beverageList.get(i).getInformation();
                }
                for(int i=0;i<listitem2.length;i++)
                {
                    listitem[i]=listitem2[i];
                }
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listitem, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(! mUserItems.contains(position))
                            {
                                mUserItems.add(position);
                            }
                            else{
                                mUserItems.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String item ="";
                        for(int i=0;i<mUserItems.size();i++)
                        {
                            item=item+listitem[mUserItems.get(i)]+"\n";
                            /*if(i!=mUserItems.size()-1){
                                item=item+", ";
                            }*/
                        }
                        beverageText.setText(item);
                        theText=item;
                        /*try {
                            writeFile(item);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        for(int i=0; i<checkedItems.length;i++)
                        {
                            checkedItems[i]=false;
                            mUserItems.clear();
                            beverageText.setText("Empty\n");
                            theText="Empty\n";
                        }
                    }
                });
                AlertDialog mDialog =mBuilder.create();
                mDialog.show();
            }
        });


        return RootView;
    }
/*
    private void writeFile(String text)throws IOException{
        File outputFile = new File ("input.txt");
        FileOutputStream out = new FileOutputStream(outputFile);
        out.write(text.getBytes());
        out.close();
    }*/
/*
    private String readFile() throws IOException {
        File inputFile = new File("input.txt");
        FileInputStream in = new FileInputStream(inputFile);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in, Charset.forName("UTF-8"))
        );
        String line = "";
        String result ="";
        try{
            while ((line = reader.readLine()) != null) {
                result =result+line+"\n";
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }
*/
    private void readBeverage() {
        InputStream is = getResources().openRawResource(R.raw.beverages);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            //Step over headers
            //reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                beverageList.add(t);
                //a=beverageList.get(0).getProduct_name();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readCereal() {
        InputStream is = getResources().openRawResource(R.raw.cereal);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";

       try {
           while((line = reader.readLine()) != null){
               String[] tokens = line.split(",");
               Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
               cerealList.add(t);
               //a=tokens[4];
           }
           //line= reader.readLine();
            //Step over headers
            //reader.readLine();
           // while ((line = reader.readLine()) != null) {
               // String[] tokens = line.split(",");
               // Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
               // cerealList.add(t);
             //  a=tokens[4];
           // }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //a=cerealList.get(20).getProduct_name();
    }

    private void readDiary() {
        InputStream is = getResources().openRawResource(R.raw.dairy);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";

        try {
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                diaryList.add(t);
                //a=tokens[4];
            }
            //line= reader.readLine();
            //Step over headers
            //reader.readLine();
            // while ((line = reader.readLine()) != null) {
            // String[] tokens = line.split(",");
            // Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
            // cerealList.add(t);
            //  a=tokens[4];
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //a=cerealList.get(20).getProduct_name();
    }

    private void readEgg() {
        InputStream is = getResources().openRawResource(R.raw.eggs);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";

        try {
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                eggList.add(t);
                //a=tokens[4];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readFruit() {
        InputStream is = getResources().openRawResource(R.raw.fruit);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";

        try {
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                fruitList.add(t);
                //a=tokens[4];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMeat() {
        InputStream is = getResources().openRawResource(R.raw.meat);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";

        try {
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                meatList.add(t);
                //a=tokens[4];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readSeafood() {
        InputStream is = getResources().openRawResource(R.raw.seafood);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";

        try {
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Food t = new Food(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                seafoodList.add(t);
                //a=tokens[4];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}