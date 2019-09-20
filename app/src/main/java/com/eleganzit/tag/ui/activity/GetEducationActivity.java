package com.eleganzit.tag.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.eleganzit.tag.R;

public class GetEducationActivity extends AppCompatActivity {
CardView add_edu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_education);
        add_edu=findViewById(R.id.add_edu);
        add_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetEducationActivity.this, AddEducationActivity.class));
                finish();

            }
        });
    }
}
