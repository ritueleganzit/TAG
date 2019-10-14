package com.eleganzit.tag;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;


import com.eleganzit.tag.ui.activity.AskAQuestionActivity;
import com.eleganzit.tag.ui.activity.CollegeSelectSpecializationActivity;
import com.eleganzit.tag.ui.activity.MyProfileActivity;
import com.eleganzit.tag.ui.activity.NotificationActivity;
import com.eleganzit.tag.ui.activity.TopCollegesActivity;
import com.eleganzit.tag.ui.activity.collegepredictor.CollegePredictorActivity;
import com.eleganzit.tag.ui.activity.school.SchoolSelectSpecializationActivity;
import com.eleganzit.tag.ui.home.HomeFragment;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;


import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;
    UserLoggedInSession userLoggedInSession;

    ActionBarDrawerToggle mDrawerToggle;
    ImageView notification_ic;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         drawer = findViewById(R.id.drawer_layout);
        notification_ic = findViewById(R.id.notification_ic);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        userLoggedInSession = new UserLoggedInSession(HomeActivity.this);

        userLoggedInSession.checkLogin();
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_tag, getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView text = (TextView) header.findViewById(R.id.name);
        TextView mobiletv = (TextView) header.findViewById(R.id.mobiletv);
        TextView emailtv = (TextView) header.findViewById(R.id.emailtv);
        notification_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        if(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_NAME)!=null && !(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_NAME).isEmpty()))
        {
            text.setText(""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_NAME));
        }
        if(userLoggedInSession.getUserDetails().get(UserLoggedInSession.EMAIL)!=null && !(userLoggedInSession.getUserDetails().get(UserLoggedInSession.EMAIL).isEmpty()))
        {
            emailtv.setText(""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.EMAIL));
        }

        if(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_PHONE)!=null && !userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_PHONE).isEmpty())
        {
            mobiletv.setText(""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_PHONE));
        }



        HomeFragment homeFragment= new HomeFragment();
        getSupportFragmentManager().beginTransaction()//
                .replace(R.id.container, homeFragment, "TAG")
                .commit();

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MyProfileActivity.class));

            }
        });
    }


    @SuppressLint("RestrictedApi")
    @Override
    protected void onResume() {
        super.onResume();
      //  Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_back, getTheme());
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        //mDrawerToggle.setDrawerIndicatorEnabled(false);
      //  mDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_tag);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.nav_home)
        {
            HomeFragment homeFragment = new HomeFragment();

            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("NavHomeActivity")
                    .replace(R.id.container, homeFragment, "TAG")
                    .commit();
        } if (id==R.id.nav__menushare)
        {
          startActivity(new Intent(HomeActivity.this, AskAQuestionActivity.class));
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        }if (id==R.id.nav_menu_terms)
        {
          startActivity(new Intent(HomeActivity.this, TermsAndCondition.class));
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        }if (id==R.id.nav__menu_college_p)
        {
          startActivity(new Intent(HomeActivity.this, CollegePredictorActivity.class));
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        }if (id==R.id.nav_share)
        {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "TAG");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }

        }if (id==R.id.nav_menu_privacy)
        {
          startActivity(new Intent(HomeActivity.this, PrivacyPolicy.class));
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        }if (id==R.id.nav_logout)
        {
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);

            AlertDialog alertDialog=     new AlertDialog.Builder(this).setMessage("Are you sure you want to logout?").setCancelable(false)

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            userLoggedInSession.logoutUser();
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.clear();
                            editor.commit();
                            //signOut();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();

            TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
            Typeface face= Typeface.createFromAsset(getAssets(),"fonts/roboto_light.ttf");
            textView.setTypeface(face);
        }if (id==R.id.nav_gallery)
        {
            startActivity(new Intent(HomeActivity.this, MyProfileActivity.class));

        }if (id==R.id.nav_slideshow)
        {
            startActivity(new Intent(HomeActivity.this, SchoolSelectSpecializationActivity.class));

        }if (id==R.id.nav_menu_courses)
        {
            startActivity(new Intent(HomeActivity.this, SelectCourseActivity.class));

        }if (id==R.id.nav_menu_specialization)
        {
            startActivity(new Intent(HomeActivity.this, SelectSpecializationActivity.class));

        }if (id==R.id.nav_send)
        {
            startActivity(new Intent(HomeActivity.this, RateUs.class));

        }if (id==R.id.nav_menu_help)
        {
            startActivity(new Intent(HomeActivity.this, HelpFAQActivity.class));

        }if (id==R.id.nav__menu_send)
        {
            //
             startActivity(new Intent(HomeActivity.this, ViewQuestionsActivity.class));

        }if (id==R.id.nav_tools)
        {

             startActivity(new Intent(HomeActivity.this, CollegeSelectSpecializationActivity.class));

        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


}
