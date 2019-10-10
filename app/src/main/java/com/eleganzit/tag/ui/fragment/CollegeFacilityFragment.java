package com.eleganzit.tag.ui.fragment;


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
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.homefacility.Datum;
import com.eleganzit.tag.model.homefacility.FacilitiesResponse;
import com.eleganzit.tag.model.homegallery.GalleryResponse;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;
import com.eleganzit.tag.utils.HomeSecondSliderAdapter;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeFacilityFragment extends Fragment {

ArrayList<Datum> datumArrayList;
    public CollegeFacilityFragment() {
        // Required empty public constructor
    }
    int college_id;
RecyclerView rc_facility;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View v=inflater.inflate(R.layout.fragment_college_facility, container, false);
        rc_facility=v.findViewById(R.id.rc_facility);
        userLoggedInSession=new UserLoggedInSession(getActivity());
        Bundle b = getArguments();
        college_id=b.getInt("college_id",0);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        getFacilityData();
      //
        //CollegeDetailActivity.college_nametv.setText("Facilities");
        return v;
    }

    private void getFacilityData() {
        progressDialog.show();
        datumArrayList=new ArrayList<>();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<FacilitiesResponse> call=myInterface.getfacility(""+college_id);
        call.enqueue(new Callback<FacilitiesResponse>() {
            @Override
            public void onResponse(Call<FacilitiesResponse> call, Response<FacilitiesResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        if (response.body().getData() != null) {
                            datumArrayList.addAll(response.body().getData());
                            rc_facility.setAdapter(new HomeFacilityAdapter(datumArrayList,getActivity()));
                        }
                    }
                }
                        }

            @Override
            public void onFailure(Call<FacilitiesResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
