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

import java.util.ArrayList;
import java.util.List;

public class CurrentEduAdapter extends RecyclerView.Adapter<CurrentEduAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    List<Preferancedata> accounts;
    public CurrentEduAdapter(List<Preferancedata> accounts, Context context) {

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

        Preferancedata preferancedata=accounts.get(i);
        holder.tvcourse.setText(""+preferancedata.getStream());
        if (preferancedata.getSpecialisation()!=null  && !(preferancedata.getSpecialisation().isEmpty()))
        {
            holder.mode_of_study.setText(""+preferancedata.getCourse()+" ("+preferancedata.getSpecialisation()+")");
        }
        else
        {
            holder.mode_of_study.setText(""+preferancedata.getCourse());
        }




    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView tvcourse,mode_of_study;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mode_of_study=itemView.findViewById(R.id.mode_of_study);
            tvcourse=itemView.findViewById(R.id.tvcourse);


        }
    }
}
