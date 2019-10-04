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
import com.eleganzit.tag.model.AddworkexpResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWorkActivity extends AppCompatActivity {
    String user_id;
    TextView submit;
    ProgressDialog progressDialog;
EditText current_job,department,designation,employee_name;
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
    }

    private void addWorkData() {
        progressDialog.show();
        Log.d("add_work_exp",""+user_id);
        Log.d("add_work_exp",""+employee_name.getText().toString());
        Log.d("add_work_exp",""+designation.getText().toString());
        Log.d("add_work_exp",""+department.getText().toString());
        Log.d("add_work_exp",""+current_job.getText().toString());
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<AddworkexpResponse> call=myInterface.addWorkExp(
                "add_work_exp"
                ,user_id
                ,employee_name.getText().toString()
                ,designation.getText().toString()
                ,department.getText().toString()
                ,current_job.getText().toString()
        );
        call.enqueue(new Callback<AddworkexpResponse>() {
            @Override
            public void onResponse(Call<AddworkexpResponse> call, Response<AddworkexpResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {

                        Log.d("ddddd",""+response.body().getData().get(0).getWorkId());
                        Log.d("ddddd",""+response.message());
                        Log.d("ddddd",""+response.body().getData().get(0).getEmployeeName());
                        Toast.makeText(AddWorkActivity.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddworkexpResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddWorkActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private boolean isValid() {


        if (employee_name.getText().toString().trim().equals("")) {


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
        }else if (current_job.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show();


            current_job.requestFocus();

            return false;
        }
        return true;
    }
}
