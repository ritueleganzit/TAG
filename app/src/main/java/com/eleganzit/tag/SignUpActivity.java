package com.eleganzit.tag;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    String[] listItems = {"INDIA", "UNITED STATES"};
    UserLoggedInSession userLoggedInSession;

    EditText nationality,password,edemail,edmobile,edname;
    TextView logintxt,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);
        logintxt=findViewById(R.id.logintxt);
        register=findViewById(R.id.register);
        nationality=findViewById(R.id.nationality);
        password=findViewById(R.id.password);
        edemail=findViewById(R.id.edemail);
        edmobile=findViewById(R.id.edmobile);
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        edname=findViewById(R.id.edname);
        userLoggedInSession=new UserLoggedInSession(SignUpActivity.this);

        nationality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

                builder.setItems(listItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nationality.setText(""+listItems[which]);

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });  register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValid())
                {
                    setSignUp();

                }


            }
        });
    }


public void  setSignUp(){
    progressDialog.show();
    RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
    Call<LoginResponse> call=myInterface.doSignUP(edname.getText().toString(),edmobile.getText().toString(),edemail.getText().toString(),password.getText().toString(),nationality.getText().toString());
    call.enqueue(new Callback<LoginResponse>() {
        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            progressDialog.dismiss();
            if (response.isSuccessful()) {

                if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    userLoggedInSession.createSignUpSession(response.body().getData().get(0).getUserEmail()
                            ,response.body().getData().get(0).getUserId()
                            ,response.body().getData().get(0).getName()
                            ,"");

                }
                else
                {
                    Toast.makeText(SignUpActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {
            progressDialog.dismiss();

            Toast.makeText(SignUpActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
        }
    });
}
    public boolean isValid() {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(edemail.getText().toString());


        if (edname.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();

            edname.requestFocus();

            return false;
        }
        else if (edmobile.getText().toString().trim().equals("") || edmobile.getText().toString().trim().length()<10) {

            Toast.makeText(this, "Phone number must contain atleast 10 digits", Toast.LENGTH_SHORT).show();


            edmobile.requestFocus();

            return false;
        }
        else if (!matcher.matches()) {
            Toast.makeText(this, "Please enter a Valid e-mail id", Toast.LENGTH_SHORT).show();

            edemail.requestFocus();
            return false;
        }
        else if (password.getText().toString().trim().equals("") || password.getText().toString().trim().length() < 6) {
            Toast.makeText(this, "Password must contain atleast 6 characters", Toast.LENGTH_SHORT).show();


            password.requestFocus();

            return false;
        }
       else if (nationality.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please select nationality", Toast.LENGTH_SHORT).show();

            nationality.requestFocus();

            return false;
        }

        return true;
    }
}
