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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.bumptech.glide.Glide;
import com.eleganzit.tag.R;
import com.eleganzit.tag.model.Education;
import com.eleganzit.tag.model.profileinfo.EducationDetail;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder>
{

    Context context;
    Activity activity;

    List<EducationDetail> educationArrayList;
    public EducationAdapter(List<EducationDetail> educationArrayList, Context context) {

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

        EducationDetail education=educationArrayList.get(i);


if (education.getType()!=null  && !(education.getType().isEmpty()))
{
    if (education.getType().equalsIgnoreCase("school"))
    {        holder.school_name_txt.setText("School Name");

        holder.cource_level.setText(""+education.getCourseLevel());
        holder.cource_year.setText(""+education.getCompletionYear());
        holder.cource_schoolname.setText(""+education.getSchoolName());
        holder.cource_marks.setText(""+education.getMarks());
        holder.cource_Board.setText(""+education.getBoard());
        holder.lin_degree.setVisibility(View.GONE);
        holder.lin_specialization.setVisibility(View.GONE);
        holder.lin_uni_name.setVisibility(View.GONE);
        holder.lin_cource_Board.setVisibility(View.VISIBLE);

    }
    else
    {
        holder.lin_degree.setVisibility(View.VISIBLE);
        holder.lin_uni_name.setVisibility(View.VISIBLE);
        holder.lin_specialization.setVisibility(View.VISIBLE);
        holder.lin_cource_Board.setVisibility(View.GONE);

        holder.school_name_txt.setText("College Name");
        holder.degree.setText(""+education.getDegree());
        holder.uni_name.setText(""+education.getUniversity());
        holder.specialization_txt.setText(""+education.getSpecialization());
        holder.cource_level.setText(""+education.getCourseLevel());
        holder.cource_year.setText(""+education.getCompletionYear());
        holder.cource_marks.setText(""+education.getMarks());
        holder.cource_schoolname.setText(""+education.getCollegeName());

        holder.cource_Board.setText(""+education.getUniversity());

    }




}



    }

    @Override
    public int getItemCount() {
        return educationArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lin_degree,lin_specialization,lin_cource_Board,lin_uni_name;

TextView cource_level,cource_year,cource_Board,cource_marks,cource_schoolname,school_name_txt,degree,specialization_txt,uni_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cource_schoolname=itemView.findViewById(R.id.cource_schoolname  );
            lin_uni_name=itemView.findViewById(R.id.lin_uni_name  );
            uni_name=itemView.findViewById(R.id.uni_name  );
            lin_cource_Board=itemView.findViewById(R.id.lin_cource_Board  );
            lin_specialization=itemView.findViewById(R.id.lin_specialization  );
            specialization_txt=itemView.findViewById(R.id.specialization_txt  );
            degree=itemView.findViewById(R.id.degree  );
            lin_degree=itemView.findViewById(R.id.lin_degree  );
            cource_Board=itemView.findViewById(R.id.cource_Board);
            cource_year=itemView.findViewById(R.id.cource_year);
            cource_marks=itemView.findViewById(R.id.cource_marks);
            school_name_txt=itemView.findViewById(R.id.school_name_txt);
            cource_level=itemView.findViewById(R.id.cource_level);
        }
    }
}
