package com.eleganzit.tag.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.DiscussionAdapter;
import com.eleganzit.tag.adapter.SubFragmentAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.askquestion.Datum;
import com.eleganzit.tag.model.askquestion.QuestionAnswerListResponse;
import com.eleganzit.tag.model.askquestion.UserQuestionListResponse;
import com.eleganzit.tag.model.askquestion.Userquestion;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AskAQuestionActivity extends AppCompatActivity {

    EditText question;
    TextView submit;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
RecyclerView rc_discussion;
ArrayList<Datum> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_aquestion);
        submit=findViewById(R.id.submit);
        userLoggedInSession = new UserLoggedInSession(AskAQuestionActivity.this);

        userLoggedInSession.checkLogin();
        question=findViewById(R.id.question);
        progressDialog = new ProgressDialog(AskAQuestionActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        rc_discussion=findViewById(R.id.rc_discussion);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.getText().toString().trim().equals("")) {


                    Toast.makeText(AskAQuestionActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();

                    question.requestFocus();

                }else
                {
                    askquestion();
                }
            }
        });
getQues();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

    private void getQues() {
        arrayList=new ArrayList<>();
        progressDialog.show();
        Log.d("asdad",""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<QuestionAnswerListResponse> call=myInterface.questionListByUser(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        call.enqueue(new Callback<QuestionAnswerListResponse>() {
            @Override
            public void onResponse(Call<QuestionAnswerListResponse> call, Response<QuestionAnswerListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        if (response.body().getData()!=null)
                        {
                            Log.d("asdad","sadad"+response.body().getData().size());


                            arrayList.addAll(response.body().getData());

                            rc_discussion.setAdapter(new DiscussionAdapter(arrayList,AskAQuestionActivity.this));

                        }

                    }
                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<QuestionAnswerListResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(AskAQuestionActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();


            }
        });
    }
    private void askquestion() {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        JsonObject jsonObject=new JsonObject();
        String pattern = "EEE, dd MMM yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        jsonObject.addProperty("user_id",""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        jsonObject.addProperty("question_text",""+question.getText().toString().trim());
        jsonObject.addProperty("created_date",""+date);
        Call<com.eleganzit.tag.model.askquestion.AskQuestionResponse> call=myInterface.askquestion(jsonObject);
call.enqueue(new Callback<com.eleganzit.tag.model.askquestion.AskQuestionResponse>() {
    @Override
    public void onResponse(Call<com.eleganzit.tag.model.askquestion.AskQuestionResponse> call, Response<com.eleganzit.tag.model.askquestion.AskQuestionResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {

            if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                Toast.makeText(AskAQuestionActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(AskAQuestionActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
            }

    @Override
    public void onFailure(Call<com.eleganzit.tag.model.askquestion.AskQuestionResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(AskAQuestionActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

    }
});


    }
}
