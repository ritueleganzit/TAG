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
import android.widget.TextView;
import android.widget.Toast;



import com.bumptech.glide.Glide;
import com.eleganzit.tag.R;
import com.eleganzit.tag.model.Education;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder>
{

    Context context;
    Activity activity;

    List<Education> educationArrayList;
    public EducationAdapter(List<Education> educationArrayList, Context context) {

        this.context = context;
        this.educationArrayList = educationArrayList;
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

Education education=educationArrayList.get(i);
if (education.getSchoolName()!=null  && !(education.getSchoolName().isEmpty()))
{
    holder.cource_level.setText(""+education.getCourceLevel()+" at "+education.getSchoolName());
    holder.cource_year.setText(""+education.getCourceYear());

}



    }

    @Override
    public int getItemCount() {
        return educationArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView cource_level,cource_year;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cource_year=itemView.findViewById(R.id.cource_year);
            cource_level=itemView.findViewById(R.id.cource_level);
        }
    }
}
