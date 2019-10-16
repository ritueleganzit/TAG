package com.eleganzit.tag.ui.activity.school.adpater;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CourseDetailsAdapter;

public class CoursesSchoolAdapter extends RecyclerView.Adapter<CoursesSchoolAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    public CoursesSchoolAdapter(Context context) {

        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_school_fee_row,viewGroup,false);
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
