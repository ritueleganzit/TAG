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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.model.CourceFee;
import com.eleganzit.tag.model.homecourse.CourceFeeDatum;
import com.eleganzit.tag.model.homecourse.Datastream;
import com.eleganzit.tag.model.newhome.CourceDetail;
import com.eleganzit.tag.model.newhome.CourseResult;

import java.util.ArrayList;
import java.util.List;

public class HomeCoursesAdapter extends RecyclerView.Adapter<HomeCoursesAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    List<CourceDetail> datastreams;
    ArrayList<Datastream> datastreamArrayList=new ArrayList<>();
    public HomeCoursesAdapter(List<CourceDetail> datastreams, Context context) {

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


        CourceDetail courceDetail=datastreams.get(i);
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

     holder.course_name.setText(courceDetail.getCourseName());
if (courceDetail.getCourseResult().size()>0)
{
    if (courceDetail.getCourseResult().get(0).getSpecializationName()!=null && !(courceDetail.getCourseResult().get(0).getSpecializationName().isEmpty()))
    {
        holder.specialisation_nam.setText(""+courceDetail.getCourseResult().get(0).getSpecializationName());

    } if (courceDetail.getCourseResult().get(0).getStudyMode()!=null && !(courceDetail.getCourseResult().get(0).getStudyMode().isEmpty()))
    {
        holder.course_fees.setText(""+courceDetail.getCourseResult().get(0).getStudyMode());

    }
}

if (courceDetail.getCourseCount().toString().equalsIgnoreCase("1"))
{    holder.total.setText(""+courceDetail.getCourseCount()+" Course");


}
else
{
    holder.total.setText(""+courceDetail.getCourseCount()+" Courses");
}




       // Log.d("uynuu",""+courceDetail.getCourseResult().get(i).getSpecializationName());
     //holder.total.setText(datastreams.get(i).getDatastream().size()+" course");
    /* datastreamArrayList.addAll(datastreams.get(i).getDatastream());
        holder.hidetxt.setAdapter(new CourseDetailAdapter(datastreams.get(i).getDatastream(),context));
        holder.hidetxt.setNestedScrollingEnabled(false);
*/

    }

    @Override
    public int getItemCount() {
        return datastreams.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
ImageView downarrow;

LinearLayout hidetxt;
TextView course_name,total,specialisation_nam,course_fees;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            specialisation_nam=itemView.findViewById(R.id.specialisation_nam);
            downarrow=itemView.findViewById(R.id.downarrow);
            course_fees=itemView.findViewById(R.id.years);
            course_name=itemView.findViewById(R.id.course_name);
            hidetxt=itemView.findViewById(R.id.linehide);
            total=itemView.findViewById(R.id.total);


        }
    }
}
