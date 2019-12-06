package com.eleganzit.tag.ui.activity.school;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.school.subfragments.ClassFeesFragment;
import com.eleganzit.tag.ui.activity.school.subfragments.SchoolFacilityFragment;
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
    TextView textTitle;
    String college_id,college_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
        htab_tabs=findViewById(R.id.htab_tabs);
        textTitle=findViewById(R.id.textTitle);
        college_name=getIntent().getStringExtra("college_name");
        college_id=getIntent().getStringExtra("college_id");
        htab_viewpager=findViewById(R.id.htab_viewpager);
        textTitle.setText(college_name);
        setupViewPager(htab_viewpager);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
       // Toast.makeText(this, ""+college_id, Toast.LENGTH_SHORT).show();
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        Bundle args=new Bundle();
        args.putString("college_id",college_id);

        SchoolHomeFragment schoolHomeFragment=new SchoolHomeFragment();
        schoolHomeFragment.setArguments(args);
        adapter.addFrag(schoolHomeFragment,"Home");
        adapter.addFrag(new ClassFeesFragment(),"Class & Fees");

        SchoolGalleryFragment schoolGalleryFragment=new SchoolGalleryFragment();
        schoolGalleryFragment.setArguments(args);
        adapter.addFrag(schoolGalleryFragment,"Gallery");

        SchoolFacilityFragment schoolFacilityFragment=new SchoolFacilityFragment();
        schoolFacilityFragment.setArguments(args);

        adapter.addFrag(schoolFacilityFragment,"Facility");

        viewPager.setAdapter(adapter);
        htab_tabs.setupWithViewPager(htab_viewpager);
    }
}
