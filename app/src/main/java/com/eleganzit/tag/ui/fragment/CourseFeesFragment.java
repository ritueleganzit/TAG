package com.eleganzit.tag.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.eleganzit.tag.HelpFAQActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeGalleryAdapter;
import com.eleganzit.tag.adapter.CoursesAdapter;
import com.eleganzit.tag.adapter.CoursesHomeAdapter;
import com.eleganzit.tag.adapter.FacilityAdapter;
import com.eleganzit.tag.adapter.HelpAdapter;
import com.eleganzit.tag.adapter.HomeCoursesAdapter;
import com.eleganzit.tag.adapter.HomeFacilityAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CollegeHomeResponse;
import com.eleganzit.tag.model.CourceFee;
import com.eleganzit.tag.model.FacilityData;
import com.eleganzit.tag.model.homecourse.CourceFeeDatum;
import com.eleganzit.tag.model.homecourse.CourseResponse;
import com.eleganzit.tag.model.homefacility.FacilitiesResponse;
import com.eleganzit.tag.model.homegallery.Event;
import com.eleganzit.tag.model.newhome.CourceDetail;
import com.eleganzit.tag.model.newhome.CourseResult;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFeesFragment extends Fragment {


    RecyclerView rc_course_fees;
    int college_id;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    ArrayList<CourceDetail> courceFeeData;
    public CourseFeesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_course_fees, container, false);
        rc_course_fees=v.findViewById(R.id.rc_course_fees);
        userLoggedInSession=new UserLoggedInSession(getActivity());
        Bundle b = getArguments();
        college_id=b.getInt("college_id",0);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        getCollegeData();

        return v;

    }

    private void getFacilityData() {
        progressDialog.show();
        courceFeeData=new ArrayList<>();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<CourseResponse> call=myInterface.coursefees("coursefees",""+college_id);
        call.enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
Log.d("dfsfsf",""+response.body().getCourceFeeData().size());
                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        if (response.body().getCourceFeeData() != null) {
                          ///  courceFeeData.addAll(response.body().getCourceFeeData());

                         //   rc_course_fees.setAdapter(new HomeCoursesAdapter(courceFeeData,getActivity()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CourseResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getCollegeData(){
        progressDialog.show();
        courceFeeData=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        // Toast.makeText(getActivity(), "asdsa"+college_id, Toast.LENGTH_SHORT).show();

        final Call<CollegeHomeResponse> getCollegeByIdCall=myInterface.getCollegeById("185");
        getCollegeByIdCall.enqueue(new Callback<CollegeHomeResponse>() {
            @Override
            public void onResponse(Call<CollegeHomeResponse> call, Response<CollegeHomeResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "asdsa"+college_id, Toast.LENGTH_SHORT).show();

                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {

                        //setCourse

                        if (response.body().getData().getCource_detail()!=null)
                        {
                            Log.d("hmiu n", "7" + response.body().getData().getCource_detail().size());


                                if (response.body().getData().getCource_detail().size()<3)
                            {

                                for (int i=0;i<response.body().getData().getCource_detail().size();i++) {
                                    courceFeeData.addAll(response.body().getData().getCource_detail());
                                }



                            }

                            else {
                                for (int i=0;i<3;i++)
                                {
                                    CourceDetail courseDetail=new CourceDetail();
                                    courseDetail.setCourseResult(response.body().getData().getCource_detail().get(i).getCourseResult());

                                    courceFeeData.add(courseDetail);
                                }

                               // courceFeeData.addAll(response.body().getData().getCource_and_fee());


                            }

                            rc_course_fees.setAdapter(new HomeCoursesAdapter(response.body().getData().getCource_detail(),getActivity()));

                            //   courses_rc.setAdapter(new CoursesHomeAdapter(firstcourseDetailArrayList,getActivity()));

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


}
