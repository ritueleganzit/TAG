package com.eleganzit.tag.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeGalleryAdapter;
import com.eleganzit.tag.adapter.CollegeGalleryEventAdapter;
import com.eleganzit.tag.adapter.CollegeGalleryInfraAdapter;
import com.eleganzit.tag.adapter.CollegeGalleryVideoAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.EventDetail;
import com.eleganzit.tag.model.QuestionAnsResponse;
import com.eleganzit.tag.model.homegallery.Event;
import com.eleganzit.tag.model.homegallery.GalleryResponse;
import com.eleganzit.tag.model.homegallery.Infrastructure;
import com.eleganzit.tag.model.homegallery.Video;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;
import com.eleganzit.tag.utils.EqualSpacingItemDecoration;
import com.eleganzit.tag.utils.SwipeController;
import com.eleganzit.tag.utils.UserLoggedInSession;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeGalleryFragment extends Fragment {

    RecyclerView rc_events,rc_infra,rc_video;

    RecyclerView grid;
    ArrayList<Event> eventArrayList;
    ArrayList<Infrastructure> infrastructures;
    ArrayList<Video> videoArrayList;
    public CollegeGalleryFragment() {
        // Required empty public constructor
    } ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    int college_id;

    LinearLayout linearlayoutsize;
ArrayList<EventDetail> arrayList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_college_gallery, container, false);
       // rc_video=v.findViewById(R.id.rc_video);
        linearlayoutsize=v.findViewById(R.id.linearlayoutsize);
        rc_events=v.findViewById(R.id.grid);
        //rc_infra=v.findViewById(R.id.rc_infra);
        EventDetail eventDetail=new EventDetail();
        eventDetail.setImageUrl("https://eleganzit.online/img/tag_upload/college/images/myimage.jpeg");
        EventDetail eventDetail1=new EventDetail();
        eventDetail1.setImageUrl("https://eleganzit.online/img/tag_upload/college/images/myimage.jpeg");
        arrayList.add(eventDetail);
        arrayList.add(eventDetail1);
        Bundle b = getArguments();
        grid=v.findViewById(R.id.grid);

        grid.addItemDecoration(new EqualSpacingItemDecoration(3, EqualSpacingItemDecoration.GRID)); // 16px. In practice, you'll want to use getDimensionPixelSize
        college_id=b.getInt("college_id",0);
      //
     //
      //
       // CollegeDetailActivity.college_nametv.setText("Gallery");

        userLoggedInSession=new UserLoggedInSession(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        getGalleryData();
        return v;
    }

    private void getGalleryData() {
        eventArrayList=new ArrayList<>();
        infrastructures=new ArrayList<>();
        videoArrayList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<GalleryResponse> call=myInterface.getGallery(""+college_id);
        call.enqueue(new Callback<GalleryResponse>() {
            @Override
            public void onResponse(Call<GalleryResponse> call, Response<GalleryResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        if (response.body().getData() != null) {
                            if (response.body().getData() != null)
                            {
                                eventArrayList.addAll(response.body().getData());
                                rc_events.setAdapter(new CollegeGalleryEventAdapter(eventArrayList,linearlayoutsize,getActivity()));
                            }

                        }



                    }

                        }



            }

            @Override
            public void onFailure(Call<GalleryResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
