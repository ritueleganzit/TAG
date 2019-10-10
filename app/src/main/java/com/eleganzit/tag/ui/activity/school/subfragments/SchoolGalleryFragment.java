package com.eleganzit.tag.ui.activity.school.subfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeGalleryAdapter;
import com.eleganzit.tag.model.EventDetail;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolGalleryFragment extends Fragment {

    RecyclerView rc_events,rc_infra,rc_video;

    public SchoolGalleryFragment() {
        // Required empty public constructor
    }
LinearLayout linearlayoutsize;
ArrayList<EventDetail> arrayList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_college_gallery, container, false);
        rc_video=v.findViewById(R.id.rc_video);
        linearlayoutsize=v.findViewById(R.id.linearlayoutsize);
        rc_events=v.findViewById(R.id.rc_events);
        rc_infra=v.findViewById(R.id.rc_infra);
        EventDetail eventDetail=new EventDetail();
        eventDetail.setImageUrl("https://eleganzit.online/img/tag_upload/college/images/myimage.jpeg");
        EventDetail eventDetail1=new EventDetail();
        eventDetail1.setImageUrl("https://eleganzit.online/img/tag_upload/college/images/myimage.jpeg");
        arrayList.add(eventDetail);
        arrayList.add(eventDetail1);
       // rc_infra.setAdapter(new CollegeGalleryAdapter(arrayList,linearlayoutsize,getActivity()));
        //rc_events.setAdapter(new CollegeGalleryAdapter(arrayList,linearlayoutsize,getActivity()));
        //rc_video.setAdapter(new CollegeGalleryAdapter(arrayList,linearlayoutsize,getActivity()));

        return v;
    }

}
