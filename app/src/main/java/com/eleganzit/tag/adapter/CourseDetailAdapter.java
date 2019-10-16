package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.model.homecourse.Datastream;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailAdapter extends RecyclerView.Adapter<CourseDetailAdapter.MyViewHolder>
{
    List<Datastream> datastreams;

    Context context;
    Activity activity;
    public CourseDetailAdapter(List<Datastream> datastreams, Context context) {

        this.context = context;
        this.datastreams = datastreams;
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
Datastream datastream=datastreams.get(i);
holder.specialization_name.setText(""+datastream.getCourseName()+"("+datastream.getSpecializationName()+")");
holder.course_fees.setText(""+datastream.getCourseFees());
holder.years.setText("Full time | "+datastream.getYears());


    }

    @Override
    public int getItemCount() {
        return datastreams.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
ImageView downarrow;
RecyclerView hidetxt;
TextView specialization_name,course_fees,years;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            specialization_name=itemView.findViewById(R.id.specialization_name);
            course_fees=itemView.findViewById(R.id.course_fees);
            years=itemView.findViewById(R.id.years);


        }
    }
}
