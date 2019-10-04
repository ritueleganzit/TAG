package com.eleganzit.tag;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.eleganzit.tag.adapter.viewPagerAdapter;
import com.eleganzit.tag.model.CoursesData;
import com.eleganzit.tag.model.GetSpecialization;
import com.eleganzit.tag.ui.fragment.EligibilityCriteriaFragment;
import com.eleganzit.tag.ui.fragment.OverviewFragment;
import com.eleganzit.tag.ui.fragment.SpecializationFragment;

public class SelectedCourseActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener  {


    TabLayout tabLayout;
    ViewPager viewPager;
    TextView textTitle;
    String course_name,course_overview,course_specialization,course_eligibility,special;
    int course_id;
    GetSpecialization object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_course);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        textTitle = findViewById(R.id.textTitle);




        if (getIntent()!=null)
        {
            special=getIntent().getStringExtra("special");


            if (special!=null && !(special.isEmpty()))
            {
                object= (GetSpecialization) getIntent().getSerializableExtra ("object");
                tabLayout.addTab(tabLayout.newTab().setText("Overview"));
                tabLayout.addTab(tabLayout.newTab().setText("Eligibility Criteria"));

                tabLayout.addTab(tabLayout.newTab().setText("Course cirrculum"));
                tabLayout.setOnTabSelectedListener(this);
                Log.d("ngiua",""+object.getOverview());

                textTitle.setText(object.getCourceName()+"");
                setupViewPager2(viewPager);
                tabLayout.setupWithViewPager(viewPager);
             }
            else
            {
                course_id=getIntent().getIntExtra("course_id",0);
                course_name=getIntent().getStringExtra("course_name");
                course_overview=getIntent().getStringExtra("course_overview");
                course_specialization=getIntent().getStringExtra("course_specialization");
                course_eligibility=getIntent().getStringExtra("course_eligibility");

                textTitle.setText(course_name+"");
                tabLayout.addTab(tabLayout.newTab().setText("Overview"));
                tabLayout.addTab(tabLayout.newTab().setText("Specialization"));
                tabLayout.addTab(tabLayout.newTab().setText("Eligibility Criteria"));
                tabLayout.setOnTabSelectedListener(this);

                setupViewPager(viewPager);
                tabLayout.setupWithViewPager(viewPager);
            }
        }
        //Adding the tabs using addTab() method







    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OverviewFragment(), "Overview");

        adapter.addFragment(new SpecializationFragment(), "Specialization");

        adapter.addFragment(new EligibilityCriteriaFragment(), "Eligibility Criteria");

        viewPager.setAdapter(adapter);

        CoursesData coursesData=new CoursesData(course_id,course_name,course_overview,course_specialization,course_eligibility);


    }
    private void setupViewPager2(ViewPager viewPager) {
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        OverviewFragment overviewFragment=new OverviewFragment();
        Bundle bundle=new Bundle();
        bundle.putString("edttext","edttext");
        overviewFragment.setArguments(bundle);



        adapter.addFragment(overviewFragment, "Overview");

        SpecializationFragment specializationFragment=new SpecializationFragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("edttext","edttext");
        specializationFragment.setArguments(bundle1);



        adapter.addFragment(specializationFragment, "Eligibility Criteria");


        EligibilityCriteriaFragment eligibilityCriteriaFragment=new EligibilityCriteriaFragment();
        Bundle bundle2=new Bundle();
        bundle2.putString("edttext","edttext");
        eligibilityCriteriaFragment.setArguments(bundle2);



        adapter.addFragment(eligibilityCriteriaFragment, "Course cirrculum");

        viewPager.setAdapter(adapter);

        CoursesData coursesData=new CoursesData(course_id,course_name,course_overview,course_specialization,course_eligibility);


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
