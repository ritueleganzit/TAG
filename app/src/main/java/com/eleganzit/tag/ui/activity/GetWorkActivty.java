package com.eleganzit.tag.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.EduBackgroundAdapter;
import com.eleganzit.tag.adapter.WorkBackgroundAdapter;
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;
import java.util.List;

public class GetWorkActivty extends AppCompatActivity {

    CardView add_work;
    RecyclerView rc_work_get;
    List<Workdata> workdata=new ArrayList<>();
    String user_id;
    UserLoggedInSession userLoggedInSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_work_activty);
        rc_work_get=findViewById(R.id.rc_work_get);
        workdata=getIntent().getParcelableArrayListExtra("workdata");
        userLoggedInSession=new UserLoggedInSession(GetWorkActivty.this);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        add_work=findViewById(R.id.add_work);
        add_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetWorkActivty.this, AddWorkActivity.class));
                finish();
            }
        });
        rc_work_get.setAdapter(new WorkBackgroundAdapter(workdata,GetWorkActivty.this,user_id));

    }
}
