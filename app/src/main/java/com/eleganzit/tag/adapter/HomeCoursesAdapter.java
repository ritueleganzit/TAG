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
import com.eleganzit.tag.model.homecourse.CourceFeeDatum;
import com.eleganzit.tag.model.homecourse.Datastream;

import java.util.ArrayList;

public class HomeCoursesAdapter extends RecyclerView.Adapter<HomeCoursesAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    ArrayList<CourceFeeDatum> datastreams;
    ArrayList<Datastream> datastreamArrayList=new ArrayList<>();
    public HomeCoursesAdapter(ArrayList<CourceFeeDatum> datastreams,Context context) {

        this.context = context;
        this.datastreams = datastreams;
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


        CourceFeeDatum datastream=datastreams.get(i);
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

     holder.course_name.setText(datastream.getCourseName());
        Log.d("myyyadapter ",datastream.getCollegeId()+" --"+datastreams.get(i).getDatastream().get(i));
     holder.total.setText(datastreams.get(i).getDatastream().size()+" course");
     datastreamArrayList.addAll(datastreams.get(i).getDatastream());
        holder.hidetxt.setAdapter(new CourseDetailAdapter(datastreams.get(i).getDatastream(),context));
        holder.hidetxt.setNestedScrollingEnabled(false);


    }

    @Override
    public int getItemCount() {
        return datastreams.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
ImageView downarrow;
RecyclerView hidetxt;
TextView course_name,total;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            downarrow=itemView.findViewById(R.id.downarrow);
            course_name=itemView.findViewById(R.id.course_name);
            hidetxt=itemView.findViewById(R.id.rchidetxt);
            total=itemView.findViewById(R.id.total);


        }
    }
}
