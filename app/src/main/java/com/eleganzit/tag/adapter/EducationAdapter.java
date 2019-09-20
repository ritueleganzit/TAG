package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



import com.bumptech.glide.Glide;
import com.eleganzit.tag.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder>
{

    Context context;
    Activity activity;

    public EducationAdapter(ArrayList<String> accounts, Context context) {

        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.edu_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {




    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
