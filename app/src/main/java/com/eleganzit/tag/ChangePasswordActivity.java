package com.eleganzit.tag;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.LoginResponse;
import com.eleganzit.tag.model.ResetPasswordResponse;
import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText ed_new_password,ed_cnf_password;
    TextView submit;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ed_new_password=findViewById(R.id.ed_new_password);
        progressDialog = new ProgressDialog(ChangePasswordActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        submit=findViewById(R.id.submit);
        ed_cnf_password=findViewById(R.id.ed_cnf_password);
        user_id=getIntent().getStringExtra("user_id");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (isValid())
                    {
                        changepassword();                    }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void changepassword() {

            progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", ""+user_id);
        paramObject.addProperty("new_password", ""+ed_new_password.getText().toString());

        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
            Call<ResetPasswordResponse> call=myInterface.get_restpassword(paramObject);
            call.enqueue(new Callback<ResetPasswordResponse>() {
                @Override
                public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                            Toast.makeText(ChangePasswordActivity.this, "Password Changed Sucessfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));

                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(ChangePasswordActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                    progressDialog.dismiss();

                    Toast.makeText(ChangePasswordActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
                }
            });
        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    public boolean isValid() {
         if (ed_new_password.getText().toString().trim().equals("") || ed_new_password.getText().toString().trim().length() < 8) {
            Toast.makeText(this, "Password must contain atleast 8 characters", Toast.LENGTH_SHORT).show();


             ed_new_password.requestFocus();

            return false;
        }
         else if (!(ed_cnf_password.getText().toString().equalsIgnoreCase(ed_new_password.getText().toString())))
         {
             Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();

             ed_cnf_password.requestFocus();

             return false;
         }


        return true;
    }

}
