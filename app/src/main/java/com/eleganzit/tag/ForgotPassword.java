package com.eleganzit.tag;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.LoginResponse;
import com.eleganzit.tag.model.SendOtpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {
    ProgressDialog progressDialog;


    EditText login_emailid;
    TextView submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        submit=findViewById(R.id.submit);
        login_emailid=findViewById(R.id.login_emailid);
        progressDialog = new ProgressDialog(ForgotPassword.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid())
                {
                    sendotp();

                }

            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
    public void sendotp(){
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<SendOtpResponse> call=myInterface.sendOtp(login_emailid.getText().toString());
        call.enqueue(new Callback<SendOtpResponse>() {
            @Override
            public void onResponse(Call<SendOtpResponse> call, Response<SendOtpResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        startActivity(new Intent(ForgotPassword.this, VerificationActivity.class).putExtra("data",login_emailid.getText().toString()));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(ForgotPassword.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<SendOtpResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(ForgotPassword.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public boolean isValid() {

        if (login_emailid.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show();

            login_emailid.requestFocus();

            return false;
        }

        return true;
    }
}
