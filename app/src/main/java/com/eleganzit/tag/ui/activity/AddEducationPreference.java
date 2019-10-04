package com.eleganzit.tag.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddEducationPreferanceResponse;
import com.eleganzit.tag.model.AddworkexpResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEducationPreference extends AppCompatActivity {
TextView submit;
    String user_id;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;

    EditText mode_of_study,specialisation,course,stream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education_preference);
        stream=findViewById(R.id.stream);
        submit=findViewById(R.id.submit);
        mode_of_study=findViewById(R.id.mode_of_study);
        specialisation=findViewById(R.id.specialisation);
        course=findViewById(R.id.course);
        userLoggedInSession=new UserLoggedInSession(AddEducationPreference.this);
        progressDialog = new ProgressDialog(AddEducationPreference.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {
                    addEduPref();
                }
            }
        });

    }

    private void addEduPref() {

        progressDialog.show();
        Log.d("add_education_pre",""+user_id);
        Log.d("add_education_pre",""+stream.getText().toString());
        Log.d("add_education_pre",""+course.getText().toString());
        Log.d("add_education_pre",""+specialisation.getText().toString());
        Log.d("add_education_pre",""+mode_of_study.getText().toString());
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<AddEducationPreferanceResponse> call=myInterface.addeducationpreferance(
                "add_education_preferance"
                ,user_id
                ,stream.getText().toString()
                ,course.getText().toString()
                ,specialisation.getText().toString()
                ,mode_of_study.getText().toString()
        );
        call.enqueue(new Callback<AddEducationPreferanceResponse>() {
            @Override
            public void onResponse(Call<AddEducationPreferanceResponse> call, Response<AddEducationPreferanceResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {

                        Log.d("ddddd",""+response.body().getData().get(0).getWorkId());
                        Log.d("ddddd",""+response.message());
                        Log.d("ddddd",""+response.body().getData().get(0).getModeOfStudy());
                        Toast.makeText(AddEducationPreference.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<AddEducationPreferanceResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddEducationPreference.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean isValid() {
        if (stream.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter education stream", Toast.LENGTH_SHORT).show();

            stream.requestFocus();

            return false;
        }

        else if (course.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter course", Toast.LENGTH_SHORT).show();


            course.requestFocus();

            return false;
        }else if (mode_of_study.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter mode of study", Toast.LENGTH_SHORT).show();


            mode_of_study.requestFocus();

            return false;
        }
        return true;
    }
}
