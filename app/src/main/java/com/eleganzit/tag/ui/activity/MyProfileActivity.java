package com.eleganzit.tag.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.HomeFacilityAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.FetchedUserResponse;
import com.eleganzit.tag.model.ImageUploadedResponse;
import com.eleganzit.tag.model.homefacility.FacilitiesResponse;
import com.eleganzit.tag.model.profileinfo.ProfileInfoDataResponse;
import com.eleganzit.tag.model.unanswered.UnansweredQuestionsResponse;
import com.eleganzit.tag.ui.activity.payment.PaymentFragment;
import com.eleganzit.tag.ui.fragment.AccountSettingFragment;
import com.eleganzit.tag.ui.fragment.ActivityFragment;
import com.eleganzit.tag.ui.fragment.MyProfileFragment;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.eleganzit.tag.utils.ViewPagerAdapter;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends AppCompatActivity {
ViewPager htab_viewpager;
TabLayout htab_tabs;
TextView namedata;
    private static final int REQUEST_IMAGE = 201;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 202;
    private ArrayList<String> mSelectPath;
public static TextView emailtv,phonetv;
FrameLayout frameimg;
    String mediapath = "";

ImageView myprofile;
    ProgressDialog progressDialog;
String user_id;
    UserLoggedInSession userLoggedInSession;
    private File file;

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
        progressDialog = new ProgressDialog(MyProfileActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        emailtv=findViewById(R.id.emailtv);
        htab_tabs=findViewById(R.id.htab_tabs);
        frameimg=findViewById(R.id.frameimg);
        myprofile=findViewById(R.id.myprofile);
        htab_viewpager=findViewById(R.id.htab_viewpager);
        phonetv=findViewById(R.id.phonetv);
        namedata=findViewById(R.id.namedata);
        frameimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
pickImage();
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
        adapter.addFrag(new AccountSettingFragment(),"ACCOUNT SETTINGS");

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
            selector.single();

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

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("sadadas", "resume" +userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_PHOTO));

        Glide.with(getApplicationContext())
                .load("" + userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_PHOTO)).

                apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user_shape))

                .into(myprofile);

        getFacilityData();


    }

    private void getFacilityData() {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<ProfileInfoDataResponse> call=myInterface.getProfileById(""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        call.enqueue(new Callback<ProfileInfoDataResponse>() {
            @Override
            public void onResponse(Call<ProfileInfoDataResponse> call, Response<ProfileInfoDataResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {

if (response.body().getData()!=null)
{
    if (response.body().getData().getPersonalInfo().getFirstName()!=null && !(response.body().getData().getPersonalInfo().getFirstName().isEmpty()))
    {
        namedata.setText(""+response.body().getData().getPersonalInfo().getFirstName());
    }
    if (response.body().getData().getPersonalInfo().getMobile()!=null && !(response.body().getData().getPersonalInfo().getMobile().isEmpty()))
    {
        phonetv.setText(""+response.body().getData().getPersonalInfo().getMobile());
    }

    if (response.body().getData().getPersonalInfo().getEmail()!=null && !(response.body().getData().getPersonalInfo().getEmail().isEmpty()))
    {
        emailtv.setText(""+response.body().getData().getPersonalInfo().getEmail());
    }
    userLoggedInSession.updateData(response.body().getData().getPersonalInfo().getEmail(),
            ""+response.body().getData().getPersonalInfo().getUserId(),
            response.body().getData().getPersonalInfo().getFirstName(),response.body().getData().getPersonalInfo().getMobile());



}



                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileInfoDataResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MyProfileActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for (String p : mSelectPath) {
                    sb.append(p);
                    sb.append("\n");
                }

                mediapath = "" + sb.toString();


                Glide.with(getApplicationContext())
                        .load("" + mediapath.trim()).

                        apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user_shape))

                        .into(myprofile);
                Log.d("sdadad", "" + mediapath);
                file=new File(""+mediapath.trim());
                updateImage();
            }


        }



    }

    public  void updateImage()
    {        progressDialog.show();


        RetrofitInterface myInterface= RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);

        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("imgUploader",file.getName(),requestFile);

        Call<ImageUploadedResponse> getUserResponseCall=myInterface.updateProfile(multipartBody);
        getUserResponseCall.enqueue(new Callback<ImageUploadedResponse>() {
            @Override
            public void onResponse(Call<ImageUploadedResponse> call, Response<ImageUploadedResponse> response) {
                Log.d("sadadas","ffff"+response.body().getPath());
                userLoggedInSession.updateData(response.body().getPath());
                Glide.with(getApplicationContext())
                        .load("" + userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_PHOTO)).

                        apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user_shape))

                        .into(myprofile);
                updatepath(response.body().getPath());

            }

            @Override
            public void onFailure(Call<ImageUploadedResponse> call, Throwable t) {
progressDialog.dismiss();
                Toast.makeText(MyProfileActivity.this, "Server or Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatepath(final String path) {
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        JsonObject jsonObject=new JsonObject();

        jsonObject.addProperty("user_id",""+user_id);
        jsonObject.addProperty("photo",""+path);

        Log.d("pathhhh",""+jsonObject.toString());
        Call<UnansweredQuestionsResponse> call=myInterface.updatePhoto(jsonObject);
        call.enqueue(new Callback<UnansweredQuestionsResponse>() {
            @Override
            public void onResponse(Call<UnansweredQuestionsResponse> call, Response<UnansweredQuestionsResponse> response) {


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        progressDialog.dismiss();
                        Log.d("hrhrhr",""+response.body().getMessage());

                        Toast.makeText(MyProfileActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
//notifyDataSetChanged();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MyProfileActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UnansweredQuestionsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MyProfileActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
