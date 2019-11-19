package com.eleganzit.tag;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.adapter.DiscussionAdapter;
import com.eleganzit.tag.adapter.ViewDiscussionAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.GetCoursesResponse;
import com.eleganzit.tag.model.discussion.Datum;
import com.eleganzit.tag.model.discussion.DiscussionListResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewQuestionsActivity extends AppCompatActivity {
    RecyclerView rc_discussion;

    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
ArrayList<Datum> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);
        rc_discussion=findViewById(R.id.rc_discussion);
        userLoggedInSession = new UserLoggedInSession(ViewQuestionsActivity.this);

        userLoggedInSession.checkLogin();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        progressDialog = new ProgressDialog(ViewQuestionsActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        viewQuestions();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
    public void viewQuestions()
    {
        arrayList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<DiscussionListResponse> call=myInterface.discussionList(Integer.parseInt(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID)));
        call.enqueue(new Callback<DiscussionListResponse>() {
            @Override
            public void onResponse(Call<DiscussionListResponse> call, Response<DiscussionListResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                            if (response.body().getData()!=null)
                            {
                                rc_discussion.setAdapter(new ViewDiscussionAdapter(response.body().getData(),ViewQuestionsActivity.this));

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
