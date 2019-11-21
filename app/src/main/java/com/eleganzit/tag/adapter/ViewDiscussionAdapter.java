package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eleganzit.tag.DiscussionActivity;
import com.eleganzit.tag.ForgotPassword;
import com.eleganzit.tag.R;
import com.eleganzit.tag.VerificationActivity;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.discussion.Datum;
import com.eleganzit.tag.model.discussion.DiscussionListResponse;
import com.eleganzit.tag.model.unanswered.UnansweredQuestionsResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDiscussionAdapter extends RecyclerView.Adapter<ViewDiscussionAdapter.MyViewHolder>
{

    String id;
    boolean clicked,click;
    ProgressDialog progressDialog;
    Context context;
    Activity activity;
    List<Datum> data;
    public ViewDiscussionAdapter(String id,List<Datum> data,Context context) {

        this.context = context;
        this.data = data;
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
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewdiscussion_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        final Datum datum=data.get(i);
        holder.question_text.setText(datum.getQuestionText());
        holder.created_date.setText(datum.getCreatedDate());
        holder.likecount.setText(""+datum.getLikeCount());
        holder.dislikecount.setText(""+datum.getDislikeList());
       /* holder.like_count.setText(""+datum.getLikeCount());
        holder.dislike_list.setText(""+datum.getDislikeList());*/
        holder.first_name.setText(datum.getFirstName());
        Glide.with(context).load(datum.getPhoto()).apply(new RequestOptions().circleCrop().error(R.drawable.user_shape).placeholder(R.drawable.user_shape)).into(holder.profilePic);

        if (datum.getUserStatus().get(0).getIsDislike().toString().equalsIgnoreCase("1"))
        {
            holder.ic_dislike.setImageDrawable(context.getDrawable(R.drawable.ic_dislike_user));

        }
        else
        {
            holder.ic_dislike.setImageDrawable(context.getDrawable(R.drawable.ic_dislike));

        }

        Log.d("dfsdfs","---"+datum.getUserStatus().get(0).getIsLike());
        if (datum.getUserStatus().get(0).getIsLike().toString().equalsIgnoreCase("1"))
        {
            holder.likeimg.setImageDrawable(context.getDrawable(R.drawable.ic_like_user));

        }
        else
        {
            holder.likeimg.setImageDrawable(context.getDrawable(R.drawable.ic_like));

        }
        holder.view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        context.startActivity(new Intent(context, DiscussionActivity.class)
                .putExtra("mydata",  datum)
                .putParcelableArrayListExtra("answer", (ArrayList<? extends Parcelable>) datum.getAnsList())


        );
        ((Activity)context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
});

        holder.dislikelin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(click) {

                    holder.ic_dislike.setImageDrawable(context.getDrawable(R.drawable.ic_dislike));

                    click=false;
                    likeDislikeQuestion(id,""+datum.getQuestionId(),"is_dislike","0");
                }
                else {


                    holder.ic_dislike.setImageDrawable(context.getDrawable(R.drawable.ic_dislike_user));
                    holder.likeimg.setImageDrawable(context.getDrawable(R.drawable.ic_like));

                    click=true;
                    likeDislikeQuestion(id,""+datum.getQuestionId(),"is_dislike","1");

                }


            }
        });  holder.likelin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(clicked) {

                    holder.likeimg.setImageDrawable(context.getDrawable(R.drawable.ic_like));
                    clicked=false;
                    likeDislikeQuestion(id,""+datum.getQuestionId(),"is_like","0");

                }
                else {
                    holder.ic_dislike.setImageDrawable(context.getDrawable(R.drawable.ic_dislike));

                    holder.likeimg.setImageDrawable(context.getDrawable(R.drawable.ic_like_user));

                    clicked=true;
                    likeDislikeQuestion(id,""+datum.getQuestionId(),"is_like","1");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView likeimg,ic_dislike,profilePic;

TextView view,question_text,first_name,created_date,dislikecount,likecount;
RecyclerView rc_discussion_comment;
LinearLayout likelin,dislikelin;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic=itemView.findViewById(R.id.profilePic);
            likecount=itemView.findViewById(R.id.likecount);
            ic_dislike=itemView.findViewById(R.id.ic_dislike);
            dislikecount=itemView.findViewById(R.id.dislikecount);
            dislikelin=itemView.findViewById(R.id.dislikelin);
            likelin=itemView.findViewById(R.id.likelin);
            likeimg=itemView.findViewById(R.id.likeimg);
            view=itemView.findViewById(R.id.view);
            //dislike_list=itemView.findViewById(R.id.dislike_list);
            question_text=itemView.findViewById(R.id.question_text);
            created_date=itemView.findViewById(R.id.created_date);
            //like_count=itemView.findViewById(R.id.like_count_txt);
            first_name=itemView.findViewById(R.id.first_name);

        }
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

                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        viewQuestions();
//notifyDataSetChanged();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UnansweredQuestionsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewQuestions()
    {
        data=new ArrayList<>();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<DiscussionListResponse> call=myInterface.discussionList(Integer.parseInt(id));
        call.enqueue(new Callback<DiscussionListResponse>() {
            @Override
            public void onResponse(Call<DiscussionListResponse> call, Response<DiscussionListResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        if (response.body().getData()!=null)
                        {
                            data.addAll(response.body().getData());
                            notifyDataSetChanged();

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DiscussionListResponse> call, Throwable t) {

            }
        });

    }
}
