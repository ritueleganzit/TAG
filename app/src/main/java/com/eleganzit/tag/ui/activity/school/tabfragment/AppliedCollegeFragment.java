package com.eleganzit.tag.ui.activity.school.tabfragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.SelectCourseActivity;
import com.eleganzit.tag.adapter.AppliedAdapter;
import com.eleganzit.tag.adapter.CollegeListAdapter;
import com.eleganzit.tag.adapter.SubFragmentAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AppliedCollegeList;
import com.eleganzit.tag.model.AppliedCollegeListResponse;
import com.eleganzit.tag.ui.activity.TopCollegesActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppliedCollegeFragment extends Fragment {
RecyclerView rc_applied_college;
    ProgressDialog progressDialog;
    ArrayList<AppliedCollegeList> appliedCollegeLists;
    UserLoggedInSession userLoggedInSession;
    public AppliedCollegeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_applied_college, container, false);
        rc_applied_college=v.findViewById(R.id.rc_applied_college);

        userLoggedInSession=new UserLoggedInSession(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        getApplied();

        return v;
    }

    private void getApplied() {
        appliedCollegeLists=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<AppliedCollegeListResponse> appliedCollegeListResponseCall=myInterface.appliedCollegeList(""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        appliedCollegeListResponseCall.enqueue(new Callback<AppliedCollegeListResponse>() {
            @Override
            public void onResponse(Call<AppliedCollegeListResponse> call, Response<AppliedCollegeListResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {

                        if (response.body().getData() != null) {
                            appliedCollegeLists.addAll(response.body().getData());

                            rc_applied_college.setAdapter(new AppliedAdapter(appliedCollegeLists,getActivity()));

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<AppliedCollegeListResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(getActivity(), "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
