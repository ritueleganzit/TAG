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
import com.eleganzit.tag.model.GetProfileDataResponse;
import com.eleganzit.tag.model.Preferancedata;
import com.eleganzit.tag.model.Workdata;
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
TextView location,user_email,mobile;
RecyclerView rc_work;
ImageView ed_profile,ed_edu,ed_work,ed_edu_pref;
RecyclerView rc_edu_pref;

    public MyProfileFragment() {
        // Required empty public constructor
    }
    ArrayList<Education> educationArrayList;
    ArrayList<Workdata> workdata;
    ArrayList<Preferancedata> prefdata;
    String user_id;
    UserLoggedInSession userLoggedInSession;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_my_profile, container, false);
        rc_educations=v.findViewById(R.id.rc_educations);
        ed_edu=v.findViewById(R.id.ed_edu);
        ed_work=v.findViewById(R.id.ed_work);
        rc_edu_pref=v.findViewById(R.id.rc_edu_pref);
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
                startActivity(new Intent(getActivity(), GetWorkActivty.class).putParcelableArrayListExtra("workdata",workdata));

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
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<GetProfileDataResponse> call=myInterface.getprofiledata("get_profiledata",""+user_id);
        call.enqueue(new Callback<GetProfileDataResponse>() {
            @Override
            public void onResponse(Call<GetProfileDataResponse> call, Response<GetProfileDataResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getPersonaldata()!=null)
                    {
                        for (int i=0; i<response.body().getPersonaldata().size();i++)
                        {
                            if (response.body().getPersonaldata().get(i).getMobile()!=null && !(response.body().getPersonaldata().get(i).getMobile().isEmpty()))
                            {
                                mobile.setText(""+response.body().getPersonaldata().get(i).getMobile());
                                MyProfileActivity.phonetv.setText(""+""+response.body().getPersonaldata().get(i).getMobile());
                            }

                            if (response.body().getPersonaldata().get(i).getLocation()!=null && !(response.body().getPersonaldata().get(i).getLocation().isEmpty()))
                            {
                                location.setText(""+response.body().getPersonaldata().get(i).getLocation());
                            }
                             if (response.body().getPersonaldata().get(i).getUserEmail()!=null && !(response.body().getPersonaldata().get(i).getUserEmail().isEmpty()))
                            {
                                user_email.setText(""+response.body().getPersonaldata().get(i).getUserEmail());
                            }

                        }
                    }

                    if (response.body().getEducation()!=null)
                    {educationArrayList.addAll(response.body().getEducation());

                        Log.d("myprofileee",""+response.body().getEducation().size());
                        rc_educations.setAdapter(new EducationAdapter(response.body().getEducation(),getActivity()));


                    } if (response.body().getWorkdata()!=null)
                    {workdata.addAll(response.body().getWorkdata());

                        Log.d("myprofileee",""+response.body().getWorkdata().size());
                        rc_work.setAdapter(new WorkAdapter(response.body().getWorkdata(),getActivity()));


                    }if (response.body().getPreferanceData()!=null)
                    {
                        prefdata.addAll(response.body().getPreferanceData());
                        Log.d("myprofileee",""+response.body().getPreferanceData().size());
                        rc_edu_pref.setAdapter(new CurrentEduAdapter(prefdata,getActivity()));


                    }
                }


            }

            @Override
            public void onFailure(Call<GetProfileDataResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(getActivity(), "Server or Internet Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
