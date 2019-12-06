package com.eleganzit.tag.ui.activity.school.subfragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.FacilityAdapter;
import com.eleganzit.tag.adapter.HomeFacilityAdapter;
import com.eleganzit.tag.adapter.SchoolHomeFacilityAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.homefacility.Datum;
import com.eleganzit.tag.model.homefacility.FacilitiesResponse;
import com.eleganzit.tag.model.schoolhome.Facility;
import com.eleganzit.tag.model.schoolhome.SchoolAppFacilityResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFacilityFragment extends Fragment {
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
String college_id;
    public SchoolFacilityFragment() {
        // Required empty public constructor
    }
RecyclerView rc_facility;
    ArrayList<Facility> datumArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View v=inflater.inflate(R.layout.fragment_college_facility, container, false);
        rc_facility=v.findViewById(R.id.rc_facility);
        Bundle b = getArguments();
        college_id=b.getString("college_id");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        getFacilityData();
        return v;
    }
    private void getFacilityData() {
        progressDialog.show();
        datumArrayList=new ArrayList<>();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<SchoolAppFacilityResponse> call=myInterface.getfacilityschool(""+college_id);
        call.enqueue(new Callback<SchoolAppFacilityResponse>() {
            @Override
            public void onResponse(Call<SchoolAppFacilityResponse> call, Response<SchoolAppFacilityResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        if (response.body().getData() != null) {
                            datumArrayList.addAll(response.body().getData());
                            rc_facility.setAdapter(new SchoolHomeFacilityAdapter(datumArrayList,getActivity()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SchoolAppFacilityResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
