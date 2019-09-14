package com.eleganzit.tag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.LoginResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    TextView login_signup,forgot,txtlogin,termsandcondition;
    CheckBox cbprivacy,rememberme;
    EditText login_emailid,login_password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        sharedPreferences=getSharedPreferences("rememberme",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        login_signup=findViewById(R.id.login_signup);
        cbprivacy=findViewById(R.id.cbprivacy);
        rememberme=findViewById(R.id.rememberme);
        login_emailid=findViewById(R.id.login_emailid);
        termsandcondition=findViewById(R.id.termsandcondition);
        txtlogin=findViewById(R.id.txtlogin);
        login_password=findViewById(R.id.login_password);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        forgot=findViewById(R.id.forgot);
        if (sharedPreferences.getString("remember","").equalsIgnoreCase(""))
        {
            login_emailid.setText("");
            rememberme.setChecked(false);
        }
        else
        {
            rememberme.setChecked(true);
            login_emailid.setText(""+sharedPreferences.getString("remember",""));

        }

        termsandcondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, TermsAndCondition.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
               // overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


            }
        });
        login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                //finish();
            }
        });
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid())
                {
                    if (cbprivacy.isChecked())
                    {
                        if (rememberme.isChecked())
                        {
                            editor.putString("remember",""+login_emailid.getText().toString());
                            editor.commit();
                        }
                        else
                        {
                            editor.clear();
                            editor.commit();
                        }
                        setLogin();                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Please agree the terms and condition", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }


    public boolean isValid() {

        if (login_emailid.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show();

            login_emailid.requestFocus();

            return false;
        }
        else if (login_password.getText().toString().trim().equals("") || login_password.getText().toString().trim().length() < 6) {
            Toast.makeText(this, "Password must contain atleast 6 characters", Toast.LENGTH_SHORT).show();



            login_password.requestFocus();

            return false;
        }

        return true;
    }

    public void setLogin(){
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<LoginResponse> call=myInterface.doLogin(login_emailid.getText().toString(),login_password.getText().toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Toast.makeText(LoginActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
