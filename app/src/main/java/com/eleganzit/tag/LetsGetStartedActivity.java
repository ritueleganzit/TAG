package com.eleganzit.tag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LetsGetStartedActivity extends AppCompatActivity {

    TextView letsgetstarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_get_started);
        letsgetstarted=findViewById(R.id.letsgetstarted);
        letsgetstarted .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LetsGetStartedActivity.this, IntroSliderActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
    }
}
