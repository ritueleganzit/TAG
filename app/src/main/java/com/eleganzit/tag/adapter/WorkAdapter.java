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
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.model.profileinfo.ExperienceInfo;

import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
   List<ExperienceInfo> accounts;
    public WorkAdapter(List<ExperienceInfo> accounts, Context context) {

        this.context = context;
        this.accounts = accounts;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.work_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        ExperienceInfo workdata=accounts.get(i);
holder.designation.setText(""+workdata.getDesignation());
holder.employee_name.setText(""+workdata.getEmployeeName());
holder.iscurrent.setText(""+workdata.getCurrentJobQue());
holder.depart.setText(""+workdata.getDepartment());


    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView designation,employee_name,depart,iscurrent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            employee_name=itemView.findViewById(R.id.employee_name);
            iscurrent=itemView.findViewById(R.id.iscurrent);
            depart=itemView.findViewById(R.id.depart);
            designation=itemView.findViewById(R.id.designation);


        }
    }
}
