package com.eleganzit.tag.adapter;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eleganzit.tag.R;
import com.eleganzit.tag.model.FacilityData;
import com.eleganzit.tag.model.homefacility.Datum;

import java.util.ArrayList;

import static android.content.Context.WINDOW_SERVICE;

public class HomeFacilityAdapter extends RecyclerView.Adapter<HomeFacilityAdapter.MyViewHolder> {
    String status,status_name,progress_name;
    ArrayList arr;
    int arimage[]={R.mipmap.swimming,R.mipmap.coffee,R.mipmap.exercise,R.mipmap.football,R.mipmap.hostel,R.mipmap.coffee,R.mipmap.exercise,R.mipmap.football};
    String name[]={"Swimming","Caferteria","Gym","Sports","Hostel","Caferteria","Gym","Sports"};
    Context context;
    ArrayList<Datum> facilityDataArrayList;
    int h;
    ProgressDialog progressDialog;
    Activity activity;
    public HomeFacilityAdapter(ArrayList<Datum> facilityDataArrayList, Context context) {
        this.arr = arr;
        this.context = context;
        activity = (Activity) context;

        this.facilityDataArrayList = facilityDataArrayList;
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
Datum facilityData=facilityDataArrayList.get(i);
       // myViewHolder.imageView.getLayoutParams().width= (int) (getScreenWidthInPXs(context,activity)/3.3);
        //myViewHolder.imageView.getLayoutParams().height=getScreenWidthInPXs(context,activity)/4;
        Log.d("dsadad",""+facilityData.getFacility_icon());
myViewHolder.nam.setText(""+facilityData.getFacilityName());
        Glide
                .with(context)
                .load(facilityData.getFacility_icon())

                .into(myViewHolder.imageView);

    }
    public static int getScreenWidthInPXs(Context context, Activity activity){

        DisplayMetrics dm = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
    @Override
    public int getItemCount() {
        return facilityDataArrayList.size();
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
