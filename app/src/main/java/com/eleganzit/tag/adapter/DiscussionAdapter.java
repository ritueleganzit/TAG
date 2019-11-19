package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.HelpFAQActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.askquestion.AskQuestionResponse;
import com.eleganzit.tag.model.askquestion.Datum;
import com.eleganzit.tag.model.askquestion.Userquestion;
import com.eleganzit.tag.ui.activity.AskAQuestionActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.MyViewHolder>
{
    String id;
    ProgressDialog progressDialog;
    Context context;
    Activity activity;
    ArrayList<Datum> arrayList;
    public DiscussionAdapter(ArrayList<Datum> arrayList, Context context) {

        this.context = context;
        this.arrayList = arrayList;
        activity = (Activity) context;
        this.id = id;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
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

        final Datum userquestion=arrayList.get(i);
        holder.emailtv.setText("Name");
        holder.hidetxt.setText(userquestion.getQuestionText());
        holder.created_date.setText(userquestion.getCreatedDate());
        holder.rc_discussion_comment.setAdapter(new CommentAdapter(userquestion.getAnsList(),context));
        holder.viewcomment.setText("View Comments ("+userquestion.getAnsList().size()+")");
        holder.viewcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userquestion.getAnsList().size()>0){
                    holder.rc_discussion_comment.setVisibility((holder.rc_discussion_comment.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);

                    if (holder.rc_discussion_comment.getVisibility()==View.VISIBLE)
                    {
                        holder.viewcomment.setText("Hide Comments ("+userquestion.getAnsList().size()+")" );

                    }
                    else
                    {
                        holder.viewcomment.setText("View Comments ("+userquestion.getAnsList().size()+")" );


                    }
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

        holder.post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnswer(""+userquestion.getUserId(),""+userquestion.getQuestionId(),"0",holder.answer_edit.getText().toString(),userquestion.getQuestionText());
            }
        });
holder.cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addcomment.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView viewcomment,reply,emailtv,hidetxt,created_date,post,cancel_tv;
EditText answer_edit;
RecyclerView rc_discussion_comment;
LinearLayout addcomment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cancel_tv=itemView.findViewById(R.id.cancel_tv);
            post=itemView.findViewById(R.id.post);
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
    public void addAnswer(String userid,String question_id,String parent_id,String ans_text,String ques_txt)
    {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        JsonObject jsonObject=new JsonObject();
        String pattern = "EEE, dd MMM yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        jsonObject.addProperty("user_id",""+userid);
        jsonObject.addProperty("question_id",""+question_id);
        jsonObject.addProperty("parent_id",""+parent_id);
        jsonObject.addProperty("ans_text",""+ans_text);
        jsonObject.addProperty("question_text",""+ques_txt);
        jsonObject.addProperty("created_date",""+date);

        Log.d("sdfsff","---"+jsonObject);
        Call<AskQuestionResponse> call=myInterface.addAnswer(jsonObject);
        call.enqueue(new Callback<AskQuestionResponse>() {
            @Override
            public void onResponse(Call<com.eleganzit.tag.model.askquestion.AskQuestionResponse> call, Response<AskQuestionResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        activity.finish();
                    }
                    else
                    {
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<com.eleganzit.tag.model.askquestion.AskQuestionResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
