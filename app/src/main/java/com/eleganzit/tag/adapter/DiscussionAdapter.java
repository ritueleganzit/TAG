package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eleganzit.tag.HelpFAQActivity;
import com.eleganzit.tag.R;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    public DiscussionAdapter(Context context) {

        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.discussion_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        holder.rc_discussion_comment.setAdapter(new CommentAdapter(context));

        holder.viewcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rc_discussion_comment.setVisibility((holder.rc_discussion_comment.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

                if (holder.rc_discussion_comment.getVisibility()==View.VISIBLE)
                {
                    holder.viewcomment.setText("Hide Comments");
                    holder.reply.setText("Add Reply");
                }
                else
                {           holder.reply.setText("Reply");         holder.viewcomment.setText("View Comments");


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView viewcomment,reply;
RecyclerView rc_discussion_comment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewcomment=itemView.findViewById(R.id.viewcomment);
            reply=itemView.findViewById(R.id.reply);
            rc_discussion_comment=itemView.findViewById(R.id.rc_discussion_comment);


        }
    }
}
