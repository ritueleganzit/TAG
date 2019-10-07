package com.eleganzit.tag.ui.activity.result.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.result.adapter.TenthResultAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntranceFragment extends Fragment {
    RecyclerView tenthresult;


    public EntranceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_entrance, container, false);
        tenthresult=v.findViewById(R.id.tenthresult);
        tenthresult.setAdapter(new TenthResultAdapter(getActivity()));

        return v;
    }

}
