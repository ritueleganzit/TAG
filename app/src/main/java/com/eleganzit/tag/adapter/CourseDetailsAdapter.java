package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.model.homecourse.CourceFeeDatum;
import com.eleganzit.tag.model.homecourse.Datastream;

import java.util.ArrayList;

public class CourseDetailsAdapter extends RecyclerView.Adapter<CourseDetailsAdapter.MyViewHolder>
{
    ArrayList<Datastream> datastreams;

    Context context;
    Activity activity;
    public CourseDetailsAdapter(Context context) {

        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_detail_fee_row,viewGroup,false);
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
ImageView downarrow;
RecyclerView hidetxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
