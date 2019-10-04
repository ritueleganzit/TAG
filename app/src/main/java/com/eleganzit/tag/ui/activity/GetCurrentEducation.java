package com.eleganzit.tag.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CurrentEduBackgroundAdapter;
import com.eleganzit.tag.adapter.EduBackgroundAdapter;
import com.eleganzit.tag.model.Education;
import com.eleganzit.tag.model.Preferancedata;
import com.eleganzit.tag.model.Workdata;

import java.util.ArrayList;
import java.util.List;

public class GetCurrentEducation extends AppCompatActivity {
    CardView add_edu_pref;
RecyclerView rc_edu_pref_cr;
    List<Preferancedata> educations=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_current_education);
        rc_edu_pref_cr=findViewById(R.id.rc_edu_pref_cr);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        educations=getIntent().getParcelableArrayListExtra("prefdata");

        add_edu_pref=findViewById(R.id.add_edu_pref);
        add_edu_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetCurrentEducation.this, AddEducationPreference.class));
                finish();
            }
        });
        rc_edu_pref_cr.setAdapter(new CurrentEduBackgroundAdapter(educations,GetCurrentEducation.this));

    }
}
