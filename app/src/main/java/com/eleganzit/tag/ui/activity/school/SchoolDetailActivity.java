package com.eleganzit.tag.ui.activity.school;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.school.subfragments.ClassFeesFragment;
import com.eleganzit.tag.ui.activity.school.subfragments.SchoolGalleryFragment;
import com.eleganzit.tag.ui.activity.school.tabfragment.SchoolHomeFragment;
import com.eleganzit.tag.ui.fragment.CollegeFacilityFragment;
import com.eleganzit.tag.ui.fragment.CollegeGalleryFragment;
import com.eleganzit.tag.ui.fragment.CollegeHomeFragment;
import com.eleganzit.tag.ui.fragment.CourseFeesFragment;
import com.eleganzit.tag.utils.ViewPagerAdapter;

public class SchoolDetailActivity extends AppCompatActivity {
  public static   ViewPager htab_viewpager;
    TabLayout htab_tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
        htab_tabs=findViewById(R.id.htab_tabs);

        htab_viewpager=findViewById(R.id.htab_viewpager);

        setupViewPager(htab_viewpager);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager());

        adapter.addFrag(new SchoolHomeFragment(),"Home");
        adapter.addFrag(new ClassFeesFragment(),"Class & Fees");
        adapter.addFrag(new SchoolGalleryFragment(),"Gallery");
        adapter.addFrag(new CollegeFacilityFragment(),"Facility");

        viewPager.setAdapter(adapter);
        htab_tabs.setupWithViewPager(htab_viewpager);
    }
}
