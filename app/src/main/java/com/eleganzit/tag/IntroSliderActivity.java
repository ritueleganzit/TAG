package com.eleganzit.tag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.utils.SliderAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class IntroSliderActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout indicator;

    List<Integer> color;
    List<String> colorName;
TextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        indicator=(TabLayout)findViewById(R.id.indicator);
        skip=findViewById(R.id.skip);
        color = new ArrayList<>();
        color.add(Color.parseColor("#008e85"));
        color.add(Color.parseColor("#1CABA0"));

        colorName = new ArrayList<>();
        colorName.add("https://ed.stanford.edu/sites/default/files/news_images/lisegagne.jpg");
        colorName.add("https://images.unsplash.com/photo-1523050854058-8df90110c9f1?ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80");


        viewPager.setAdapter(new SliderAdapter(this, color, colorName));
        indicator.setupWithViewPager(viewPager, true);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroSliderActivity.this, LoginActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            IntroSliderActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < color.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
