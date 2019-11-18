package com.eleganzit.tag;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.adapter.viewPagerAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CoursesData;
import com.eleganzit.tag.model.GetSpecialization;
import com.eleganzit.tag.model.coursedetails.CourseDetailsResponse;
import com.eleganzit.tag.model.profileinfo.ProfileInfoDataResponse;
import com.eleganzit.tag.ui.fragment.EligibilityCriteriaFragment;
import com.eleganzit.tag.ui.fragment.OverviewFragment;
import com.eleganzit.tag.ui.fragment.SpecializationFragment;
import com.eleganzit.tag.utils.UserLoggedInSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedCourseActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener  {

    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView textTitle;
    String course_name,course_overview,course_specialization,course_eligibility,special,overview,cirrculum,eligibility;
    int course_id;
    String object;
   public
   static CoursesData coursesData;
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

        userLoggedInSession=new UserLoggedInSession(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);



        if (getIntent()!=null)
        {
            special=getIntent().getStringExtra("special");


            if (special!=null && !(special.isEmpty()))
            {
                object= getIntent().getStringExtra ("object");
                overview= getIntent().getStringExtra ("overview");
                cirrculum= getIntent().getStringExtra ("cirrculum");
                eligibility= getIntent().getStringExtra ("eligibility");
                tabLayout.addTab(tabLayout.newTab().setText("Overview"));
                tabLayout.addTab(tabLayout.newTab().setText("Overview"));
                tabLayout.addTab(tabLayout.newTab().setText("Eligibility Criteria"));

                tabLayout.addTab(tabLayout.newTab().setText("Course cirrculum"));
                tabLayout.setOnTabSelectedListener(this);
                Log.d("ngiua",""+object);

                textTitle.setText(object+"");
                setupViewPager2(viewPager);
                tabLayout.setupWithViewPager(viewPager);
             }
            else
            {
                course_id=getIntent().getIntExtra("course_id",0);
                course_name=getIntent().getStringExtra("course_name");


                textTitle.setText(course_name+"");
               getCourseDetails();
            }
        }
        //Adding the tabs using addTab() method



      //  getCourseDetails();



    }

    public void getCourseDetails()
    {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<CourseDetailsResponse> call=myInterface.courceById(""+course_id);
        call.enqueue(new Callback<CourseDetailsResponse>() {
            @Override
            public void onResponse(Call<CourseDetailsResponse> call, Response<CourseDetailsResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                    if (response.body().getData()!=null)
                    {
                        course_overview=""+response.body().getData().get(0).getOverview();
                        course_specialization=response.body().getData().get(0).getSpecialization();
                        course_eligibility=response.body().getData().get(0).getEligibilityCriteria();



                        setupViewPager(viewPager);

                    }
            }

            @Override
            public void onFailure(Call<CourseDetailsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SelectedCourseActivity.this, "Server or Internet error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Specialization"));
        tabLayout.addTab(tabLayout.newTab().setText("Eligibility Criteria"));

        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());

        OverviewFragment overviewFragment=new OverviewFragment();
        Bundle bundle=new Bundle();
        bundle.putString("edttext",course_overview);
        overviewFragment.setArguments(bundle);
        if (course_overview!=null && !(course_overview.isEmpty()))
        {
            adapter.addFragment(overviewFragment, "Overview");

        }


        SpecializationFragment specializationFragment=new SpecializationFragment();
        Bundle bundle2=new Bundle();
        bundle2.putString("edttext",course_specialization);
        specializationFragment.setArguments(bundle2);

        if (course_specialization!=null && !(course_specialization.isEmpty()))
        {
            adapter.addFragment(specializationFragment, "Specialization");

        }

        EligibilityCriteriaFragment eligibilityCriteriaFragment=new EligibilityCriteriaFragment();
        Bundle bundle3=new Bundle();
        bundle3.putString("edttext",course_eligibility);
        eligibilityCriteriaFragment.setArguments(bundle3);

        if (course_eligibility!=null && !(course_eligibility.isEmpty()))
        {
            adapter.addFragment(eligibilityCriteriaFragment, "Eligibility Criteria");

        }

        // adapter.addFragment(new EligibilityCriteriaFragment(), "Eligibility Criteria");

        viewPager.setAdapter(adapter);



    }
    private void setupViewPager2(ViewPager viewPager) {
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        OverviewFragment overviewFragment=new OverviewFragment();
        Bundle bundle=new Bundle();
        bundle.putString("edttext",overview);
        overviewFragment.setArguments(bundle);


        if (overview!=null && !(overview.isEmpty()))
        {
            adapter.addFragment(overviewFragment, "Overview");

        }

        SpecializationFragment specializationFragment=new SpecializationFragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("edttext",eligibility);
        specializationFragment.setArguments(bundle1);

        if (eligibility!=null && !(eligibility.isEmpty()))
        {
            adapter.addFragment(specializationFragment, "Eligibility Criteria");

        }




        EligibilityCriteriaFragment eligibilityCriteriaFragment=new EligibilityCriteriaFragment();
        Bundle bundle2=new Bundle();
        bundle2.putString("edttext",cirrculum);
        eligibilityCriteriaFragment.setArguments(bundle2);
        if (cirrculum!=null && !(cirrculum.isEmpty())) {
            adapter.addFragment(eligibilityCriteriaFragment, "Course cirrculum");

        }



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
