package com.example.se_project;

import android.content.Context;
import android.content.Intent;
import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<Model>modelList;
    ArrayList<Model>arrayList;

    public ListViewAdapter(Context context, List<Model> modelList) {
        mContext = context;
        this.modelList = modelList;
        inflater=LayoutInflater.from(mContext);
        this.arrayList=new ArrayList<Model>();
        this.arrayList.addAll(modelList);
    }

    public class ViewHolder{
        TextView title, desc;
        ImageView mIcon;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Model getItem(int i) {
        return modelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null)
        {
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.row,null);
            holder.title=view.findViewById(R.id.name);
            holder.desc=view.findViewById(R.id.address);
            holder.mIcon=view.findViewById(R.id.icon);
            view.setTag(holder);
        }
        else{
            holder=(ViewHolder)view.getTag();
        }
        if(modelList.get(position).getDistance()!=-1)
        {
            Model nearest=arrayList.get(0);
            double min=arrayList.get(0).getDistance();
            for(int i =0;i<arrayList.size();i++)
            {
                if(arrayList.get(i).getDistance()<min)
                {
                    min=arrayList.get(i).getDistance();
                    nearest=arrayList.get(i);
                }
            }
            String dist="";
            double distanceValue=modelList.get(position).getDistance()/1000;
            if(modelList.get(position).getDistance()==min)
                dist="Nearest: "+String.format("%.2f", distanceValue)+"km from here";
            else{
                dist="Distance: "+String.format("%.2f", distanceValue)+"km";
            }
            holder.desc.setText(dist);
        }
        else{
            holder.desc.setText(modelList.get(position).getInfo1());
        }

        holder.title.setText(modelList.get(position).getTitle());

        holder.mIcon.setImageResource(modelList.get(position).getIcon());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(modelList.get(position).getDistance()!=-1)
                {
                    Intent intent = new Intent(mContext,OnePharmacy.class);
                    intent.putExtra("Name",modelList.get(position).getTitle());
                    intent.putExtra("Info",modelList.get(position).getInfo1());
                    intent.putExtra("Address",modelList.get(position).getInfo2());
                    mContext.startActivity(intent);
                }
                else{
                    String xx="";
                    Intent intent = new Intent(mContext,OneMedicine.class);
                    xx=modelList.get(position).getNum()+xx;
                    intent.putExtra("Number",xx);
                    mContext.startActivity(intent);
                }

            }
        });
        return view;
    }



    public void filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        modelList.clear();
        if(charText.length()==0){
            modelList.addAll(arrayList);
        }
        else{
            for(Model model:arrayList){
                if(model.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    modelList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
