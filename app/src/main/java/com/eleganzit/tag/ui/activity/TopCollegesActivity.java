package com.eleganzit.tag.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeListAdapter;
import com.eleganzit.tag.adapter.CurrentEduAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CollegeResult;
import com.eleganzit.tag.model.TopCollegeResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopCollegesActivity extends AppCompatActivity {

    RecyclerView rc_top_colleges;
    TextView college_count;
    ImageView sort;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    ArrayList<CollegeResult> collegeResultArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_colleges);
        rc_top_colleges=findViewById(R.id.rc_top_colleges);
        sort=findViewById(R.id.sort);
        college_count=findViewById(R.id.college_count);
        userLoggedInSession=new UserLoggedInSession(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);


        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(TopCollegesActivity.this);
                dialog.setContentView(R.layout.sort_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

            }
        });
        getTopCollegeList();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getTopCollegeList() {
        collegeResultArrayList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        final Call<TopCollegeResponse> collegeResponseCall=myInterface.getCollegeList("BE","CSE");
        collegeResponseCall.enqueue(new Callback<TopCollegeResponse>() {
            @Override
            public void onResponse(Call<TopCollegeResponse> call, Response<TopCollegeResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                    if (response.body().getResult()!=null)
                    {
                        college_count.setText(response.body().getCollegeCount()+" college");
                        collegeResultArrayList.addAll(response.body().getResult());
                    }
                        rc_top_colleges.setAdapter(new CollegeListAdapter(collegeResultArrayList,TopCollegesActivity.this));


                    }
                }
            }

            @Override
            public void onFailure(Call<TopCollegeResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TopCollegesActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
