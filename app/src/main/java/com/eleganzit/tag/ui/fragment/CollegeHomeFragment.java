package com.eleganzit.tag.ui.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.HelpFAQActivity;
import com.eleganzit.tag.HomeActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeGalleryAdapter;
import com.eleganzit.tag.adapter.CoursesHomeAdapter;
import com.eleganzit.tag.adapter.FacilityAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CourseDetail;
import com.eleganzit.tag.model.EventDetail;
import com.eleganzit.tag.model.GalleryData;
import com.eleganzit.tag.model.GetCollegeById;
import com.eleganzit.tag.ui.activity.AddBasicInformationActivity;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import me.nereo.multi_image_selector.bean.Image;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeHomeFragment extends Fragment {

    TextView applynow,collegename,collagelocation,accreditation,approved_by,course_duration,study_mode,placement,ownership,university_name,rank,viewAll,hideAll,viewallgallery,email;
    LinearLayout linearlayoutsize;
    public CollegeHomeFragment() {
        // Required empty public constructor
    }
    RatingBar myRatingBar;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    RecyclerView rc_events,courses_rc,rc_facility;
    ArrayList<CourseDetail> courseDetailArrayList;
    ArrayList<CourseDetail> firstcourseDetailArrayList;
    ArrayList<EventDetail> galleryDataArrayList;
    ImageView imgbg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_college_home, container, false);
        applynow=v.findViewById(R.id.applynow);
        myRatingBar=v.findViewById(R.id.myRatingBar);
        email=v.findViewById(R.id.email);
        course_duration=v.findViewById(R.id.course_duration);
        collagelocation=v.findViewById(R.id.collagelocation);
        viewallgallery=v.findViewById(R.id.viewallgallery);
        approved_by=v.findViewById(R.id.approved_by);
        accreditation=v.findViewById(R.id.accreditation);
        placement=v.findViewById(R.id.placement);
        collegename=v.findViewById(R.id.collegename);
        rc_events=v.findViewById(R.id.rc_events);
        viewAll=v.findViewById(R.id.viewAll);
        courses_rc=v.findViewById(R.id.courses_rc);
        university_name=v.findViewById(R.id.university_name);
        rc_facility=v.findViewById(R.id.rc_facility);
        rank=v.findViewById(R.id.rank);
        ownership=v.findViewById(R.id.ownership);
        study_mode=v.findViewById(R.id.study_mode);
        imgbg=v.findViewById(R.id.imgbg);
        userLoggedInSession=new UserLoggedInSession(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        linearlayoutsize=v.findViewById(R.id.linearlayoutsize);

        rc_facility.setAdapter(new FacilityAdapter(getActivity()));
        imgbg.getLayoutParams().width= (int) (getScreenWidthInPXs(getContext(),getActivity()));
        imgbg.getLayoutParams().height= (int) (getScreenWidthInPXs(getContext(),getActivity())/3.5);
        applynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddBasicInformationActivity.class));

            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollegeDetailActivity.htab_viewpager.setCurrentItem(1);

            }
        });   viewallgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollegeDetailActivity.htab_viewpager.setCurrentItem(2);

            }
        });
        getCollegeData();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void getCollegeData(){
        courseDetailArrayList=new ArrayList<>();
        firstcourseDetailArrayList=new ArrayList<>();
        galleryDataArrayList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);

        final Call<GetCollegeById> getCollegeByIdCall=myInterface.getCollegeById();
        getCollegeByIdCall.enqueue(new Callback<GetCollegeById>() {
            @Override
            public void onResponse(Call<GetCollegeById> call, Response<GetCollegeById> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {
                        if (response.body().getData().getCollegeDetail()!=null)
                        {
                            if (response.body().getData().getCollegeDetail().getCollegeName()!=null  && !(response.body().getData().getCollegeDetail().getCollegeName().isEmpty()))
                            {
                                collegename.setText(""+response.body().getData().getCollegeDetail().getCollegeName());
                            }

                            if (response.body().getData().getCollegeDetail().getCollegeCity()!=null  && !(response.body().getData().getCollegeDetail().getCollegeCity().isEmpty()))
                            {
                                collagelocation.setText(""+response.body().getData().getCollegeDetail().getCollegeCity()+",");
                            }
                            else
                            {
                                collagelocation.setText("");
                            }

                            if (response.body().getData().getCollegeDetail().getCollegeCountry()!=null  && !(response.body().getData().getCollegeDetail().getCollegeCountry().isEmpty()))
                            {
                                collagelocation.append(" "+response.body().getData().getCollegeDetail().getCollegeCountry()+"");
                            }

                            if (response.body().getData().getCollegeDetail().getAccreditation()!=null  && !(response.body().getData().getCollegeDetail().getAccreditation().isEmpty()))
                            {
                                accreditation.setText("Accredited by: "+response.body().getData().getCollegeDetail().getAccreditation()+"");
                            }
                            else
                            {
                                accreditation.setText("Accredited by: -");
                            }

                            if (response.body().getData().getCollegeDetail().getApprovedBy()!=null  && !(response.body().getData().getCollegeDetail().getApprovedBy().isEmpty()))
                            {
                                approved_by.setText("Approved by: "+response.body().getData().getCollegeDetail().getApprovedBy()+"");
                            }
                            else
                            {
                                approved_by.setText("Approved by: -");
                            }
                            if (response.body().getData().getCollegeDetail().getPlacement()!=null  && !(response.body().getData().getCollegeDetail().getPlacement().isEmpty()))
                            {
                                placement.setText("Placement : "+response.body().getData().getCollegeDetail().getPlacement()+"");
                            }
                            else
                            {
                                placement.setText("Placement : -");
                            }if (response.body().getData().getCollegeDetail().getRank()!=null  && !(response.body().getData().getCollegeDetail().getRank().isEmpty()))
                            {
                                rank.setVisibility(View.VISIBLE);
                                rank.setText("Rank : "+response.body().getData().getCollegeDetail().getRank()+"");
                            }
                            else
                            {
                                rank.setVisibility(View.GONE);
                            }
 if (response.body().getData().getCollegeDetail().getOwnership()!=null  && !(response.body().getData().getCollegeDetail().getOwnership().isEmpty()))
                            {
                                ownership.setText(""+response.body().getData().getCollegeDetail().getOwnership()+"");
                            }
                            else
                            {
                                ownership.setText("");
                            } if (response.body().getData().getCollegeDetail().getUniversityName()!=null  && !(response.body().getData().getCollegeDetail().getUniversityName().isEmpty()))
                            {
                                university_name.setText(""+response.body().getData().getCollegeDetail().getUniversityName()+"");
                            }
                            else
                            {
                                university_name.setText("");
                            }


                            if (response.body().getData().getCollegeDetail().getCourseDuration()!=null  && !(response.body().getData().getCollegeDetail().getCourseDuration().isEmpty()))
                            {
                                course_duration.setVisibility(View.VISIBLE);
                                course_duration.setText(""+response.body().getData().getCollegeDetail().getCourseDuration());


                            }
                            else
                            {
                                course_duration.setVisibility(View.GONE);
                            }if (response.body().getData().getCollegeDetail().getStudyMode()!=null  && !(response.body().getData().getCollegeDetail().getStudyMode().isEmpty()))
                            {
                                study_mode.setVisibility(View.VISIBLE);
                                study_mode.setText(""+response.body().getData().getCollegeDetail().getStudyMode());


                            }
                            else
                            {
                                study_mode.setVisibility(View.GONE);
                            }if (response.body().getData().getCollegeDetail().getRatings()!=null)
                            {
                                myRatingBar.setRating(Float.parseFloat(""+response.body().getData().getCollegeDetail().getRatings()));


                            }
                            else
                            {
                                myRatingBar.setRating(0);
                            }

                        }

                        //setCourse

                        if (response.body().getData().getCourseDetail()!=null)
                        {
                            courseDetailArrayList.addAll(response.body().getData().getCourseDetail());


                            if (response.body().getData().getCourseDetail().size()<3)
                            {
                                firstcourseDetailArrayList.addAll(response.body().getData().getCourseDetail());

                            }
                            else {
                                for (int i=0;i<3;i++)
                                {
                                    CourseDetail courseDetail=new CourseDetail();
                                    courseDetail.setCourseName(""+response.body().getData().getCourseDetail().get(i).getCourseName());
                                    courseDetail.setCourseFees(""+response.body().getData().getCourseDetail().get(i).getCourseFees());
                                    courseDetail.setSpecializationName(""+response.body().getData().getCourseDetail().get(i).getSpecializationName());
                    firstcourseDetailArrayList.add(courseDetail);
                                }

                            }
                            Log.d("fsfs",""+response.body().getData().getCourseDetail().size());
                            courses_rc.setAdapter(new CoursesHomeAdapter(firstcourseDetailArrayList,getActivity()));

                        }
  if (response.body().getData().getGallery()!=null)
                        {


                            if (response.body().getData().getCourseDetail().size()<3)
                            {
                                galleryDataArrayList.addAll(response.body().getData().getGallery().getEventDetails());

                            }
                            else {
                                for (int i=0;i<3;i++)
                                {
                                    EventDetail courseDetail=new EventDetail();
                                    courseDetail.setImageUrl(""+response.body().getData().getGallery().getEventDetails().get(i).getImageUrl());

                    galleryDataArrayList.add(courseDetail);
                                }

                            }
                            Log.d("fsfs",""+response.body().getData().getCourseDetail().size());
                            rc_events.setAdapter(new CollegeGalleryAdapter(galleryDataArrayList,linearlayoutsize,getActivity()));

                        }




                    }
                }
            }

            @Override
            public void onFailure(Call<GetCollegeById> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static int getScreenWidthInPXs(Context context, Activity activity){

        DisplayMetrics dm = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

}
