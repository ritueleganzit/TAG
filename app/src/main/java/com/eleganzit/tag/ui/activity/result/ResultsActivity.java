package com.eleganzit.tag.ui.activity.result;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.exam.fragment.ExamDateFragment;
import com.eleganzit.tag.ui.activity.exam.fragment.ExamResultsFragment;
import com.eleganzit.tag.ui.activity.result.fragment.EntranceFragment;
import com.eleganzit.tag.ui.activity.result.fragment.TenthResultsFragment;
import com.eleganzit.tag.utils.ViewPagerAdapter;

public class ResultsActivity extends AppCompatActivity {
    public static ViewPager htab_viewpager;
    TabLayout htab_tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        htab_tabs=findViewById(R.id.htab_tabs);

        htab_viewpager=findViewById(R.id.htab_viewpager);
        setupViewPager(htab_viewpager);

    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager());

        adapter.addFrag(new TenthResultsFragment(),"10th Results");
        adapter.addFrag(new TenthResultsFragment(),"12th Results");
        adapter.addFrag(new EntranceFragment(),"Entrance Exam");

        viewPager.setAdapter(adapter);
        htab_tabs.setupWithViewPager(htab_viewpager);
    }
}
