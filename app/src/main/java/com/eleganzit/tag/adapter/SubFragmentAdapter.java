package com.eleganzit.tag.adapter;

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
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.model.QuestionData;
import com.eleganzit.tag.model.askquestion.Userquestion;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;
import com.eleganzit.tag.utils.TimeAgo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.WINDOW_SERVICE;

public class SubFragmentAdapter extends RecyclerView.Adapter<SubFragmentAdapter.MyViewHolder>
{
    ArrayList<Userquestion> arrayList;

    Context context;
    Activity activity;
    public SubFragmentAdapter(ArrayList<Userquestion> arrayList, Context context) {

        this.context = context;
        this.arrayList = arrayList;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subactivity_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        Userquestion questionData=arrayList.get(i);

if (questionData.getCreatedDate()!=null && !(questionData.getCreatedDate().isEmpty()))
{
    DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss");
    try {
        Date date = (Date)formatter.parse(questionData.getCreatedDate());
        String timeAgo = TimeAgo.getTimeAgo(date.getTime());
        holder.created_date.setText(""+timeAgo);


    } catch (ParseException e) {
        e.printStackTrace();
    }
}if (questionData.getQuestionText()!=null && !(questionData.getQuestionText().isEmpty()))
{
    holder.question_text.setText(""+questionData.getQuestionText());
}



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

CardView clgcard;
ImageView imgbg;
TextView question_text,created_date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbg=itemView.findViewById(R.id.imgbg);
            question_text=itemView.findViewById(R.id.question_text);
            created_date=itemView.findViewById(R.id.created_date);
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
