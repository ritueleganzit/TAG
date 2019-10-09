package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.model.AppliedCollegeList;

import java.util.ArrayList;

import static android.content.Context.WINDOW_SERVICE;

public class AppliedAdapter extends RecyclerView.Adapter<AppliedAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    ArrayList<AppliedCollegeList> appliedCollegeLists;
    public AppliedAdapter(ArrayList<AppliedCollegeList> appliedCollegeLists,Context context) {

        this.context = context;
        this.appliedCollegeLists = appliedCollegeLists;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.applied_clg_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        AppliedCollegeList appliedCollegeList=appliedCollegeLists.get(i);
        if (appliedCollegeList.getCollegeName()!=null && !(appliedCollegeList.getCollegeName().isEmpty()))
        {
            holder.college_name.setText(""+appliedCollegeList.getCollegeName());
        }

        if (appliedCollegeList.getCourseName()!=null && !(appliedCollegeList.getCourseName().isEmpty()))
        {
            holder.course_name.setText(appliedCollegeList.getCourseName());

        }
        if (appliedCollegeList.getSpecializationName()!=null && !(appliedCollegeList.getSpecializationName().isEmpty()))
        {
            holder.course_name.append(""+" - "+appliedCollegeList.getSpecializationName());
        }

        if (appliedCollegeList.getRatings()>0)
        {
            holder.ratings.setRating((float) appliedCollegeList.getRatings());
        }

        if (appliedCollegeList.getApplicationApproved().toString().equalsIgnoreCase("1"))
        {
            holder.application_approved.setText("Applied");
        }
        else
        {
            holder.application_approved.setText("Pay Now");
        }
    }

    @Override
    public int getItemCount() {
        return appliedCollegeLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

CardView clgcard;
TextView college_name,course_name;
ImageView imgbg;
TextView application_approved;
RatingBar ratings;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbg=itemView.findViewById(R.id.imgbg);
            ratings=itemView.findViewById(R.id.ratings);
            college_name=itemView.findViewById(R.id.college_name);
            application_approved=itemView.findViewById(R.id.application_approved);
            course_name=itemView.findViewById(R.id.course_name);
clgcard=itemView.findViewById(R.id.clgcard);

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
