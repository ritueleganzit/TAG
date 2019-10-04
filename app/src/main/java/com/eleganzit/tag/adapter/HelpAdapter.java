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
import com.eleganzit.tag.model.GetFaqList;
import com.eleganzit.tag.model.Workdata;

import java.util.List;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    List<GetFaqList> getFaqListList;
    public HelpAdapter(List<GetFaqList> getFaqListList, Context context) {

        this.context = context;
        this.getFaqListList = getFaqListList;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.help_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        GetFaqList getFaqList=getFaqListList.get(i);
        holder.txttap.setText(getFaqList.getQuestion());
        holder.hidetxt.setText(getFaqList.getAnswer());

     holder.txttap.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             holder.hidetxt.setVisibility((holder.hidetxt.getVisibility() == View.VISIBLE)
                     ? View.GONE : View.VISIBLE);
         }
     });
    }

    @Override
    public int getItemCount() {
        return getFaqListList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView txttap,hidetxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txttap=itemView.findViewById(R.id.txttap);
            hidetxt=itemView.findViewById(R.id.hidetxt);


        }
    }
}
