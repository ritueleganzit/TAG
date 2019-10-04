package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eleganzit.tag.DiscussionActivity;
import com.eleganzit.tag.ForgotPassword;
import com.eleganzit.tag.R;
import com.eleganzit.tag.VerificationActivity;

public class ViewDiscussionAdapter extends RecyclerView.Adapter<ViewDiscussionAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    public ViewDiscussionAdapter(Context context) {

        this.context = context;
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
holder.view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        context.startActivity(new Intent(context, DiscussionActivity.class));
        ((Activity)context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ((Activity)context).finish();
    }
});
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView view;
RecyclerView rc_discussion_comment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.view);

        }
    }
}
