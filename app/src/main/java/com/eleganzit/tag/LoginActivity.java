package com.eleganzit.tag;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.LoginNodeResponse;
import com.eleganzit.tag.model.LoginResponse;
import com.eleganzit.tag.utils.HideKeyBoard;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    TextView login_signup,forgot,txtlogin,termsandcondition,privacy;
    CheckBox cbprivacy,rememberme;
    EditText login_emailid,login_password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    UserLoggedInSession userLoggedInSession;
    CardView cardviewhide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        sharedPreferences=getSharedPreferences("rememberme",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        userLoggedInSession=new UserLoggedInSession(LoginActivity.this);
        cardviewhide=findViewById(R.id.cardviewhide);
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
            login_password.setText(""+sharedPreferences.getString("rememberpassword",""));

        }

        HideKeyBoard.setupUI(cardviewhide,LoginActivity.this);

        SpannableString SpanString = new SpannableString(
                "I agree Privacy policy & Terms Conditions");

        ClickableSpan teremsAndCondition = new ClickableSpan() {
            @Override
            public void onClick(View textView) {


                Intent mIntent = new Intent(LoginActivity.this, TermsAndCondition.class);
                mIntent.putExtra("isTermsAndCondition", true);
                startActivity(mIntent);

            }
        };

        // Character starting from 32 - 45 is Terms and condition.
        // Character starting from 49 - 63 is privacy policy.

        ClickableSpan privacy = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

                Intent mIntent = new Intent(LoginActivity.this, PrivacyPolicy.class);
                mIntent.putExtra("isPrivacyPolicy", true);
                startActivity(mIntent);

            }
        };

        SpanString.setSpan(teremsAndCondition, 25, 41, 0);
        SpanString.setSpan(privacy, 8, 22, 0);
      //  SpanString.setSpan(new ForegroundColorSpan(Color.BLUE), 25, 40, 0);
        //SpanString.setSpan(new ForegroundColorSpan(Color.BLUE), 8, 22, 0);
        SpanString.setSpan(new UnderlineSpan(), 25, 41, 0);
        SpanString.setSpan(new UnderlineSpan(), 8, 22, 0);

        termsandcondition.setMovementMethod(LinkMovementMethod.getInstance());
        termsandcondition.setText(SpanString, TextView.BufferType.SPANNABLE);
        termsandcondition.setSelected(true);


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
                            editor.putString("rememberpassword",""+login_password.getText().toString());
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
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_name", ""+login_emailid.getText().toString());
        paramObject.addProperty("password", ""+login_password.getText().toString());

        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<LoginNodeResponse> call=myInterface.doLogin(paramObject);
        call.enqueue(new Callback<LoginNodeResponse>() {
            @Override
            public void onResponse(Call<LoginNodeResponse> call, Response<LoginNodeResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Log.d("MYDATA",""+response.body().getData().getName());
                        Toast.makeText(LoginActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();

                       userLoggedInSession.createLoginSession(response.body().getData().getUserEmail()
                       ,""+response.body().getData().getUserId()
                       ,response.body().getData().getName()
                       ,""
                       ,response.body().getData().getMobile());

                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<LoginNodeResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, "Server and Internet Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
