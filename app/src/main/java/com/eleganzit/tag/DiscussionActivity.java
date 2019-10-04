package com.eleganzit.tag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.adapter.DiscussionAdapter;
import com.eleganzit.tag.adapter.HelpAdapter;

public class DiscussionActivity extends AppCompatActivity {
RecyclerView rc_discussion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rc_discussion=findViewById(R.id.rc_discussion);
        rc_discussion.setAdapter(new DiscussionAdapter(DiscussionActivity.this));
    }@Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
