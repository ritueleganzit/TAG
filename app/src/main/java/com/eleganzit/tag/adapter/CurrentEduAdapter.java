package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.model.Preferancedata;
import com.eleganzit.tag.model.profileinfo.PreferenceInfo;

import java.util.ArrayList;
import java.util.List;

public class CurrentEduAdapter extends RecyclerView.Adapter<CurrentEduAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    List<PreferenceInfo> accounts;
    public CurrentEduAdapter(List<PreferenceInfo> accounts, Context context) {

        this.context = context;
        this.accounts = accounts;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.current_edu_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        PreferenceInfo preferancedata=accounts.get(i);
        if (preferancedata.getStream()!=null  && !(preferancedata.getStream().isEmpty()))
        {
            holder.tvcourse.setText(""+preferancedata.getStream());

        }
        if (preferancedata.getSpecialization()!=null  && !(preferancedata.getSpecialization().isEmpty()))
        {
            holder.secialization_txt.setText(preferancedata.getSpecialization());
        }
 if (preferancedata.getCourse()!=null  && !(preferancedata.getCourse().isEmpty()))
        {
            holder.Course_txt.setText(preferancedata.getCourse());
        }

 if (preferancedata.getStudyMode()!=null  && !(preferancedata.getStudyMode().isEmpty()))
        {
            holder.mode_of_study_txt.setText(preferancedata.getStudyMode());
        }





    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView tvcourse,secialization_txt,Course_txt,mode_of_study_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            secialization_txt=itemView.findViewById(R.id.secialization_txt);
            Course_txt=itemView.findViewById(R.id.Course_txt);
            tvcourse=itemView.findViewById(R.id.tvcourse);
            mode_of_study_txt=itemView.findViewById(R.id.mode_of_study_txt);


        }
    }
}
