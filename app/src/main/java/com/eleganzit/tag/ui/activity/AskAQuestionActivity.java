package com.eleganzit.tag.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.HomeActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.SignUpActivity;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AskQuestionResponse;
import com.eleganzit.tag.model.LoginResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AskAQuestionActivity extends AppCompatActivity {

    EditText question;
    TextView submit;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;

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

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
    private void askquestion() {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<AskQuestionResponse> call=myInterface.askquestion("ask_question",""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID),""+question.getText().toString());
call.enqueue(new Callback<AskQuestionResponse>() {
    @Override
    public void onResponse(Call<AskQuestionResponse> call, Response<AskQuestionResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {

            if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                Toast.makeText(AskAQuestionActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(AskAQuestionActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
            }

    @Override
    public void onFailure(Call<AskQuestionResponse> call, Throwable t) {
        Toast.makeText(AskAQuestionActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

    }
});


    }
}
