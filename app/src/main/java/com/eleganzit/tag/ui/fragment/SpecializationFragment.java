package com.eleganzit.tag.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.SelectCourseActivity;
import com.eleganzit.tag.SelectSpecializationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecializationFragment extends Fragment {


    public SpecializationFragment() {
        // Required empty public constructor
    }
    TextView txt_content;
String strtext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View  v=inflater.inflate(R.layout.fragment_specialization, container, false);


        txt_content=v.findViewById(R.id.txt_content);
        if (getArguments()!=null)
        {
            strtext = getArguments().getString("edttext");

        }
        Log.d("hfskdjhfs",""+strtext);

        if (strtext!=null && !(strtext.isEmpty()))
        {
            txt_content.setText(SelectSpecializationActivity.getSpecialization.getEligibility());



        }
        else {
            txt_content.setText(SelectCourseActivity.coursesData.getCourse_specialization());
        }

        return v;
    }

}
