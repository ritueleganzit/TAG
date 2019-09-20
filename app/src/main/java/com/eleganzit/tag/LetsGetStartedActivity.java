package com.eleganzit.tag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.eleganzit.tag.utils.UserLoggedInSession;

public class LetsGetStartedActivity extends AppCompatActivity {
    UserLoggedInSession userLoggedInSession;

    TextView letsgetstarted,textname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_get_started);
        letsgetstarted=findViewById(R.id.letsgetstarted);
        textname=findViewById(R.id.textname);
        userLoggedInSession=new UserLoggedInSession(LetsGetStartedActivity.this);

        textname.setText("Hi, "+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_NAME));
        letsgetstarted .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LetsGetStartedActivity.this, IntroSliderActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();

            }
        });
    }
}
