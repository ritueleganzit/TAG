package com.eleganzit.tag.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.eleganzit.tag.R;

public class GetWorkActivty extends AppCompatActivity {

    CardView add_work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_work_activty);
        add_work=findViewById(R.id.add_work);
        add_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetWorkActivty.this, AddWorkActivity.class));
                finish();
            }
        });
    }
}
