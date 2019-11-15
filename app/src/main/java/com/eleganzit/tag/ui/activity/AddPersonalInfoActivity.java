package com.eleganzit.tag.ui.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.LoginActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.SignUpActivity;
import com.eleganzit.tag.adapter.CurrentEduAdapter;
import com.eleganzit.tag.adapter.EducationAdapter;
import com.eleganzit.tag.adapter.WorkAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddPersonalInfoResponse;
import com.eleganzit.tag.model.addprofileinfo.UpdateProfileResponse;
import com.eleganzit.tag.model.profileinfo.ProfileInfoDataResponse;
import com.eleganzit.tag.ui.activity.school.ApplySchoolBasicInfoActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddPersonalInfoActivity extends AppCompatActivity {
TextView submit;

EditText user_email,mobile,name,location,edlastname,eddob;
String user_id;
    ProgressDialog progressDialog;

UserLoggedInSession userLoggedInSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_info);
        submit=findViewById(R.id.submit);
        edlastname=findViewById(R.id.edlastname);

        user_email=findViewById(R.id.user_email);
        mobile=findViewById(R.id.mobile);
        name=findViewById(R.id.edname);
        location=findViewById(R.id.location);
        eddob=findViewById(R.id.eddob);
        eddob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int  mMonth = c.get(Calendar.MONTH);
                int   mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPersonalInfoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                int month= monthOfYear+1;
                                String fm=""+month;
                                String fd=""+dayOfMonth;
                                if(month<10){
                                    fm ="0"+month;
                                }
                                if (dayOfMonth<10){
                                    fd="0"+dayOfMonth;
                                }
                                eddob.setText(fm + "/" + (fd) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
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
                if (isValid()) {


                    updateProfile();
                    //  Toast.makeText(AddPersonalInfoActivity.this, "password not selected", Toast.LENGTH_SHORT).show();

                }






            }
        });

        getAllProfiledata();


    }
    public void updateProfile()

    {
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        paramObject.addProperty("first_name", name.getText().toString());
        paramObject.addProperty("last_name", edlastname.getText().toString());
        paramObject.addProperty("user_email", user_email.getText().toString());
        paramObject.addProperty("mobile", mobile.getText().toString());
        paramObject.addProperty("dob", eddob.getText().toString());
        paramObject.addProperty("nationality", location.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<UpdateProfileResponse> call=myInterface.updateProfileResponse(paramObject);
        call.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                    {
                        Toast.makeText(AddPersonalInfoActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddPersonalInfoActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void getAllProfiledata() {

        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<ProfileInfoDataResponse> call=myInterface.getProfileById(user_id);
        call.enqueue(new Callback<ProfileInfoDataResponse>() {
            @Override
            public void onResponse(Call<ProfileInfoDataResponse> call, Response<ProfileInfoDataResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {
                        if (response.body().getData().getPersonalInfo()!=null)
                        {
                            if (response.body().getData().getPersonalInfo().getMobile()!=null && !(response.body().getData().getPersonalInfo().getMobile().isEmpty())) {
                                mobile.setText(""+response.body().getData().getPersonalInfo().getMobile());
                                MyProfileActivity.phonetv.setText(""+""+response.body().getData().getPersonalInfo().getMobile());
                            }
                            if (response.body().getData().getPersonalInfo().getNationality()!=null && !(response.body().getData().getPersonalInfo().getNationality().isEmpty()))
                            {
                                location.setText(""+response.body().getData().getPersonalInfo().getNationality());
                            }
                            if (response.body().getData().getPersonalInfo().getEmail()!=null && !(response.body().getData().getPersonalInfo().getEmail().isEmpty()))
                            {
                                user_email.setText(""+response.body().getData().getPersonalInfo().getEmail());

                            }
                            if (response.body().getData().getPersonalInfo().getFirstName()!=null && !(response.body().getData().getPersonalInfo().getFirstName().isEmpty()))
                        {
                            name.setText(""+response.body().getData().getPersonalInfo().getFirstName());

                        }if (response.body().getData().getPersonalInfo().getLastName()!=null && !(response.body().getData().getPersonalInfo().getLastName().isEmpty()))
                        {
                            edlastname.setText(""+response.body().getData().getPersonalInfo().getLastName());

                        }if (response.body().getData().getPersonalInfo().getDob()!=null && !(response.body().getData().getPersonalInfo().getDob().isEmpty()))
                        {
                            eddob.setText(""+response.body().getData().getPersonalInfo().getDob());

                        }

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileInfoDataResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });



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
                progressDialog.dismiss();
                Toast.makeText(AddPersonalInfoActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

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



    public boolean isValid()
    {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(user_email.getText().toString());
        if (name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please first enter name", Toast.LENGTH_SHORT).show();

            name.requestFocus();

            return false;
        }else if (edlastname.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please last enter name", Toast.LENGTH_SHORT).show();

            edlastname.requestFocus();

            return false;
        }
         else if (eddob.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please select date of birth", Toast.LENGTH_SHORT).show();


            eddob.requestFocus();

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
