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

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;

import static android.content.Context.WINDOW_SERVICE;

public class CollegePredictorAdapter extends RecyclerView.Adapter<CollegePredictorAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    public CollegePredictorAdapter(Context context) {

        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_collegepredictor,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

     /*   holder.clgcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CollegeDetailActivity.class));

            }
        });
        holder.imgbg.getLayoutParams().width= (int) (getScreenWidthInPXs(context,(Activity) context));
        holder.imgbg.getLayoutParams().height= (int) (getScreenWidthInPXs(context,(Activity) context)/3.3);
*/


    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

CardView clgcard;
ImageView imgbg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
         //   imgbg=itemView.findViewById(R.id.imgbg);
//clgcard=itemView.findViewById(R.id.clgcard);

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
