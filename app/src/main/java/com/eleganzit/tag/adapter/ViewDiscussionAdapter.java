package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eleganzit.tag.DiscussionActivity;
import com.eleganzit.tag.ForgotPassword;
import com.eleganzit.tag.R;
import com.eleganzit.tag.VerificationActivity;
import com.eleganzit.tag.model.discussion.Datum;

import java.util.ArrayList;
import java.util.List;

public class ViewDiscussionAdapter extends RecyclerView.Adapter<ViewDiscussionAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    List<Datum> data;
    public ViewDiscussionAdapter(List<Datum> data,Context context) {

        this.context = context;
        this.data = data;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewdiscussion_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        final Datum datum=data.get(i);
        holder.question_text.setText(datum.getQuestionText());
        holder.created_date.setText(datum.getCreatedDate());
       /* holder.like_count.setText(""+datum.getLikeCount());
        holder.dislike_list.setText(""+datum.getDislikeList());*/
        holder.first_name.setText(datum.getFirstName());
holder.view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.d("dfsdfs","da"+datum.getAnsList().size());

        context.startActivity(new Intent(context, DiscussionActivity.class)
                .putExtra("mydata",  datum)
                .putParcelableArrayListExtra("answer", (ArrayList<? extends Parcelable>) datum.getAnsList())
                .putParcelableArrayListExtra("reply", (ArrayList<? extends Parcelable>) datum.getAnsList().get(i).getReplyList())

        );
        ((Activity)context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
});
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView view,question_text,first_name,created_date,like_count,dislike_list;
RecyclerView rc_discussion_comment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.view);
            //dislike_list=itemView.findViewById(R.id.dislike_list);
            question_text=itemView.findViewById(R.id.question_text);
            created_date=itemView.findViewById(R.id.created_date);
            //like_count=itemView.findViewById(R.id.like_count_txt);
            first_name=itemView.findViewById(R.id.first_name);

        }
    }
}
