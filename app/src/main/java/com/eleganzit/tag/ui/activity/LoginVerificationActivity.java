package com.eleganzit.tag.ui.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.ChangePasswordActivity;
import com.eleganzit.tag.LoginActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.VerificationActivity;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.LoginNodeResponse;
import com.eleganzit.tag.model.RegisterOtpSent;
import com.eleganzit.tag.model.VerifiedResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import me.philio.pinentry.PinEntryView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginVerificationActivity extends AppCompatActivity {
PinEntryView vr_code;
TextView submit,resend;
UserLoggedInSession userLoggedInSession;
    ProgressDialog progressDialog;

    String pinentered="",data="",code="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        vr_code=findViewById(R.id.vr_code);
        data=getIntent().getStringExtra("user_email");
        submit=findViewById(R.id.submit);
        resend=findViewById(R.id.resend);
        userLoggedInSession=new UserLoggedInSession(LoginVerificationActivity.this);

        progressDialog = new ProgressDialog(LoginVerificationActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        vr_code.setOnPinEnteredListener(new PinEntryView.OnPinEnteredListener() {
            @Override
            public void onPinEntered(String pin) {
                pinentered=pin;

            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendotp();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinentered.equals("") || pinentered.length()<4) {

                    Toast.makeText(LoginVerificationActivity.this, "Please Enter valid OTP", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    matchOtp();

                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        sendotp();
    }
    private void matchOtp() {
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("username", ""+data);
        paramObject.addProperty("sentcode", ""+pinentered);

        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<VerifiedResponse> call=myInterface.checkcode(paramObject);
        call.enqueue(new Callback<VerifiedResponse>() {
            @Override
            public void onResponse(Call<VerifiedResponse> call, Response<VerifiedResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        setLogin();
                    }
                    else
                    {
                        Toast.makeText(LoginVerificationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<VerifiedResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(LoginVerificationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void sendotp(){
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("username", ""+data);
        paramObject.addProperty("type", "2");

        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<RegisterOtpSent> call=myInterface.sendregOtp(paramObject);
        call.enqueue(new Callback<RegisterOtpSent>() {
            @Override
            public void onResponse(Call<RegisterOtpSent> call, Response<RegisterOtpSent> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Toast.makeText(LoginVerificationActivity.this, "Verification Code has been send to Email", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(LoginVerificationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterOtpSent> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(LoginVerificationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void setLogin(){
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_name", ""+getIntent().getStringExtra("user_email"));
        paramObject.addProperty("password", ""+getIntent().getStringExtra("password"));
        paramObject.addProperty("device_type", "android");
        paramObject.addProperty("location_lat", "23.0810287");
        paramObject.addProperty("location_long", "72.5768002");
        paramObject.addProperty("device_token", "dE2pBNjvpqo:APA91bEE51saF1gwcK05-nGZAQOzvaxoGLvSq8hrIeKGjAPtkZye3MFvoMVX6ODz_c0ISDOyUItaXEjHyKW3Ojf_W_xHS5IgGbrMTH3Cf1c-W63vem5njqj98axr66zc6ArZAZpvmApW");


        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<LoginNodeResponse> call=myInterface.doLogin(paramObject);
        call.enqueue(new Callback<LoginNodeResponse>() {
            @Override
            public void onResponse(Call<LoginNodeResponse> call, Response<LoginNodeResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Log.d("MYDATA",""+response.body().getData().get(0).getFirstName());
                        Toast.makeText(LoginVerificationActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();

                        userLoggedInSession.createLoginSession(response.body().getData().get(0).getUserEmail()
                                ,""+response.body().getData().get(0).getUserId()
                                ,response.body().getData().get(0).getFirstName()
                                ,""
                                ,response.body().getData().get(0).getMobile());

                    }
                    else
                    {
                        Toast.makeText(LoginVerificationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<LoginNodeResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(LoginVerificationActivity.this, "Server and Internet Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
