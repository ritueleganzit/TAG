package com.eleganzit.tag.ui.activity.collegepredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeListAdapter;
import com.eleganzit.tag.adapter.CollegePredictorAdapter;
import com.eleganzit.tag.ui.activity.TopCollegesActivity;

public class CollegePredictorDetail extends AppCompatActivity {

    RecyclerView rc_collegepredictor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_predictor_detail);
        rc_collegepredictor=findViewById(R.id.rc_collegepredictor);
        rc_collegepredictor.setAdapter(new CollegePredictorAdapter(CollegePredictorDetail.this));
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

    }
}
