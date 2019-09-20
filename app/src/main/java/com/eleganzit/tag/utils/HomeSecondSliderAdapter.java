package com.eleganzit.tag.utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.eleganzit.tag.R;

import java.util.ArrayList;

public class HomeSecondSliderAdapter extends RecyclerView.Adapter<HomeSecondSliderAdapter.MyViewHolder> {
    String status,status_name,progress_name;
    ArrayList arr;
    Context context;
    int h;
    ProgressDialog progressDialog;

    public HomeSecondSliderAdapter(int height, ArrayList arr, Context context) {
        this.arr = arr;
        this.context = context;
        h=height;
            }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homesec,viewGroup,false);

        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
myViewHolder.imageView.getLayoutParams().height= (int) (h/1.8);

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.img);



        }
    }



}
