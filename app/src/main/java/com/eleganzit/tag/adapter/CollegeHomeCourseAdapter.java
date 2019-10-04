package com.eleganzit.tag.adapter;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.tag.R;

import java.util.ArrayList;

public class CollegeHomeCourseAdapter extends RecyclerView.Adapter<CollegeHomeCourseAdapter.MyViewHolder> {
    String status,status_name,progress_name;
    ArrayList arr;
    Context context;
    int h;
    ProgressDialog progressDialog;

    public CollegeHomeCourseAdapter(Context context) {
        this.context = context;
            }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clghomecourserow,viewGroup,false);

        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);



        }
    }



}
