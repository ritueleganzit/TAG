package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.model.CourceFee;
import com.eleganzit.tag.model.CourseDetail;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CoursesHomeAdapter extends RecyclerView.Adapter<CoursesHomeAdapter.MyViewHolder>
{
    ArrayList<CourceFee> courseDetailArrayList;

    Context context;
    Activity activity;
    public CoursesHomeAdapter(    ArrayList<CourceFee> courseDetailArrayList ,Context context) {

        this.context = context;
        this.courseDetailArrayList= courseDetailArrayList;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_home_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        CourceFee courseDetail=courseDetailArrayList.get(i);

        Log.d("fsfsfs",""+courseDetail.getCourseName());
        Log.d("fsfsfs",""+courseDetail.getCourseFees());

        if (courseDetail.getCourseName()!=null && !(courseDetail.getCourseName().isEmpty()))
        {
            holder.course_name.setText(""+courseDetail.getCourseName());

        }
        if (courseDetail.getCourseFees()!=null && !(courseDetail.getCourseFees().isEmpty()))
        {
            holder.course_fees.setText(""+courseDetail.getCourseFees());

        }
        if (courseDetail.getSpecializationName()!=null && !(courseDetail.getSpecializationName().isEmpty()))
        {
            holder.specialization_name.setText(""+courseDetail.getSpecializationName());

        }


    }

    @Override
    public int getItemCount() {
        return courseDetailArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
TextView course_fees,specialization_name,course_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            course_name=itemView.findViewById(R.id.course_name);
            specialization_name=itemView.findViewById(R.id.specialization_name);
            course_fees=itemView.findViewById(R.id.course_fees);




        }
    }
}
