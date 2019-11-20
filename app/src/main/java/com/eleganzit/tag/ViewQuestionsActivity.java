package com.eleganzit.tag;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.adapter.DiscussionAdapter;
import com.eleganzit.tag.adapter.ViewDiscussionAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.GetCoursesResponse;
import com.eleganzit.tag.model.discussion.Datum;
import com.eleganzit.tag.model.discussion.DiscussionListResponse;
import com.eleganzit.tag.ui.activity.school.tabfragment.AppliedCollegeFragment;
import com.eleganzit.tag.ui.activity.school.tabfragment.SubActivityFragment;
import com.eleganzit.tag.ui.fragment.DiscussionFragment;
import com.eleganzit.tag.ui.fragment.UnansweredFragment;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.eleganzit.tag.utils.ViewPagerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewQuestionsActivity extends AppCompatActivity {
    public static ViewPager htab_viewpager;
    TabLayout htab_tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);
        htab_tabs=findViewById(R.id.htab_tabs);


findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
});
        htab_viewpager=findViewById(R.id.htab_viewpager);
        setupViewPager(htab_viewpager);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager());

        adapter.addFrag(new DiscussionFragment(),"Discussion");
        adapter.addFrag(new UnansweredFragment(),"Unanswered");

        viewPager.setAdapter(adapter);
        htab_tabs.setupWithViewPager(htab_viewpager);
    }
}
