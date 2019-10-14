package com.eleganzit.tag.ui.activity.councelling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.councelling.adapter.CouncellingAdapter;
import com.eleganzit.tag.ui.activity.exam.ExamActivity;
import com.eleganzit.tag.ui.activity.exam.adapter.ExamAdapter;

public class CouncellingExamActivity extends AppCompatActivity {
    RecyclerView rc_exam_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_councelling_exam);
        rc_exam_list=findViewById(R.id.rc_exam_list);
        rc_exam_list.setAdapter(new CouncellingAdapter(CouncellingExamActivity.this));
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }
}
