package com.eleganzit.tag.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.NotificationAdapter;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView rc_exam_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        rc_exam_list=findViewById(R.id.rc_exam_list);
findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
});
        rc_exam_list.setAdapter(new NotificationAdapter(NotificationActivity.this));
    }
}
