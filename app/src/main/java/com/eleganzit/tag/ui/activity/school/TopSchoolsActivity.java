package com.eleganzit.tag.ui.activity.school;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeListAdapter;
import com.eleganzit.tag.ui.activity.school.adpater.SchoolListAdapter;

public class TopSchoolsActivity extends AppCompatActivity {

    RecyclerView rc_top_colleges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_school);
        rc_top_colleges=findViewById(R.id.rc_top_colleges);
        rc_top_colleges.setAdapter(new SchoolListAdapter(TopSchoolsActivity.this));

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
}
