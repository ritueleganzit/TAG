package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eleganzit.tag.HelpFAQActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.askquestion.AskQuestionResponse;
import com.eleganzit.tag.model.askquestion.Datum;
import com.eleganzit.tag.model.askquestion.Userquestion;
import com.eleganzit.tag.model.unanswered.UnansweredQuestionsResponse;
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
    String id,photo,name;
    boolean clicked,click;
    ProgressDialog progressDialog;
    Context context;
    Activity activity;
    ArrayList<Datum> arrayList;
    public DiscussionAdapter(String name,String photo,String id,ArrayList<Datum> arrayList, Context context) {

        this.context = context;
        this.arrayList = arrayList;
        activity = (Activity) context;
        this.name = name;
        this.id = id;
        this.photo = photo;
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
        if (name!=null && !name.isEmpty())
        {
            holder.emailtv.setText(""+name);

        }

        if (userquestion.getQuestionText()!=null && !userquestion.getQuestionText().isEmpty())
        {
            holder.hidetxt.setText(userquestion.getQuestionText());

        }

        if (userquestion.getCreatedDate()!=null && !userquestion.getCreatedDate().isEmpty())
        {
            holder.created_date.setText(userquestion.getCreatedDate());

        }

            holder.viewcomment.setText("View Comments ("+userquestion.getAnsList().size()+")");



            holder.rc_discussion_comment.setAdapter(new CommentAdapter(userquestion.getAnsList(),context));

        Glide.with(context).load(photo).apply(new RequestOptions().circleCrop().error(R.drawable.user_shape).placeholder(R.drawable.user_shape)).into(holder.profilePic);

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

                if (holder.answer_edit.getText().toString().equalsIgnoreCase(""))

                {
                    Toast.makeText(context, "Please enter data", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addAnswer(""+userquestion.getUserId(),""+userquestion.getQuestionId(),"0",holder.answer_edit.getText().toString(),userquestion.getQuestionText());
                }

            }
        });
holder.cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addcomment.setVisibility(View.GONE);

            }
        });


        /*holder.dislikelin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(click) {

                    holder.ic_dislike.setImageDrawable(context.getDrawable(R.drawable.ic_dislike));

                    click=false;
                    likeDislikeQuestion(id,""+userquestion.getQuestionId(),"is_dislike","0");
                }
                else {


                    holder.ic_dislike.setImageDrawable(context.getDrawable(R.drawable.ic_dislike_user));
                    holder.likeimg.setImageDrawable(context.getDrawable(R.drawable.ic_like));

                    click=true;
                    likeDislikeQuestion(id,""+userquestion.getQuestionId(),"is_dislike","1");

                }


            }
        });  holder.likelin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(clicked) {

                    holder.likeimg.setImageDrawable(context.getDrawable(R.drawable.ic_like));
                    clicked=false;
                    likeDislikeQuestion(id,""+userquestion.getQuestionId(),"is_like","0");

                }
                else {
                    holder.ic_dislike.setImageDrawable(context.getDrawable(R.drawable.ic_dislike));

                    holder.likeimg.setImageDrawable(context.getDrawable(R.drawable.ic_like_user));

                    clicked=true;
                    likeDislikeQuestion(id,""+userquestion.getQuestionId(),"is_like","1");
                }

            }
        });*/
        holder.likelin.setVisibility(View.GONE);
        holder.dislikelin.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
ImageView likeimg,ic_dislike,profilePic;
TextView viewcomment,reply,emailtv,hidetxt,created_date,post,cancel_tv;
EditText answer_edit;
RecyclerView rc_discussion_comment;
LinearLayout addcomment,likelin,dislikelin;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic=itemView.findViewById(R.id.profilePic);
            ic_dislike=itemView.findViewById(R.id.ic_dislike);
            dislikelin=itemView.findViewById(R.id.dislikelin);
            likelin=itemView.findViewById(R.id.likelin);
            likeimg=itemView.findViewById(R.id.likeimg);
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

                        activity.startActivity(new Intent(context,AskAQuestionActivity.class));
                        activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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

      public void likeDislikeQuestion(String userid,String question_id,String liketype,String likestatus)
    {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        JsonObject jsonObject=new JsonObject();

        jsonObject.addProperty("user_id",""+userid);
        jsonObject.addProperty("question_id",""+question_id);
        if (liketype.equalsIgnoreCase("is_like"))
        {
            jsonObject.addProperty("is_like",likestatus);
        }
        else
        {
            jsonObject.addProperty("is_dislike",likestatus);

        }


        Log.d("sdfsff","---"+jsonObject);
        Call<UnansweredQuestionsResponse> call=myInterface.likeDislikeQuestion(jsonObject);
        call.enqueue(new Callback<UnansweredQuestionsResponse>() {
            @Override
            public void onResponse(Call<UnansweredQuestionsResponse> call, Response<UnansweredQuestionsResponse> response) {
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
            public void onFailure(Call<UnansweredQuestionsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
