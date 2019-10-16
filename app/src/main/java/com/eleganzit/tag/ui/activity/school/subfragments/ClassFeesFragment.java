package com.eleganzit.tag.ui.activity.school.subfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CoursesAdapter;
import com.eleganzit.tag.ui.activity.school.adpater.CoursesSchoolAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFeesFragment extends Fragment {


    RecyclerView rc_course_fees;

    public ClassFeesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_course_fees, container, false);
        rc_course_fees=v.findViewById(R.id.rc_course_fees);
        rc_course_fees.setAdapter(new CoursesSchoolAdapter(getActivity()));

        return v;

    }

}
