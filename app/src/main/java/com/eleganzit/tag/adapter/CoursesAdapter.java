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
import com.eleganzit.tag.model.GetFaqList;

import java.util.List;

import me.nereo.multi_image_selector.bean.Image;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    public CoursesAdapter(  Context context) {

        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_fee_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {



     holder.downarrow.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             holder.hidetxt.setVisibility((holder.hidetxt.getVisibility() == View.VISIBLE)
                     ? View.GONE : View.VISIBLE);
             if (holder.hidetxt.getVisibility() == View.VISIBLE){
                 holder.downarrow.setImageResource(R.drawable.ic_angle_arrow_up);
             }
             else
             {
                 holder.downarrow.setImageResource(R.drawable.ic_angle_arrow_down);
             }
         }
     });
        holder.hidetxt.setAdapter(new CourseDetailsAdapter(context));
        holder.hidetxt.setNestedScrollingEnabled(false);


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
            downarrow=itemView.findViewById(R.id.downarrow);
            hidetxt=itemView.findViewById(R.id.rchidetxt);


        }
    }
}
