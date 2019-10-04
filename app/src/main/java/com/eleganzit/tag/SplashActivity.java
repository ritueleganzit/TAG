package com.eleganzit.tag;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.eleganzit.tag.utils.UserLoggedInSession;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class SplashActivity extends AppCompatActivity {
    UserLoggedInSession userLoggedInSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        userLoggedInSession=new UserLoggedInSession(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userLoggedInSession.isLoggedIn())
                {
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }







            }
        },3000);
    }
}
