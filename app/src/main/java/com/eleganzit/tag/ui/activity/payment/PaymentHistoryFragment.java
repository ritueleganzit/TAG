package com.eleganzit.tag.ui.activity.payment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.tag.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentHistoryFragment extends Fragment {


    public PaymentHistoryFragment() {
        // Required empty public constructor
    }

    RecyclerView rc_supplied_college;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_payment_history, container, false);
        rc_supplied_college=v.findViewById(R.id.rc_supplied_college);

        rc_supplied_college.setAdapter(new PaymentListAdapter(getActivity()));
        return v;
    }

}
