package com.eleganzit.tag.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.DiscussionActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.ViewQuestionsActivity;
import com.eleganzit.tag.adapter.DisCommentAdapter;
import com.eleganzit.tag.adapter.ViewDiscussionAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.askquestion.AskQuestionResponse;
import com.eleganzit.tag.model.discussion.AnsList;
import com.eleganzit.tag.model.discussion.Datum;
import com.eleganzit.tag.model.discussion.DiscussionListResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscussionFragment extends Fragment {

    RecyclerView rc_discussion;

    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    ArrayList<Datum> arrayList;
    public DiscussionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_discussion, container, false);
        rc_discussion=v.findViewById(R.id.rc_discussion);
        userLoggedInSession = new UserLoggedInSession(getActivity());

        userLoggedInSession.checkLogin();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        viewQuestions();
        return v;
    }
    public void viewQuestions()
    {
        arrayList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<DiscussionListResponse> call=myInterface.discussionList(Integer.parseInt(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID)));
        call.enqueue(new Callback<DiscussionListResponse>() {
            @Override
            public void onResponse(Call<DiscussionListResponse> call, Response<DiscussionListResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        if (response.body().getData()!=null)
                        {
                            rc_discussion.setAdapter(new ViewDiscussionAdapter(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID),response.body().getData(),getActivity()));

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DiscussionListResponse> call, Throwable t) {
progressDialog.dismiss();
            }
        });

    }
}
