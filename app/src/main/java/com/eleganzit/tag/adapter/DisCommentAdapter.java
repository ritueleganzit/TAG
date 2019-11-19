package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.askquestion.AnsList;
import com.eleganzit.tag.model.askquestion.AskQuestionResponse;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisCommentAdapter extends RecyclerView.Adapter<DisCommentAdapter.MyViewHolder>
{

    Context context;
    Activity activity;
    List<com.eleganzit.tag.model.discussion.AnsList> ansLists;
    List<com.eleganzit.tag.model.discussion.ReplyList> reply;
    ProgressDialog progressDialog;

    public DisCommentAdapter(List<com.eleganzit.tag.model.discussion.ReplyList> reply,List<com.eleganzit.tag.model.discussion.AnsList> ansLists, Context context) {

        this.ansLists = ansLists;
        this.reply = reply;
        this.context = context;
        activity = (Activity) context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        final com.eleganzit.tag.model.discussion.AnsList ans=ansLists.get(i);
        holder.answer_tv.setText(Html.fromHtml("<font color=#000000> <b>" + ans.getFirstName() + " </b> </font> &nbsp;" +""+ans.getAnsText()));

          holder.rc__replies.setAdapter(new DisReplyAdapter(reply,context));
       holder.viewcomment.setText("Replies ("+reply.size()+")");

        holder.replytv.setOnClickListener(new View.OnClickListener() {
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
                addAnswer(""+ans.getSubmitedBy(),""+ans.getQuestionId(),""+ans.getAnsId(),holder.answer_edit.getText().toString(),ans.getAnsText());
            }
        });
        holder.cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addcomment.setVisibility(View.GONE);

            }
        });
holder.viewcomment.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        holder.rc__replies.setVisibility((holder.rc__replies.getVisibility() == View.VISIBLE)
                ? View.GONE : View.VISIBLE);
    }
});
    }

    @Override
    public int getItemCount() {
        return ansLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView answer_tv,viewcomment,replytv,post,cancel_tv;;
RecyclerView rc__replies;
        EditText answer_edit;

        LinearLayout addcomment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            answer_edit=itemView.findViewById(R.id.answer_edit);
            replytv=itemView.findViewById(R.id.replytv);
            answer_tv=itemView.findViewById(R.id.answer_tv);
            rc__replies=itemView.findViewById(R.id.rc__replies);
            viewcomment=itemView.findViewById(R.id.viewcomment);
            addcomment=itemView.findViewById(R.id.addcomment);
            cancel_tv=itemView.findViewById(R.id.cancel_tv);
            post=itemView.findViewById(R.id.post);

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
            public void onResponse(Call<AskQuestionResponse> call, Response<AskQuestionResponse> response) {
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
            public void onFailure(Call<AskQuestionResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
