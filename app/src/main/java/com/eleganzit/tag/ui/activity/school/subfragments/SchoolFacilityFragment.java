package com.eleganzit.tag.ui.activity.school.subfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.FacilityAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFacilityFragment extends Fragment {


    public SchoolFacilityFragment() {
        // Required empty public constructor
    }
RecyclerView rc_facility;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View v=inflater.inflate(R.layout.fragment_college_facility, container, false);
        rc_facility=v.findViewById(R.id.rc_facility);
       // rc_facility.setAdapter(new FacilityAdapter(getActivity()));

        return v;
    }

}
