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
import com.eleganzit.tag.R;
import com.eleganzit.tag.SignUpActivity;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.LoginNodeResponse;
import com.eleganzit.tag.model.RegisterOtpSent;
import com.eleganzit.tag.model.SendOtpResponse;
import com.eleganzit.tag.model.VerifiedResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import me.philio.pinentry.PinEntryView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegVerificationActivity extends AppCompatActivity {
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
        userLoggedInSession=new UserLoggedInSession(RegVerificationActivity.this);

        progressDialog = new ProgressDialog(RegVerificationActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        vr_code.setOnPinEnteredListener(new PinEntryView.OnPinEnteredListener() {
            @Override
            public void onPinEntered(String pin) {
                pinentered=pin;

            }
        });
        code=getIntent().getStringExtra("code");
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

                    Toast.makeText(RegVerificationActivity.this, "Please Enter valid OTP", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    if (pinentered.equalsIgnoreCase(""+code))
                    {
                        setSignUp();
                    }
                    else
                    {
                        Toast.makeText(RegVerificationActivity.this, "InValid OTP", Toast.LENGTH_SHORT).show();
                    }

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
    public void sendotp(){
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("username", ""+data);
        paramObject.addProperty("type", "1");

        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<RegisterOtpSent> call=myInterface.sendregOtp(paramObject);
        call.enqueue(new Callback<RegisterOtpSent>() {
            @Override
            public void onResponse(Call<RegisterOtpSent> call, Response<RegisterOtpSent> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Toast.makeText(RegVerificationActivity.this, "Verification Code has been send to Email", Toast.LENGTH_SHORT).show();
code=response.body().getResponse().getOtp();
                    }
                    else
                    {
                        Toast.makeText(RegVerificationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterOtpSent> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(RegVerificationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


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
                      setSignUp();
                    }
                    else
                    {
                        Toast.makeText(RegVerificationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<VerifiedResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(RegVerificationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
finish();
    }


    public void  setSignUp(){
        progressDialog.show();


        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_email", ""+getIntent().getStringExtra("user_email"));
        paramObject.addProperty("first_name", ""+getIntent().getStringExtra("first_name"));
        paramObject.addProperty("last_name", ""+getIntent().getStringExtra("last_name"));
        paramObject.addProperty("mobile", ""+getIntent().getStringExtra("mobile"));
        paramObject.addProperty("location_lat", "23.0225");
        paramObject.addProperty("location_long", "72.5714");
        paramObject.addProperty("device_type", "android");
        paramObject.addProperty("device_token", "3pBNjvpqo:APA91bEE51saF1gwcK05-nGZAQOzvaxoGLvSq8hrIeKGjAPtkZye3MFvoMVX6ODz_c0ISDOyUItaXEjHyKW3Ojf_W_xHS5IgGbrMTH3Cf1c-W63vem5njqj98axr66zc6ArZAZpvmApW");
        paramObject.addProperty("nationality", ""+getIntent().getStringExtra("nationality"));
        paramObject.addProperty("password", ""+getIntent().getStringExtra("password"));


        Log.d("dataaaa",""+paramObject.toString());


        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<LoginNodeResponse> call=myInterface.doSignUP(paramObject);
        call.enqueue(new Callback<LoginNodeResponse>() {
            @Override
            public void onResponse(Call<LoginNodeResponse> call, Response<LoginNodeResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Log.d("dataaaa",""+response.body().getData().get(0).getFirstName());
                        Log.d("dataaaa",""+response.body().getData().get(0).getLastName());
                        Log.d("dataaaa",""+response.body().getData().get(0).getUserId());
                        Log.d("dataaaa",""+response.body().getData().get(0).getMobile());
                        Log.d("dataaaa",""+response.body().getData().get(0).getNationality());


                        Toast.makeText(RegVerificationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        userLoggedInSession.createSignUpSession(response.body().getData().get(0).getUserEmail()
                                ,""+response.body().getData().get(0).getUserId()
                                ,response.body().getData().get(0).getFirstName()
                                ,""
                                ,response.body().getData().get(0).getMobile());

                    }
                    else
                    {
                        Toast.makeText(RegVerificationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<LoginNodeResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("nyyhu",""+t.getMessage());


                Toast.makeText(RegVerificationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
