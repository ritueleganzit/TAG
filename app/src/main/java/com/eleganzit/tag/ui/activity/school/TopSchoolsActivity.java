package com.eleganzit.tag.ui.activity.school;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeListAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CollegeResult;
import com.eleganzit.tag.model.TopCollegeResponse;
import com.eleganzit.tag.model.schoolstream.Datum;
import com.eleganzit.tag.model.schoolstream.GetSchoolListResponse;
import com.eleganzit.tag.model.schoolstream.Result;
import com.eleganzit.tag.ui.activity.TopCollegesActivity;
import com.eleganzit.tag.ui.activity.school.adpater.SchoolListAdapter;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopSchoolsActivity extends AppCompatActivity {

    RecyclerView rc_top_colleges;
    String assigned_class,stream_name;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    ArrayList<Result> collegeResultArrayList;
TextView college_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_school);
        college_count=findViewById(R.id.college_count);
        rc_top_colleges=findViewById(R.id.rc_top_colleges);
        userLoggedInSession=new UserLoggedInSession(this);
        assigned_class=getIntent().getStringExtra("assigned_class");
        stream_name=getIntent().getStringExtra("stream_name");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTopSchoolList();

    }

    private void getTopSchoolList() {
        collegeResultArrayList=new ArrayList<>();
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("assigned_class", assigned_class);
        paramObject.addProperty("stream_name", ""+stream_name);

        Log.d("sadfsdf","sd"+paramObject);

        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        final Call<GetSchoolListResponse> collegeResponseCall=myInterface.getSchoolList(paramObject);
        collegeResponseCall.enqueue(new Callback<GetSchoolListResponse>() {
            @Override
            public void onResponse(Call<GetSchoolListResponse> call, Response<GetSchoolListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        if (response.body().getResult()!=null)
                        {
                            college_count.setText(response.body().getSchoolCount()+" school");
                            collegeResultArrayList.addAll(response.body().getResult());
                        }
                        rc_top_colleges.setAdapter(new SchoolListAdapter(collegeResultArrayList,TopSchoolsActivity.this));


                    }
                }
            }

            @Override
            public void onFailure(Call<GetSchoolListResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TopSchoolsActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
