package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.model.askquestion.AnsList;
import com.eleganzit.tag.model.askquestion.ReplyList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    List<ReplyList> ansLists;
    public ReplyAdapter(List<ReplyList> ansLists, Context context) {

        this.ansLists = ansLists;
        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reply_roww,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        ReplyList ans=ansLists.get(i);
        //holder.answer_tv.setText(ans.getAnsText());
        holder.answer_tv.setText(Html.fromHtml("<font color=#000000> <b>" + ans.getFirstName() + " </b> </font> &nbsp;" +""+ans.getAnsText()));

        String pattern = "EEE, dd MMM yyyy hh:mm:ss";

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        if (ans.getCreatedDate()!=null && !ans.getCreatedDate().isEmpty())
        {
            try {
                long time = sdf.parse(ans.getCreatedDate()).getTime();
                long now = System.currentTimeMillis();

                CharSequence ago =
                        DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);

                if (ago.toString().equalsIgnoreCase("0 minutes ago"))
                {
                    holder.comment_time_posted.setText("Just Now");
                }
                else
                {
                    holder.comment_time_posted.setText(ago+"");

                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int getItemCount() {
        return ansLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView answer_tv,comment_time_posted;
RecyclerView rc__replies;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            answer_tv=itemView.findViewById(R.id.answer_tv);
            comment_time_posted=itemView.findViewById(R.id.comment_time_posted);
            rc__replies=itemView.findViewById(R.id.rc__replies);


        }
    }
}
