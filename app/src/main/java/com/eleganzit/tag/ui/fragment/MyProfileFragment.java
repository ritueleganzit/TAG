package com.eleganzit.tag.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CurrentEduAdapter;
import com.eleganzit.tag.adapter.EducationAdapter;
import com.eleganzit.tag.adapter.WorkAdapter;
import com.eleganzit.tag.ui.activity.AddPersonalInfoActivity;
import com.eleganzit.tag.ui.activity.GetCurrentEducation;
import com.eleganzit.tag.ui.activity.GetEducationActivity;
import com.eleganzit.tag.ui.activity.GetWorkActivty;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

RecyclerView rc_educations;
RecyclerView rc_work;
ImageView ed_profile,ed_edu,ed_work,ed_edu_pref;
RecyclerView rc_edu_pref;

    public MyProfileFragment() {
        // Required empty public constructor
    }
    ArrayList<String> arrayList=new ArrayList<>();


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

        rc_educations.setAdapter(new EducationAdapter(arrayList,getActivity()));
        rc_work.setAdapter(new WorkAdapter(arrayList,getActivity()));
        rc_edu_pref.setAdapter(new CurrentEduAdapter(arrayList,getActivity()));
        ed_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddPersonalInfoActivity.class));

            }
        });  ed_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GetEducationActivity.class));

            }
        });ed_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GetWorkActivty.class));

            }
        });ed_edu_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GetCurrentEducation.class));

            }
        });
        return v;
    }

}
