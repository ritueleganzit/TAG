package com.eleganzit.tag.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.fragment.MyProfileFragment;
import com.eleganzit.tag.ui.home.HomeFragment;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.eleganzit.tag.utils.ViewPagerAdapter;

public class MyProfileActivity extends AppCompatActivity {
ViewPager htab_viewpager;
TabLayout htab_tabs;
TextView namedata;
String user_id;
    UserLoggedInSession userLoggedInSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        htab_tabs=findViewById(R.id.htab_tabs);
        htab_viewpager=findViewById(R.id.htab_viewpager);
        namedata=findViewById(R.id.namedata);

        userLoggedInSession=new UserLoggedInSession(MyProfileActivity.this);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);
        namedata.setText(""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_NAME));
setupViewPager(htab_viewpager);
        htab_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                htab_viewpager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        // TODO
                        break;
                }
            }            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }            @Override
            public void onTabReselected(TabLayout.Tab tab) {}});
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        adapter.addFrag(new MyProfileFragment(),"PROFILE");
        adapter.addFrag(new HomeFragment(),"ACTIVITY");
        adapter.addFrag(new HomeFragment(),"PAYMENT");

        viewPager.setAdapter(adapter);
        htab_tabs.setupWithViewPager(htab_viewpager);
    }
}
