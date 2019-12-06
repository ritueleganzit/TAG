package com.eleganzit.tag.ui.activity.school.subfragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeGalleryAdapter;
import com.eleganzit.tag.adapter.CollegeGalleryEventAdapter;
import com.eleganzit.tag.adapter.SchoolGalleryEventAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.EventDetail;
import com.eleganzit.tag.model.homegallery.GalleryResponse;
import com.eleganzit.tag.model.schoolhome.Datum;
import com.eleganzit.tag.model.schoolhome.SchoolAppGalleryResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolGalleryFragment extends Fragment {

    RecyclerView rc_events,rc_infra,rc_video;

    public SchoolGalleryFragment() {
        // Required empty public constructor
    }
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
LinearLayout linearlayoutsize;
String college_id;
ArrayList<Datum> arrayList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_college_gallery, container, false);
        //rc_video=v.findViewById(R.id.rc_video);
        linearlayoutsize=v.findViewById(R.id.linearlayoutsize);
        rc_events=v.findViewById(R.id.grid);
        //rc_infra=v.findViewById(R.id.rc_infra);
        Bundle b = getArguments();
        college_id=b.getString("college_id");
       // rc_infra.setAdapter(new CollegeGalleryAdapter(arrayList,linearlayoutsize,getActivity()));
        //rc_events.setAdapter(new CollegeGalleryAdapter(arrayList,linearlayoutsize,getActivity()));
        //rc_video.setAdapter(new CollegeGalleryAdapter(arrayList,linearlayoutsize,getActivity()));
        userLoggedInSession=new UserLoggedInSession(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        getGalleryData();
        return v;
    }

    private void getGalleryData() {
        arrayList=new ArrayList<>();
        Log.d("gdgd",""+college_id);

        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<SchoolAppGalleryResponse> call=myInterface.getschoolgallery(""+college_id);
        call.enqueue(new Callback<SchoolAppGalleryResponse>() {
            @Override
            public void onResponse(Call<SchoolAppGalleryResponse> call, Response<SchoolAppGalleryResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        if (response.body().getData() != null) {
                            if (response.body().getData() != null)
                            {
                                arrayList.addAll(response.body().getData());
                                rc_events.setAdapter(new SchoolGalleryEventAdapter(arrayList,linearlayoutsize,getActivity()));
                            }

                        }



                    }

                }



            }

            @Override
            public void onFailure(Call<SchoolAppGalleryResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
