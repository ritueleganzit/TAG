package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.eleganzit.tag.HomeActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.model.CollegeResult;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;
import com.eleganzit.tag.ui.activity.TopCollegesActivity;

import java.util.ArrayList;

import me.nereo.multi_image_selector.bean.Image;

import static android.content.Context.WINDOW_SERVICE;

public class CollegeListAdapter extends RecyclerView.Adapter<CollegeListAdapter.MyViewHolder>
{
    ArrayList<CollegeResult> collegeResultArrayList;
    Context context;
    Activity activity;
    String e_sp;
    public CollegeListAdapter(String e_sp, ArrayList<CollegeResult> collegeResultArrayList, Context context) {

        this.e_sp = e_sp;
        this.collegeResultArrayList = collegeResultArrayList;
        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.college_list_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
final CollegeResult collegeResult=collegeResultArrayList.get(i);
        holder.clgcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CollegeDetailActivity.class)
                .putExtra("college_id",collegeResult.getCollegeId())
                .putExtra("e_sp",e_sp)
                .putExtra("college_name",collegeResult.getCollegeName()));



            }
        });
        holder.imgbg.getLayoutParams().width= (int) (getScreenWidthInPXs(context,(Activity) context));
        holder.imgbg.getLayoutParams().height= (int) (getScreenWidthInPXs(context,(Activity) context)/3.3);

holder.collegename.setText(""+collegeResult.getCollegeName());
holder.specialname.setText(""+e_sp);
        if (collegeResult.getCollegeCity()!=null  && !(collegeResult.getCollegeCity().isEmpty()))
        {
            holder.collegeaddress.setText(""+collegeResult.getCollegeCity());
        }
        else
        {
            holder.collegeaddress.setText("");
        }

        if (collegeResult.getCollegeState()!=null  && !(collegeResult.getCollegeState().isEmpty()))
        {
            holder.collegeaddress.append(", "+collegeResult.getCollegeState()+"");
        }
        else
        {
            holder.collegeaddress.setText(""+holder.collegeaddress.getText().toString());
        }

        if (collegeResult.getCollegeCountry()!=null  && !(collegeResult.getCollegeCountry().isEmpty()))
        {
            holder.collegeaddress.append(", "+collegeResult.getCollegeCountry()+"");
        } if (collegeResult.getCollegeType()!=null  && !(collegeResult.getCollegeType().isEmpty()))
        {
            holder.college_type.setText(""+collegeResult.getCollegeType()+"");
        } if (collegeResult.getYears()!=null  && !(collegeResult.getYears().isEmpty()))
        {
            holder.years.setText(""+collegeResult.getYears()+"");
        }if (collegeResult.getApprovedBy()!=null  && !(collegeResult.getApprovedBy().isEmpty()))
        {
            holder.approved_by.setText("Approved by - "+collegeResult.getApprovedBy()+"");
        }if (collegeResult.getAccreditation()!=null  && !(collegeResult.getAccreditation().isEmpty()))
        {
            holder.accreditation.setText("Accredited by  - "+collegeResult.getAccreditation()+"");
        }

        if (collegeResult.getPlacement()!=null  && !(collegeResult.getPlacement().isEmpty()))
        {
            holder.placement.setText("Placement : "+collegeResult.getPlacement()+"");
        }


        if (collegeResult.getIsUniversity()==1)
        {
           if (collegeResult.getUniversityName()!=null  && !(collegeResult.getUniversityName().isEmpty()))
         holder.isUniversity.setText("Affiliated to "+collegeResult.getUniversityName());
        }
        else {
            holder.isUniversity.setText("Affiliated to Deemed university");
        }



        if (collegeResult.getUniversityName()!=null && !(collegeResult.getUniversityName().isEmpty()))
        {
            holder.privatetv.setText("Private  "+collegeResult.getUniversityName());
        }  if (collegeResult.getRank()!=null && !(collegeResult.getRank().isEmpty()))
        {
            holder.rank.setText("Rank : "+collegeResult.getRank());
        }
        if (collegeResult.getRatings()!=null)
        {
            holder.myRatingBar.setRating(Float.parseFloat(""+collegeResult.getRatings()));


        }
        else
        {
            holder.myRatingBar.setRating(0);
        }
    }

    @Override
    public int getItemCount() {
        return collegeResultArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

CardView clgcard;
TextView collegename,collegeaddress,college_type,years,approved_by,accreditation,isUniversity,placement,privatetv,rank,specialname;
ImageView imgbg;
RatingBar myRatingBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            specialname=itemView.findViewById(R.id.specialname);
            collegeaddress=itemView.findViewById(R.id.collegeaddress);
            college_type=itemView.findViewById(R.id.college_type);
            myRatingBar=itemView.findViewById(R.id.myRatingBar);
            placement=itemView.findViewById(R.id.placement);
            years=itemView.findViewById(R.id.years);
            collegename=itemView.findViewById(R.id.collegename);
            accreditation=itemView.findViewById(R.id.accreditation);
            isUniversity=itemView.findViewById(R.id.isUniversity);
            imgbg=itemView.findViewById(R.id.imgbg);
            rank=itemView.findViewById(R.id.rank);
            privatetv=itemView.findViewById(R.id.privatetv);
            approved_by=itemView.findViewById(R.id.approved_by);
clgcard=itemView.findViewById(R.id.clgcard);

        }
    }
    public static int getScreenWidthInPXs(Context context, Activity activity){

        DisplayMetrics dm = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
}
