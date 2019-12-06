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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eleganzit.tag.R;
import com.eleganzit.tag.model.homegallery.Event;
import com.eleganzit.tag.model.schoolhome.Datum;

import java.util.ArrayList;

import static android.content.Context.WINDOW_SERVICE;

public class SchoolGalleryEventAdapter extends RecyclerView.Adapter<SchoolGalleryEventAdapter.MyViewHolder> {
    String status,status_name,progress_name;
    ArrayList<Datum> arr;
    Context context;
    int h;
    Activity activity;
    ProgressDialog progressDialog;

    public SchoolGalleryEventAdapter(ArrayList<Datum> arr, LinearLayout linearlayoutsize, Context context) {
        this.context = context;
        this.arr = arr;
        activity = (Activity) context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clggallery_row,viewGroup,false);

        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Datum courseDetail=arr.get(i);
     //   myViewHolder.img.getLayoutParams().width= (int) (getScreenWidthInPXs(context,activity)/3.3);
      //  myViewHolder.img.getLayoutParams().height=getScreenWidthInPXs(context,activity)/4;
       // myViewHolder.rel_main.getLayoutParams().width= (int) (getScreenWidthInPXs(context,activity)/3.3);
       // myViewHolder.rel_main.getLayoutParams().height=getScreenWidthInPXs(context,activity)/4;
        Log.d("gdgd",""+courseDetail.getImageUrl());
        myViewHolder.categoryname.setText(courseDetail.getSectionName());
        Glide
                .with(context)
                .load(courseDetail.getImageUrl())

                .into(myViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView categoryname;
        RelativeLayout rel_main;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryname=itemView.findViewById(R.id.categoryname);
            img=itemView.findViewById(R.id.img);



        }
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

}
