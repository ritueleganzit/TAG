package com.eleganzit.tag.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ViewQuestionsActivity;
import com.eleganzit.tag.adapter.UnansweredAdapter;
import com.eleganzit.tag.adapter.ViewDiscussionAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.discussion.DiscussionListResponse;
import com.eleganzit.tag.model.unanswered.Datum;
import com.eleganzit.tag.model.unanswered.UnansweredQuestionsResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnansweredFragment extends Fragment {

    RecyclerView rc_discussion;

    ProgressDialog progressDialog;
    ArrayList<Datum> arrayList;
    UserLoggedInSession userLoggedInSession;
    public UnansweredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_unanswered, container, false);
        rc_discussion=v.findViewById(R.id.rc_discussion);
        userLoggedInSession = new UserLoggedInSession(getActivity());

        userLoggedInSession.checkLogin();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        viewQuestions();;
        return v;
    }

    private void viewQuestions() {
        arrayList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<UnansweredQuestionsResponse> call=myInterface.unAnsQuesList();
        call.enqueue(new Callback<UnansweredQuestionsResponse>() {
            @Override
            public void onResponse(Call<UnansweredQuestionsResponse> call, Response<UnansweredQuestionsResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        if (response.body().getData()!=null)
                        {
                            rc_discussion.setAdapter(new UnansweredAdapter(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID),response.body().getData(),getActivity()));

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UnansweredQuestionsResponse> call, Throwable t) {

            }
        });

    }

}
