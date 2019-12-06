package com.eleganzit.tag.ui.activity.school.adpater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eleganzit.tag.R;
import com.eleganzit.tag.model.schoolstream.Datum;
import com.eleganzit.tag.model.schoolstream.Result;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;
import com.eleganzit.tag.ui.activity.school.SchoolDetailActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.WINDOW_SERVICE;

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.MyViewHolder>
{
    ArrayList<Result> collegeResultArrayList;

    Context context;
    Activity activity;
    public SchoolListAdapter(ArrayList<Result> collegeResultArrayList, Context context) {

        this.collegeResultArrayList = collegeResultArrayList;
        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.school_list_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        Result collegeResult=collegeResultArrayList.get(i);
        holder.collegename.setText(capitalize(""+collegeResult.getSchoolName()));
       // Toast.makeText(context, ""+collegeResult.getSchoolId(), Toast.LENGTH_SHORT).show();
        holder.clgcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SchoolDetailActivity.class);
                intent.putExtra("college_id",""+collegeResult.getSchoolId());
                intent.putExtra("college_name",""+collegeResult.getSchoolName());

                context.startActivity(intent);

            }
        });
        holder.imgbg.getLayoutParams().width= (int) (getScreenWidthInPXs(context,(Activity) context));
        holder.imgbg.getLayoutParams().height= (int) (getScreenWidthInPXs(context,(Activity) context)/3.3);

        if (collegeResult.getSchoolLocation()!=null  && !(collegeResult.getSchoolLocation().isEmpty()))
        {
            holder.collegeaddress.setText(capitalize(""+collegeResult.getSchoolLocation()));
        } if (collegeResult.getSchoolImage()!=null  && !(collegeResult.getSchoolImage().isEmpty()))
        {
            Glide.with(context).load(collegeResult.getSchoolImage()).into(holder.imgbg);
        }
        else
        {
            holder.collegeaddress.setText("");
        }

        if (collegeResult.getSchoolState()!=null  && !(collegeResult.getSchoolState().isEmpty()))
        {
            holder.collegeaddress.append(", "+collegeResult.getSchoolState()+"");
        }
        else
        {
            holder.collegeaddress.setText(capitalize(""+holder.collegeaddress.getText().toString()+""));
        }
        if (collegeResult.getType()!=null  && !(collegeResult.getType().isEmpty()))
        {
            holder.college_type.setText(capitalize(""+collegeResult.getType()+""));
        } if (collegeResult.getCategory()!=null  && !(collegeResult.getCategory().isEmpty()))
        {
            holder.category.setText(capitalize(""+collegeResult.getCategory()+""));
        }

    if (collegeResult.getApprovedBy()!=null  && !(collegeResult.getApprovedBy().isEmpty()))
    {
        holder.approved_by.setText(capitalize(""+collegeResult.getApprovedBy()+""));
    }
    else
    {
        holder.approved_by.setText("");

    }
 if (collegeResult.getAccreditation()!=null  && !(collegeResult.getAccreditation().isEmpty()))
    {
        holder.accreditation.setText(capitalize(""+collegeResult.getAccreditation()+""));
    }
 if (collegeResult.getOwnership()!=null  && !(collegeResult.getOwnership().isEmpty()))
    {
        holder.ownership.setText(capitalize(""+collegeResult.getOwnership()+""));
    } if (collegeResult.getBoardName()!=null  && !(collegeResult.getBoardName().isEmpty()))
    {
        holder.board_name.setText(capitalize(""+collegeResult.getBoardName()+""));
    }
if (collegeResult.getRatings()!=null  )
    {
        holder.myRatingBar.setRating(collegeResult.getRatings());
    }


    }

    @Override
    public int getItemCount() {
        return collegeResultArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

CardView clgcard;
        TextView collegename,collegeaddress,college_type,board_name,approved_by,accreditation,isUniversity,placement,privatetv,rank,ownership,category;
RatingBar myRatingBar;
ImageView imgbg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myRatingBar=itemView.findViewById(R.id.myRatingBar);
            board_name=itemView.findViewById(R.id.board_name);
            imgbg=itemView.findViewById(R.id.imgbg);
clgcard=itemView.findViewById(R.id.clgcard);
            collegename=itemView.findViewById(R.id.collegename);
            accreditation=itemView.findViewById(R.id.accreditation);
            collegeaddress=itemView.findViewById(R.id.collegeaddress);
            category=itemView.findViewById(R.id.category);
            college_type=itemView.findViewById(R.id.college_type);
            ownership=itemView.findViewById(R.id.ownership);
            approved_by=itemView.findViewById(R.id.specialname);

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
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }
}
