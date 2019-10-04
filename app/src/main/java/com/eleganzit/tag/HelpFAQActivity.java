package com.eleganzit.tag;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.eleganzit.tag.adapter.HelpAdapter;
import com.eleganzit.tag.adapter.WorkAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CoursesData;
import com.eleganzit.tag.model.GetCoursesData;
import com.eleganzit.tag.model.GetCoursesResponse;
import com.eleganzit.tag.model.GetFaqList;
import com.eleganzit.tag.model.GetFaqListResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpFAQActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    List<GetFaqList> getFaqListList;

    RecyclerView rc_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_faq);
        rc_search=findViewById(R.id.rc_search);
        progressDialog = new ProgressDialog(HelpFAQActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        userLoggedInSession=new UserLoggedInSession(HelpFAQActivity.this);

        progressDialog.setCanceledOnTouchOutside(false);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getHelpData();
    }

    private void getHelpData() {
        progressDialog.show();
        getFaqListList=new ArrayList<>();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);

        Call<GetFaqListResponse> call=myInterface.getFaqList();
        call.enqueue(new Callback<GetFaqListResponse>() {
            @Override
            public void onResponse(Call<GetFaqListResponse> call, Response<GetFaqListResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {

                        if (response.body().getData()!=null)
                        {
                            getFaqListList.addAll(response.body().getData());
                            rc_search.setAdapter(new HelpAdapter(getFaqListList,HelpFAQActivity.this));

                        }

                    }
                    else
                    {

                        Toast.makeText(HelpFAQActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(HelpFAQActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GetFaqListResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(HelpFAQActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

}
