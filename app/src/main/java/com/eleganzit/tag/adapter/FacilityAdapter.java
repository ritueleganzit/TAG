package com.eleganzit.tag.adapter;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eleganzit.tag.R;

import java.util.ArrayList;

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.MyViewHolder> {
    String status,status_name,progress_name;
    ArrayList arr;
    int arimage[]={R.mipmap.swimming,R.mipmap.coffee,R.mipmap.exercise,R.mipmap.football,R.mipmap.hostel,R.mipmap.coffee,R.mipmap.exercise,R.mipmap.football};
    String name[]={"Swimming","Caferteria","Gym","Sports","Hostel","Caferteria","Gym","Sports"};
    Context context;
    int h;
    ProgressDialog progressDialog;

    public FacilityAdapter( Context context) {
        this.arr = arr;
        this.context = context;
            }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.facility_row,viewGroup,false);

        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

myViewHolder.nam.setText(""+name[i]);
        Glide
                .with(context)
                .load(arimage[i])

                .into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return arimage.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

ImageView imageView;
TextView nam;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            nam=itemView.findViewById(R.id.nam);
            imageView=itemView.findViewById(R.id.img);



        }
    }



}
