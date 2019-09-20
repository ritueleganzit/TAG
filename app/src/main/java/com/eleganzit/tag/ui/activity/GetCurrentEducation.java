package com.eleganzit.tag.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.eleganzit.tag.R;

public class GetCurrentEducation extends AppCompatActivity {
    CardView add_edu_pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_current_education);
        add_edu_pref=findViewById(R.id.add_edu_pref);
        add_edu_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetCurrentEducation.this, AddEducationPreference.class));
                finish();
            }
        });
    }
}
