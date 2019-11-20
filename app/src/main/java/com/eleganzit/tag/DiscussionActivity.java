package com.eleganzit.tag;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.adapter.CommentAdapter;
import com.eleganzit.tag.adapter.DisCommentAdapter;
import com.eleganzit.tag.adapter.DiscussionAdapter;
import com.eleganzit.tag.adapter.HelpAdapter;
import com.eleganzit.tag.adapter.ViewDiscussionAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.askquestion.AskQuestionResponse;
import com.eleganzit.tag.model.discussion.AnsList;
import com.eleganzit.tag.model.discussion.Datum;
import com.eleganzit.tag.model.discussion.DiscussionListResponse;
import com.eleganzit.tag.model.discussion.ReplyList;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscussionActivity extends AppCompatActivity {
RecyclerView rc_discussion;
Datum datum;
ArrayList<AnsList> ansListsss;
    UserLoggedInSession userLoggedInSession;
    TextView viewcomment,reply,emailtv,hidetxt,created_date,post,cancel_tv;
    EditText answer_edit;
    RecyclerView rc_discussion_comment;
    LinearLayout addcomment;


    ProgressDialog progressDialog;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_discussion);
    userLoggedInSession = new UserLoggedInSession(DiscussionActivity.this);

    userLoggedInSession.checkLogin();
    datum=getIntent().getParcelableExtra("mydata");
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    progressDialog = new ProgressDialog(DiscussionActivity.this);
    progressDialog.setMessage("Please Wait");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    hidetxt=findViewById(R.id.hidetxt);
    viewcomment=findViewById(R.id.viewcomment);
    created_date=findViewById(R.id.created_date);
    reply=findViewById(R.id.reply);
    answer_edit=findViewById(R.id.answer_edit);
    cancel_tv=findViewById(R.id.cancel_tv);
    post=findViewById(R.id.post);
    addcomment=findViewById(R.id.addcomment);
    hidetxt=findViewById(R.id.hidetxt);
    emailtv=findViewById(R.id.emailtv);
    emailtv.setText(datum.getFirstName());
    hidetxt.setText(datum.getQuestionText());
    created_date.setText(datum.getCreatedDate());
        rc_discussion=findViewById(R.id.rc_discussion);


    discussionByQuesId();
    viewcomment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (ansListsss!=null){
                if (ansListsss.size()>0)
                {
                    rc_discussion.setVisibility((rc_discussion.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);

                }
                else
                {
                    viewcomment.setText("View Comment (0)");
                }

            }

        }
    });
    reply.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addcomment.setVisibility((addcomment.getVisibility() == View.VISIBLE)
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
    post.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addAnswer(""+datum.getUserId(),""+datum.getQuestionId(),"0",answer_edit.getText().toString(),datum.getQuestionText());
        }
    });
    cancel_tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addcomment.setVisibility(View.GONE);

        }
    });




}@Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
                        Toast.makeText(DiscussionActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(DiscussionActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<com.eleganzit.tag.model.askquestion.AskQuestionResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DiscussionActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public  void discussionByQuesId()
    {
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        paramObject.addProperty("question_id",""+datum.getQuestionId());


        Log.d("dfsfs",""+paramObject.toString());
        ansListsss=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<DiscussionListResponse> call=myInterface.discussionByQuesId(paramObject);
        call.enqueue(new Callback<DiscussionListResponse>() {
            @Override
            public void onResponse(Call<DiscussionListResponse> call, Response<DiscussionListResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        if (response.body().getData()!=null)
                        {



                            ansListsss.addAll(response.body().getData().get(0).getAnsList());
                                Log.d("fghfhf",""+response.body().getData().get(0).getAnsList());


                            rc_discussion.setAdapter(new DisCommentAdapter(ansListsss,DiscussionActivity.this));
                            if (ansListsss!=null) {
                                if (ansListsss.size() > 0) {
                                    viewcomment.setText("View Comment ("+ansListsss.size()+")");
                                }
                            }

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DiscussionListResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DiscussionActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
