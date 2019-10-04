package com.eleganzit.tag.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.LoginActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.SignUpActivity;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddPersonalInfoResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPersonalInfoActivity extends AppCompatActivity {
TextView submit;

EditText new_password,cnf_password,old_password,user_email,mobile,name,location;
String user_id;
    ProgressDialog progressDialog;

UserLoggedInSession userLoggedInSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_info);
        submit=findViewById(R.id.submit);
        new_password=findViewById(R.id.new_password);
        cnf_password=findViewById(R.id.cnf_password);
        old_password=findViewById(R.id.old_password);
        user_email=findViewById(R.id.user_email);
        mobile=findViewById(R.id.mobile);
        name=findViewById(R.id.name);
        location=findViewById(R.id.location);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        userLoggedInSession=new UserLoggedInSession(AddPersonalInfoActivity.this);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);
        progressDialog = new ProgressDialog(AddPersonalInfoActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
});
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()){

                if ((old_password.getText().toString().trim().equals("")) && (new_password.getText().toString().trim().equals("")) && (cnf_password.getText().toString().trim().equals("")))
                {
                    updateProfilewithoutPassword();
                  //  Toast.makeText(AddPersonalInfoActivity.this, "password not selected", Toast.LENGTH_SHORT).show();
                }
                else
                {

                     if (old_password.getText().toString().trim().equals("") || old_password.getText().toString().trim().length() < 6) {
                    Toast.makeText(AddPersonalInfoActivity.this, "Please enter valid password", Toast.LENGTH_SHORT).show();


                    old_password.requestFocus();

                }

                else if (new_password.getText().toString().trim().equals("") || new_password.getText().toString().trim().length() < 8) {

                         Toast.makeText(AddPersonalInfoActivity.this, "Password must contain atleast 8 characters", Toast.LENGTH_SHORT).show();

                    new_password.requestFocus();

                } else if (!new_password.getText().toString().trim().equals(cnf_password.getText().toString().trim())) {

                         Toast.makeText(AddPersonalInfoActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();

                    cnf_password.requestFocus();

                }else
                     {
                       //  Toast.makeText(AddPersonalInfoActivity.this, "password  selected", Toast.LENGTH_SHORT).show();

                        updateProfilewithPassword();
                     }


                }
                }






            }
        });

        getPersonalInfo();


    }

    private void getPersonalInfo() {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<AddPersonalInfoResponse> call=myInterface.get_userdata("get_userdata",user_id);
        call.enqueue(new Callback<AddPersonalInfoResponse>() {
            @Override
            public void onResponse(Call<AddPersonalInfoResponse> call, Response<AddPersonalInfoResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {

                        if (response.body().getData()!=null)
                        {
                            for (int i=0;i<response.body().getData().size();i++)
                            {
                                if (response.body().getData().get(i).getName()!=null  && !(response.body().getData().get(i).getName().isEmpty()))
                                {
                                    name.setText(""+response.body().getData().get(i).getName());

                                }

                                if (response.body().getData().get(i).getLocation()!=null  && !(response.body().getData().get(i).getLocation().isEmpty()))
                                {
                                    location.setText(""+response.body().getData().get(i).getLocation());

                                }

                                if (response.body().getData().get(i).getMobile()!=null  && !(response.body().getData().get(i).getMobile().isEmpty()))
                                {
                                    mobile.setText(""+response.body().getData().get(i).getMobile());

                                }

                                if (response.body().getData().get(i).getUserEmail()!=null  && !(response.body().getData().get(i).getUserEmail().isEmpty()))
                                {
                                    user_email.setText(""+response.body().getData().get(i).getUserEmail());

                                }

                                if (response.body().getData().get(i).getName()!=null  && !(response.body().getData().get(i).getName().isEmpty()))
                                {
                                    name.setText(""+response.body().getData().get(i).getName());

                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<AddPersonalInfoResponse> call, Throwable t) {

            }
        });


    }

    private void updateProfilewithoutPassword() {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<AddPersonalInfoResponse> call=myInterface.updatePersonalInfo("personal_information",user_id
        ,name.getText().toString()
                ,mobile.getText().toString()
                ,user_email.getText().toString()
                ,location.getText().toString()
        );


        call.enqueue(new Callback<AddPersonalInfoResponse>() {
            @Override
            public void onResponse(Call<AddPersonalInfoResponse> call, Response<AddPersonalInfoResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Toast.makeText(AddPersonalInfoActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        Toast.makeText(AddPersonalInfoActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<AddPersonalInfoResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(AddPersonalInfoActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProfilewithPassword() {

        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<AddPersonalInfoResponse> call=myInterface.updatePersonalInfo("personal_information",user_id
                ,name.getText().toString()
                ,mobile.getText().toString()
                ,user_email.getText().toString()
                ,old_password.getText().toString()
                ,new_password.getText().toString()
                ,location.getText().toString()
        );


        call.enqueue(new Callback<AddPersonalInfoResponse>() {
            @Override
            public void onResponse(Call<AddPersonalInfoResponse> call, Response<AddPersonalInfoResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        Toast.makeText(AddPersonalInfoActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        Toast.makeText(AddPersonalInfoActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<AddPersonalInfoResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(AddPersonalInfoActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean isValid()
    {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(user_email.getText().toString());
        if (name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();

            name.requestFocus();

            return false;
        }
        else if (mobile.getText().toString().trim().equals("") || mobile.getText().toString().trim().length()<10) {

            Toast.makeText(this, "Phone number must contain atleast 10 digits", Toast.LENGTH_SHORT).show();


            mobile.requestFocus();

            return false;
        }
        else if (!matcher.matches()) {
            Toast.makeText(this, "Please enter a Valid e-mail id", Toast.LENGTH_SHORT).show();

            user_email.requestFocus();
            return false;
        }
        else if (location.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter  location", Toast.LENGTH_SHORT).show();


            location.requestFocus();

            return false;
        }/*else if (old_password.getText().toString().trim().equals("") || old_password.getText().toString().trim().length() < 6) {
            Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show();


            old_password.requestFocus();

            return false;
        }

         else if (new_password.getText().toString().trim().equals("") || new_password.getText().toString().trim().length() < 6) {

            new_password.setError("Password must contain atleast 6 characters");

            new_password.requestFocus();
            return false;

        } else if (!new_password.getText().toString().trim().equals(cnf_password.getText().toString().trim())) {

            cnf_password.setError("Password doesn't match");

            cnf_password.requestFocus();
            return false;

        }*/





        return true;    }
}
