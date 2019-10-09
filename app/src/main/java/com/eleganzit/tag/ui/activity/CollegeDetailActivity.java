package com.eleganzit.tag.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.fragment.ActivityFragment;
import com.eleganzit.tag.ui.fragment.CollegeCourses;
import com.eleganzit.tag.ui.fragment.CollegeFacilityFragment;
import com.eleganzit.tag.ui.fragment.CollegeGalleryFragment;
import com.eleganzit.tag.ui.fragment.CollegeHomeFragment;
import com.eleganzit.tag.ui.fragment.CourseFeesFragment;
import com.eleganzit.tag.ui.fragment.MyProfileFragment;
import com.eleganzit.tag.ui.fragment.PaymentFragment;
import com.eleganzit.tag.utils.ViewPagerAdapter;

public class CollegeDetailActivity extends AppCompatActivity {
  public static   ViewPager htab_viewpager;
    TabLayout htab_tabs;
    int college_id;
    String college_name;
    public static TextView college_nametv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);
        htab_tabs=findViewById(R.id.htab_tabs);
        college_nametv=findViewById(R.id.college_name);
        college_id=getIntent().getIntExtra("college_id",0);
        college_name=getIntent().getStringExtra("college_name");
        htab_viewpager=findViewById(R.id.htab_viewpager);
        Log.d("hbguy g",""+college_id );

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

        Bundle args=new Bundle();
        args.putInt("college_id",college_id);
        args.putString("college_name",college_name);
        CollegeHomeFragment collegeHomeFragment=new CollegeHomeFragment();
        collegeHomeFragment.setArguments(args);
        adapter.addFrag(collegeHomeFragment,"Home");
        adapter.addFrag(new CourseFeesFragment(),"Course & Fees");
        adapter.addFrag(new CollegeGalleryFragment(),"Gallery");
        adapter.addFrag(new CollegeFacilityFragment(),"Facility");

        viewPager.setAdapter(adapter);
        htab_tabs.setupWithViewPager(htab_viewpager);
    }
}
