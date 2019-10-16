package com.eleganzit.tag;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.LoginNodeResponse;
import com.eleganzit.tag.model.LoginResponse;
import com.eleganzit.tag.model.SendOtpResponse;
import com.eleganzit.tag.model.VerifiedResponse;
import com.google.gson.JsonObject;

import me.philio.pinentry.PinEntryView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity {
PinEntryView vr_code;
TextView submit,resend;
    ProgressDialog progressDialog;

    String pinentered="",data="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        vr_code=findViewById(R.id.vr_code);
        data=getIntent().getStringExtra("data");
        submit=findViewById(R.id.submit);
        resend=findViewById(R.id.resend);
        progressDialog = new ProgressDialog(VerificationActivity.this);
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

                    Toast.makeText(VerificationActivity.this, "Please Enter valid OTP", Toast.LENGTH_SHORT).show();
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
    }
    public void sendotp(){
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("username", ""+data);

        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<SendOtpResponse> call=myInterface.sendOtp(paramObject);
        call.enqueue(new Callback<SendOtpResponse>() {
            @Override
            public void onResponse(Call<SendOtpResponse> call, Response<SendOtpResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Toast.makeText(VerificationActivity.this, "Verification Code has been send to Registered Email", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(VerificationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<SendOtpResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(VerificationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
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
                        startActivity(new Intent(VerificationActivity.this, ChangePasswordActivity.class)
                        .putExtra("user_id",""+response.body().getUserId()));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(VerificationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<VerifiedResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(VerificationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
finish();
    }
}
