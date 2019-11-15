package com.eleganzit.tag.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CurrentEduAdapter;
import com.eleganzit.tag.adapter.EduBackgroundAdapter;
import com.eleganzit.tag.model.Education;
import com.eleganzit.tag.model.profileinfo.EducationDetail;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;
import java.util.List;

public class GetEducationActivity extends AppCompatActivity {
CardView add_edu;
    String user_id;
    UserLoggedInSession userLoggedInSession;

RecyclerView rc_edu_get;
List<EducationDetail> educations=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_education);
        userLoggedInSession=new UserLoggedInSession(GetEducationActivity.this);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rc_edu_get=findViewById(R.id.rc_edu_get);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);

        educations=getIntent().getParcelableArrayListExtra("eduarray");
        add_edu=findViewById(R.id.add_edu);
        add_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetEducationActivity.this, AddEducationActivity.class));
                finish();

            }
        });

        rc_edu_get.setAdapter(new EduBackgroundAdapter(educations,GetEducationActivity.this,user_id));

    }
}
