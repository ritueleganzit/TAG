package com.eleganzit.tag.ui.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CurrentEduAdapter;
import com.eleganzit.tag.adapter.EducationAdapter;
import com.eleganzit.tag.adapter.WorkAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.Education;
import com.eleganzit.tag.model.FetchedUserResponse;
import com.eleganzit.tag.model.GetProfileDataResponse;
import com.eleganzit.tag.model.Preferancedata;
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.model.profileinfo.EducationDetail;
import com.eleganzit.tag.model.profileinfo.ExperienceInfo;
import com.eleganzit.tag.model.profileinfo.PreferenceInfo;
import com.eleganzit.tag.model.profileinfo.ProfileInfoDataResponse;
import com.eleganzit.tag.ui.activity.AddPersonalInfoActivity;
import com.eleganzit.tag.ui.activity.GetCurrentEducation;
import com.eleganzit.tag.ui.activity.GetEducationActivity;
import com.eleganzit.tag.ui.activity.GetWorkActivty;
import com.eleganzit.tag.ui.activity.MyProfileActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

RecyclerView rc_educations;
TextView location,user_email,mobile,dob,numofexp;
RecyclerView rc_work;
ImageView ed_profile,ed_edu,ed_work,ed_edu_pref;
RecyclerView rc_edu_pref;

    public MyProfileFragment() {
        // Required empty public constructor
    }
    ArrayList<EducationDetail> educationArrayList;
    ArrayList<ExperienceInfo> workdata;
    ArrayList<PreferenceInfo> prefdata;
    String user_id,exp;
    UserLoggedInSession userLoggedInSession;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_my_profile, container, false);
        numofexp=v.findViewById(R.id.numofexp);
        rc_educations=v.findViewById(R.id.rc_educations);
        ed_edu=v.findViewById(R.id.ed_edu);
        ed_work=v.findViewById(R.id.ed_work);
        rc_edu_pref=v.findViewById(R.id.rc_edu_pref);
        dob=v.findViewById(R.id.dob);
        ed_profile=v.findViewById(R.id.ed_profile);
        ed_edu_pref=v.findViewById(R.id.ed_edu_pref);
        rc_work=v.findViewById(R.id.rc_work);
        userLoggedInSession=new UserLoggedInSession(getActivity());

        location=v.findViewById(R.id.location);
        user_email=v.findViewById(R.id.user_email);
        mobile=v.findViewById(R.id.mobile);


        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);
        ed_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddPersonalInfoActivity.class));

            }
        });  ed_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GetEducationActivity.class).putParcelableArrayListExtra("eduarray",educationArrayList));

            }
        });ed_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GetWorkActivty.class).putExtra("exp",exp).putParcelableArrayListExtra("workdata",workdata));

            }
        });ed_edu_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GetCurrentEducation.class).putParcelableArrayListExtra("prefdata",prefdata));

            }
        });




        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        getAllProfiledata();
    }

    private void getAllProfiledata() {
        educationArrayList=new ArrayList<>();
        workdata=new ArrayList<>();
        prefdata=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<ProfileInfoDataResponse> call=myInterface.getProfileById(user_id);
        call.enqueue(new Callback<ProfileInfoDataResponse>() {
            @Override
            public void onResponse(Call<ProfileInfoDataResponse> call, Response<ProfileInfoDataResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {


                        if (response.body().getData().getPersonalInfo()!=null)
                        {
                            if (response.body().getData().getPersonalInfo().getMobile()!=null && !(response.body().getData().getPersonalInfo().getMobile().isEmpty())) {
                                mobile.setText(""+response.body().getData().getPersonalInfo().getMobile());
                                MyProfileActivity.phonetv.setText(""+""+response.body().getData().getPersonalInfo().getMobile());
                            }
                            if (response.body().getData().getPersonalInfo().getNationality()!=null && !(response.body().getData().getPersonalInfo().getNationality().isEmpty()))
                            {
                                location.setText(""+response.body().getData().getPersonalInfo().getNationality());
                            }
                            if (response.body().getData().getPersonalInfo().getEmail()!=null && !(response.body().getData().getPersonalInfo().getEmail().isEmpty()))
                            {
                                user_email.setText(""+response.body().getData().getPersonalInfo().getEmail());

                            } if (response.body().getData().getPersonalInfo().getDob()!=null && !(response.body().getData().getPersonalInfo().getDob().isEmpty()))
                            {
                                dob.setText(""+response.body().getData().getPersonalInfo().getDob());

                            }

                        }
                        if (response.body().getData().getTotalExp()!=null)
                        {
                            if (response.body().getData().getTotalExp().get(0).getTotalExp().equalsIgnoreCase("0"))
                            {
                                numofexp.setText(" : 0");

                            }
                            else
                            {
                                numofexp.setText(" : "+response.body().getData().getTotalExp().get(0).getTotalExp());

                            }
                        }
                        if (response.body().getData().getEducationDetail()!=null)
                        {
                            {educationArrayList.addAll(response.body().getData().getEducationDetail());

                               // Log.d("myprofileee",""+response.body().getData().getEducationDetail().size());
                                rc_educations.setAdapter(new EducationAdapter(response.body().getData().getEducationDetail(),getActivity()));


                            }
                        }
                        if (response.body().getData().getTotalExp()!=null)
                        {
                            exp=response.body().getData().getTotalExp().get(0).getTotalExp();
                        }
                        if (response.body().getData().getExperienceInfo()!=null)
                        {

                            if (response.body().getData().getExperienceInfo()!=null)
                            {workdata.addAll(response.body().getData().getExperienceInfo());

                                Log.d("myprofileee",""+response.body().getData().getExperienceInfo().size());
                                rc_work.setAdapter(new WorkAdapter(response.body().getData().getExperienceInfo(),getActivity()));


                            }
                        }

                        if (response.body().getData().getPreferenceInfo()!=null)
                        {
                            prefdata.addAll(response.body().getData().getPreferenceInfo());
                            Log.d("myprofileee",""+response.body().getData().getPreferenceInfo().size());
                            rc_edu_pref.setAdapter(new CurrentEduAdapter(prefdata,getActivity()));


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileInfoDataResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server or Internet Error", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
