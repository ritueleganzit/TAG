package com.eleganzit.tag.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.fragment.ActivityFragment;
import com.eleganzit.tag.ui.fragment.MyProfileFragment;
import com.eleganzit.tag.ui.fragment.PaymentFragment;
import com.eleganzit.tag.ui.home.HomeFragment;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.eleganzit.tag.utils.ViewPagerAdapter;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;

public class MyProfileActivity extends AppCompatActivity {
ViewPager htab_viewpager;
TabLayout htab_tabs;
TextView namedata;
    private static final int REQUEST_IMAGE = 201;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 202;
    private ArrayList<String> mSelectPath;
public static TextView emailtv,phonetv;
FrameLayout frameimg;
String user_id;
    UserLoggedInSession userLoggedInSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        emailtv=findViewById(R.id.emailtv);
        htab_tabs=findViewById(R.id.htab_tabs);
        frameimg=findViewById(R.id.frameimg);
        htab_viewpager=findViewById(R.id.htab_viewpager);
        phonetv=findViewById(R.id.phonetv);
        namedata=findViewById(R.id.namedata);
        frameimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        userLoggedInSession=new UserLoggedInSession(MyProfileActivity.this);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);
        emailtv.setText(""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.EMAIL));

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
        adapter.addFrag(new ActivityFragment(),"ACTIVITY");
        adapter.addFrag(new PaymentFragment(),"PAYMENT");

        viewPager.setAdapter(adapter);
        htab_tabs.setupWithViewPager(htab_viewpager);
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(MyProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {

            MultiImageSelector selector = MultiImageSelector.create(MyProfileActivity.this);
            selector.multi();
            selector.count(6);
            selector.showCamera(false);

            selector.origin(mSelectPath);
            selector.start(MyProfileActivity.this, REQUEST_IMAGE);
        }
    }
    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MyProfileActivity.this, permission)) {
            new AlertDialog.Builder(MyProfileActivity.this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MyProfileActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(MyProfileActivity.this, new String[]{permission}, requestCode);
        }
    }
}
