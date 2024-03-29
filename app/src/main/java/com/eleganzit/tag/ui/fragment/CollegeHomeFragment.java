package com.eleganzit.tag.ui.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.eleganzit.tag.HelpFAQActivity;
import com.eleganzit.tag.HomeActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeGalleryAdapter;
import com.eleganzit.tag.adapter.CoursesHomeAdapter;
import com.eleganzit.tag.adapter.FacilityAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CollegeHomeResponse;
import com.eleganzit.tag.model.CourceFee;
import com.eleganzit.tag.model.CourseDetail;
import com.eleganzit.tag.model.EventDetail;
import com.eleganzit.tag.model.FacilityData;
import com.eleganzit.tag.model.GalleryData;
import com.eleganzit.tag.model.GetCollegeById;
import com.eleganzit.tag.model.homegallery.Event;
import com.eleganzit.tag.model.newhome.CourseResult;
import com.eleganzit.tag.ui.activity.AddBasicInformationActivity;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import me.nereo.multi_image_selector.bean.Image;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeHomeFragment extends Fragment {

    TextView applynow,collegename,collagelocation,accreditation,approved_by,course_duration,study_mode,placement,ownership,university_name,rank,viewAll,hideAll,viewallgallery,email,address,mobilenum,website,e_sp;
    LinearLayout linearlayoutsize;
    public CollegeHomeFragment() {
        // Required empty public constructor
    }
    RatingBar myRatingBar;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    RecyclerView rc_events,courses_rc,rc_facility;
    ArrayList<CourseResult> courseDetailArrayList;
    ArrayList<CourseResult> firstcourseDetailArrayList;
    ArrayList<Event> galleryDataArrayList;
    ArrayList<FacilityData> facilityDataArrayList;
    ImageView imgbg;
    int college_id;
String college_name;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_college_home, container, false);
        applynow=v.findViewById(R.id.applynow);
        myRatingBar=v.findViewById(R.id.myRatingBar);
        website=v.findViewById(R.id.website);
        e_sp=v.findViewById(R.id.e_sp);
        email=v.findViewById(R.id.email);
        course_duration=v.findViewById(R.id.course_duration);
        address=v.findViewById(R.id.address);
        collagelocation=v.findViewById(R.id.collagelocation);
        viewallgallery=v.findViewById(R.id.viewallgallery);
        approved_by=v.findViewById(R.id.approved_by);
        accreditation=v.findViewById(R.id.accreditation);
        placement=v.findViewById(R.id.placement);
        collegename=v.findViewById(R.id.collegename);
        rc_events=v.findViewById(R.id.rc_events);
        viewAll=v.findViewById(R.id.viewAll);
        mobilenum=v.findViewById(R.id.mobilenum);
        courses_rc=v.findViewById(R.id.courses_rc);
        university_name=v.findViewById(R.id.university_name);
        rc_facility=v.findViewById(R.id.rc_facility);
        rank=v.findViewById(R.id.rank);
        ownership=v.findViewById(R.id.ownership);
        study_mode=v.findViewById(R.id.study_mode);
        imgbg=v.findViewById(R.id.imgbg);
        userLoggedInSession=new UserLoggedInSession(getActivity());
        Bundle b = getArguments();
        college_id=b.getInt("college_id",0);
        college_name=b.getString("college_name");
       String stre_sp=b.getString("e_sp");

        e_sp.setText(""+stre_sp);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        linearlayoutsize=v.findViewById(R.id.linearlayoutsize);

        sharedPreferences=getActivity().getSharedPreferences("dataapply",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        imgbg.getLayoutParams().width= (int) (getScreenWidthInPXs(getContext(),getActivity()));
        imgbg.getLayoutParams().height= (int) (getScreenWidthInPXs(getContext(),getActivity())/3.5);
        applynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
editor.putString("college_id",""+college_id);
editor.commit();
                startActivity(new Intent(getActivity(), AddBasicInformationActivity.class));

            }
        });
        if (college_name!=null && !(college_name.isEmpty())){
            CollegeDetailActivity.college_nametv.setText(""+college_name);
        }

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
        if (college_id!=0)
        {
            getCollegeData();
        }

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
        facilityDataArrayList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
       // Toast.makeText(getActivity(), "asdsa"+college_id, Toast.LENGTH_SHORT).show();

        final Call<CollegeHomeResponse> getCollegeByIdCall=myInterface.getCollegeById(""+college_id);
        getCollegeByIdCall.enqueue(new Callback<CollegeHomeResponse>() {
            @Override
            public void onResponse(Call<CollegeHomeResponse> call, Response<CollegeHomeResponse> response) {
                progressDialog.dismiss();
                //Toast.makeText(getActivity(), "asdsa"+college_id, Toast.LENGTH_SHORT).show();
                Log.d("fsfs","iii"+response.errorBody());
                Log.d("fsfs","iii"+response.message());
                Log.d("fsfs","iii"+college_id);

                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {
                        mobilenum.setText(response.body().getData().getCollegeInfo().get(0).getPhone());
                        email.setText(response.body().getData().getCollegeInfo().get(0).getEmail());
                        website.setText(response.body().getData().getCollegeInfo().get(0).getWeb());

                        RequestOptions options = new RequestOptions()
                                .centerCrop()
                                .placeholder(R.drawable.schools_tri)
                                .error(R.drawable.schools_tri)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .priority(Priority.HIGH);

                        Glide.with(getActivity()).load(response.body().getData().getCollegeInfo().get(0).getCollegeImage())
                                .apply(options)
                                .into(imgbg);

                         if (response.body().getData().getCollegeInfo().get(0).getCollegeName()!=null  && !(response.body().getData().getCollegeInfo().get(0).getCollegeName().isEmpty()))
                            {
                                collegename.setText(""+response.body().getData().getCollegeInfo().get(0).getCollegeName());
                            }

                            if (response.body().getData().getCollegeInfo().get(0).getCollegeCity()!=null  && !(response.body().getData().getCollegeInfo().get(0).getCollegeCity().isEmpty()))
                            {
                                collagelocation.setText(""+response.body().getData().getCollegeInfo().get(0).getCollegeCity()+",");
                            }
                            else
                            {
                                collagelocation.setText("");
                            }

                            if (response.body().getData().getCollegeInfo().get(0).getCollegeCountry()!=null  && !(response.body().getData().getCollegeInfo().get(0).getCollegeCountry().isEmpty()))
                            {
                                collagelocation.append(" "+response.body().getData().getCollegeInfo().get(0).getCollegeCountry()+"");
                                address.setText(collagelocation.getText().toString());
                            }

                            if (response.body().getData().getCollegeInfo().get(0).getAccreditation()!=null  && !(response.body().getData().getCollegeInfo().get(0).getAccreditation().isEmpty()))
                            {
                                accreditation.setText("Accredited by: "+response.body().getData().getCollegeInfo().get(0).getAccreditation()+"");
                            }
                            else
                            {
                                accreditation.setText("Accredited by: -");
                            }

                            if (response.body().getData().getCollegeInfo().get(0).getApprovedBy()!=null  && !(response.body().getData().getCollegeInfo().get(0).getApprovedBy().isEmpty()))
                            {
                                approved_by.setText("Approved by: "+response.body().getData().getCollegeInfo().get(0).getApprovedBy()+"");
                            }
                            else
                            {
                                approved_by.setText("Approved by: -");
                            }
                            if (response.body().getData().getCollegeInfo().get(0).getPlacement()!=null  && !(response.body().getData().getCollegeInfo().get(0).getPlacement().isEmpty()))
                            {
                                placement.setText("Placement : "+response.body().getData().getCollegeInfo().get(0).getPlacement()+"");
                            }
                            else
                            {
                                placement.setText("Placement : -");
                            }if (response.body().getData().getCollegeInfo().get(0).getRank()!=null  && !(response.body().getData().getCollegeInfo().get(0).getRank().isEmpty()))
                            {
                                rank.setVisibility(View.VISIBLE);
                                rank.setText("Rank : "+response.body().getData().getCollegeInfo().get(0).getRank()+"");
                            }
                            else
                            {
                                rank.setVisibility(View.GONE);
                            }
 if (response.body().getData().getCollegeInfo().get(0).getOwnership()!=null  && !(response.body().getData().getCollegeInfo().get(0).getOwnership().isEmpty()))
                            {
                                ownership.setText(""+response.body().getData().getCollegeInfo().get(0).getOwnership()+"");
                            }
                            else
                            {
                                ownership.setText("");
                            } if (response.body().getData().getCollegeInfo().get(0).getUniversityName()!=null  && !(response.body().getData().getCollegeInfo().get(0).getUniversityName().isEmpty()))
                            {
                                university_name.setText(""+response.body().getData().getCollegeInfo().get(0).getUniversityName()+"");
                            }
                            else
                            {
                                university_name.setText("");
                            }


                            if (response.body().getData().getCollegeInfo().get(0).getYears()!=null  && !(response.body().getData().getCollegeInfo().get(0).getYears().isEmpty()))
                            {
                                course_duration.setVisibility(View.VISIBLE);
                                course_duration.setText(""+response.body().getData().getCollegeInfo().get(0).getYears());


                            }
                            else
                            {
                                course_duration.setVisibility(View.GONE);
                            }/*if (response.body().getData().getCollegeInfo().get(0).getStudyMode()!=null  && !(response.body().getData().getCollegeDetail().getStudyMode().isEmpty()))
                            {
                                study_mode.setVisibility(View.VISIBLE);
                                study_mode.setText(""+response.body().getData().getCollegeDetail().getStudyMode());


                            }
                            else
                            {
                                study_mode.setVisibility(View.GONE);
                            }*/if (response.body().getData().getCollegeInfo().get(0).getRatings()!=null)
                            {
                                myRatingBar.setRating(Float.parseFloat(""+response.body().getData().getCollegeInfo().get(0).getRatings()));


                            }
                            else
                            {
                                myRatingBar.setRating(0);
                            }



                        //setCourse

                        if (response.body().getData().getCource_detail()!=null)
                        {
                            courseDetailArrayList.addAll(response.body().getData().getCource_detail().get(0).getCourseResult());
                            Log.d("fsfs","ghjgj"+response.body().getData().getCource_detail().get(0).getCourseResult());


                            if (response.body().getData().getCource_detail().size()<3)
                            {
                                firstcourseDetailArrayList.addAll(response.body().getData().getCource_detail().get(0).getCourseResult());

                            }
                            else {
                                for (int i=0;i<3;i++)
                                {
                                    CourseResult courseDetail=new CourseResult();
                                    courseDetail.setCourseName(""+response.body().getData().getCource_detail().get(i).getCourseName());
                                    courseDetail.setCourseFees(""+response.body().getData().getCource_detail().get(i).getCourseResult().get(i).getCourseFees());
                                    courseDetail.setSpecializationName(""+response.body().getData().getCource_detail().get(i).getCourseResult().get(i).getSpecializationName());
                    firstcourseDetailArrayList.add(courseDetail);
                                }

                            }

                            courses_rc.setAdapter(new CoursesHomeAdapter(firstcourseDetailArrayList,getActivity()));

                        }
  if (response.body().getData().getGallery()!=null)
                        {


                            if (response.body().getData().getGallery().size()<3)
                            {
                                galleryDataArrayList.addAll(response.body().getData().getGallery());

                            }
                            else {
                                for (int i=0;i<3;i++)
                                {
                                    Event courseDetail=new Event();
                                   courseDetail.setImageUrl(""+response.body().getData().getGallery().get(i).getImageUrl());

                    galleryDataArrayList.add(courseDetail);
                                }

                            }
                            Log.d("fsfgdss",college_id+"---"+response.body().getData().getGallery().get(0).getImageUrl());
                            rc_events.setAdapter(new CollegeGalleryAdapter(galleryDataArrayList,linearlayoutsize,getActivity()));

                        }

  if (response.body().getData().getFacility()!=null)
  {

      for (int i=0;i<response.body().getData().getFacility().size();i++)
      {
          FacilityData courseDetail=new FacilityData();
          courseDetail.setImageUrl(""+response.body().getData().getFacility().get(i).getImageUrl());
          courseDetail.setFacilityName(""+response.body().getData().getFacility().get(i).getFacilityName());
          courseDetail.setCollegeId(""+response.body().getData().getFacility().get(i).getCollegeId());
          Log.d("fsfgdss",college_id+"---"+response.body().getData().getFacility().get(0).getImageUrl());

          facilityDataArrayList.add(courseDetail);
      }
      rc_facility.setAdapter(new FacilityAdapter(facilityDataArrayList,getActivity()));

  }




                    }
                }
            }

            @Override
            public void onFailure(Call<CollegeHomeResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(getActivity(), "Server and Internet  "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
