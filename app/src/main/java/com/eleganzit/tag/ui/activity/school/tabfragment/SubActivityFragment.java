package com.eleganzit.tag.ui.activity.school.tabfragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.SubFragmentAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.QuestionAnsResponse;
import com.eleganzit.tag.model.QuestionData;
import com.eleganzit.tag.model.askquestion.UserQuestionListResponse;
import com.eleganzit.tag.model.askquestion.Userquestion;
import com.eleganzit.tag.ui.activity.TopCollegesActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubActivityFragment extends Fragment {

    ArrayList<Userquestion> arrayList;
RecyclerView rc_supplied_college;
FrameLayout nodataframe;
LinearLayout lin_sub;
    public SubActivityFragment() {
        // Required empty public constructor
    }
    TextView numofquestion;

    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_sub_activity, container, false);
        numofquestion=v.findViewById(R.id.numofquestion);
        lin_sub=v.findViewById(R.id.lin_sub);
        nodataframe=v.findViewById(R.id.nodataframe);
        rc_supplied_college=v.findViewById(R.id.rc_supplied_college);
        userLoggedInSession=new UserLoggedInSession(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        getQues();
        return v;
    }

    private void getQues() {
        arrayList=new ArrayList<>();
        progressDialog.show();
        Log.d("asdad",""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<UserQuestionListResponse> call=myInterface.getquestion(userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        call.enqueue(new Callback<UserQuestionListResponse>() {
            @Override
            public void onResponse(Call<UserQuestionListResponse> call, Response<UserQuestionListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    nodataframe.setVisibility(View.GONE);
                    lin_sub.setVisibility(View.VISIBLE);

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        if (response.body().getUserquestions()!=null)
                        {
                            Log.d("asdad","sadad"+response.body().getUserquestions().size());


                            numofquestion.setText(""+response.body().getUserquestions().size());
                            arrayList.addAll(response.body().getUserquestions());
                            rc_supplied_college.setAdapter(new SubFragmentAdapter(arrayList,getActivity()));

                        }

                    }
                    }
                else
                {
                    nodataframe.setVisibility(View.VISIBLE);
                    lin_sub.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<UserQuestionListResponse> call, Throwable t) {
                progressDialog.dismiss();
                lin_sub.setVisibility(View.GONE);
                nodataframe.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Server and Internet Error", Toast.LENGTH_SHORT).show();


            }
        });
    }

}
