package com.eleganzit.tag.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddworkexpResponse;
import com.eleganzit.tag.model.addeducation.EducationDeleteResponse;
import com.eleganzit.tag.model.addeducation.EducationUpdateResponse;
import com.eleganzit.tag.model.addwork.AddWorkExperience;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddWorkActivity extends AppCompatActivity {
    String user_id;
    TextView submit;
    String iscurrent="nodata";

    RadioGroup current_job;
    ProgressDialog progressDialog;
EditText department,designation,employee_name,employee_exp;
    UserLoggedInSession userLoggedInSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        userLoggedInSession=new UserLoggedInSession(AddWorkActivity.this);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);
        progressDialog = new ProgressDialog(AddWorkActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        employee_name=findViewById(R.id.employee_name);
        employee_exp=findViewById(R.id.employee_exp);
        current_job=findViewById(R.id.current_job);
        department=findViewById(R.id.department);
        designation=findViewById(R.id.designation);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {
                    addWorkData();
                }
            }
        });

        current_job.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rb1)
                {
                    iscurrent="yes";
                } if (checkedId==R.id.rb2)
                {
                    iscurrent="no";
                }
            }
        });
    }

    private void addWorkData() {


        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", user_id);
        paramObject.addProperty("total_exp", employee_exp.getText().toString());

        JsonObject paramObject2=new JsonObject();
        paramObject2.addProperty("employee_name",employee_name.getText().toString());
        paramObject2.addProperty("designation",designation.getText().toString());
        paramObject2.addProperty("department",department.getText().toString());
        paramObject2.addProperty("current_job_que",iscurrent);
        JsonArray jsonArray=new JsonArray();

        jsonArray.add(paramObject2);

        paramObject.add("work_details", jsonArray);



        Log.d("dataaaa","-"+paramObject.toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<AddWorkExperience> call=myInterface.updateWorkExperience(paramObject);
        call.enqueue(new Callback<AddWorkExperience>() {
            @Override
            public void onResponse(Call<AddWorkExperience> call, Response<AddWorkExperience> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                    {
                        Toast.makeText(AddWorkActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(AddWorkActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<AddWorkExperience> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddWorkActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValid() {


        if (employee_exp.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter total exp", Toast.LENGTH_SHORT).show();

            employee_exp.requestFocus();

            return false;
        } if (employee_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter employee name", Toast.LENGTH_SHORT).show();

            employee_name.requestFocus();

            return false;
        }

        else if (designation.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter designation", Toast.LENGTH_SHORT).show();


            designation.requestFocus();

            return false;
        }else if (department.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter department", Toast.LENGTH_SHORT).show();

            department.requestFocus();

            return false;
        }else if (iscurrent.equalsIgnoreCase("nodata")) {

            Toast.makeText(this, "Please select whether it is existing job", Toast.LENGTH_SHORT).show();


            current_job.requestFocus();

            return false;
        }
        return true;
    }
}
