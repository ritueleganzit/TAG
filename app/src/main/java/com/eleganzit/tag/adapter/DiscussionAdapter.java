package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eleganzit.tag.HelpFAQActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.model.askquestion.Userquestion;

import java.util.ArrayList;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    ArrayList<Userquestion> arrayList;
    public DiscussionAdapter(ArrayList<Userquestion> arrayList,Context context) {

        this.context = context;
        this.arrayList = arrayList;
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

        Userquestion userquestion=arrayList.get(i);
        holder.emailtv.setText(userquestion.getFirstName());
        holder.hidetxt.setText(userquestion.getQuestionText());
        holder.created_date.setText(userquestion.getCreatedDate());
        holder.rc_discussion_comment.setAdapter(new CommentAdapter(context));

        holder.viewcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rc_discussion_comment.setVisibility((holder.rc_discussion_comment.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

                if (holder.rc_discussion_comment.getVisibility()==View.VISIBLE)
                {
                    holder.viewcomment.setText("Hide Comments");

                }
                else
                {
                holder.viewcomment.setText("View Comments");


                }


            }
        }); holder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addcomment.setVisibility((holder.addcomment.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
                /*if (holder.reply.getText().toString().equalsIgnoreCase("Add Answer"))
                {
                    holder.addcomment.setVisibility(View.VISIBLE);

                }
                else
                {
                    holder.addcomment.setVisibility(View.GONE);
                }*/

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView viewcomment,reply,emailtv,hidetxt,created_date;
EditText answer_edit;
RecyclerView rc_discussion_comment;
LinearLayout addcomment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            emailtv=itemView.findViewById(R.id.emailtv);
            answer_edit=itemView.findViewById(R.id.answer_edit);
            hidetxt=itemView.findViewById(R.id.hidetxt);
            created_date=itemView.findViewById(R.id.created_date);
            viewcomment=itemView.findViewById(R.id.viewcomment);
            reply=itemView.findViewById(R.id.reply);
            rc_discussion_comment=itemView.findViewById(R.id.rc_discussion_comment);
            addcomment=itemView.findViewById(R.id.addcomment);


        }
    }
}
