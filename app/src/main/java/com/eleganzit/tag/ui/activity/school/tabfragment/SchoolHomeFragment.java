package com.eleganzit.tag.ui.activity.school.tabfragment;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
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
import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeGalleryAdapter;
import com.eleganzit.tag.adapter.CoursesHomeAdapter;
import com.eleganzit.tag.adapter.CoursesSchoolHomeAdapter;
import com.eleganzit.tag.adapter.FacilityAdapter;
import com.eleganzit.tag.adapter.SchoolFacilityAdapter;
import com.eleganzit.tag.adapter.SchoolGalleryAdapter;
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
import com.eleganzit.tag.model.schoolhome.ClassResult;
import com.eleganzit.tag.model.schoolhome.Facilities;
import com.eleganzit.tag.model.schoolhome.Gallery;
import com.eleganzit.tag.model.schoolhome.SchoolAppHomeResponse;
import com.eleganzit.tag.ui.activity.AddBasicInformationActivity;
import com.eleganzit.tag.ui.activity.CollegeDetailActivity;
import com.eleganzit.tag.ui.activity.school.ApplySchoolBasicInfoActivity;
import com.eleganzit.tag.ui.activity.school.SchoolDetailActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolHomeFragment extends Fragment {

    TextView address,applynow, collegename,website, collagelocation, accreditation, approved_by, course_duration, study_mode, placement, ownership, university_name, rank, viewAll, hideAll, viewallgallery, email, board_name,board_category,brouchure;
    LinearLayout linearlayoutsize;
String brouchuretxt;
    public SchoolHomeFragment() {
        // Required empty public constructor
    }

    RatingBar myRatingBar;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    RecyclerView rc_events, courses_rc, rc_facility;
    ArrayList<ClassResult> courseDetailArrayList;
    ArrayList<ClassResult> firstcourseDetailArrayList;
    ArrayList<Gallery> galleryDataArrayList;
    ArrayList<Facilities> facilityDataArrayList;


    ImageView imgbg;
    String college_id;
    ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_school_home, container, false);
        brouchure = v.findViewById(R.id.brouchure);
        applynow = v.findViewById(R.id.applynow);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("A message");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);

        board_category = v.findViewById(R.id.board_category);
        myRatingBar = v.findViewById(R.id.myRatingBar);
        website = v.findViewById(R.id.website);
        email = v.findViewById(R.id.email);
        course_duration = v.findViewById(R.id.course_duration);
        address = v.findViewById(R.id.address);
        collagelocation = v.findViewById(R.id.collegeaddress);
        board_name = v.findViewById(R.id.board_name);
        viewallgallery = v.findViewById(R.id.viewallgallery);
        approved_by = v.findViewById(R.id.approved_by);
        accreditation = v.findViewById(R.id.accreditation);
        placement = v.findViewById(R.id.placement);
        collegename = v.findViewById(R.id.collegename);
        rc_events = v.findViewById(R.id.rc_events);
        viewAll = v.findViewById(R.id.viewAll);
        courses_rc = v.findViewById(R.id.courses_rc);
        university_name = v.findViewById(R.id.university_name);
        rc_facility = v.findViewById(R.id.rc_facility);
        rank = v.findViewById(R.id.rank);
        ownership = v.findViewById(R.id.ownership);
        study_mode = v.findViewById(R.id.study_mode);
        imgbg = v.findViewById(R.id.imgbg);
        userLoggedInSession = new UserLoggedInSession(getActivity());
        Bundle b = getArguments();
        college_id = b.getString("college_id");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        linearlayoutsize = v.findViewById(R.id.linearlayoutsize);
        brouchure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();


            }
        });
        // rc_facility.setAdapter(new FacilityAdapter(getActivity()));
        imgbg.getLayoutParams().width = (int) (getScreenWidthInPXs(getContext(), getActivity()));
        imgbg.getLayoutParams().height = (int) (getScreenWidthInPXs(getContext(), getActivity()) / 3.5);
        applynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ApplySchoolBasicInfoActivity.class));

            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SchoolDetailActivity.htab_viewpager.setCurrentItem(1);

            }
        });
        viewallgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SchoolDetailActivity.htab_viewpager.setCurrentItem(2);

            }
        });

        return v;
    }
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }
    @Override
    public void onResume() {
        super.onResume();
        getCollegeData();
    }

    public void getCollegeData() {
        facilityDataArrayList = new ArrayList<>();
        courseDetailArrayList = new ArrayList<>();
        firstcourseDetailArrayList = new ArrayList<>();
        galleryDataArrayList = new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
      //  Toast.makeText(getActivity(), "asdsa" + college_id, Toast.LENGTH_SHORT).show();

        final Call<SchoolAppHomeResponse> getCollegeByIdCall = myInterface.getSchoolByid("" + college_id);
        getCollegeByIdCall.enqueue(new Callback<SchoolAppHomeResponse>() {
            @Override
            public void onResponse(Call<SchoolAppHomeResponse> call, Response<SchoolAppHomeResponse> response) {
                progressDialog.dismiss();
                //Toast.makeText(getActivity(), "asdsa"+college_id, Toast.LENGTH_SHORT).show();
                Log.d("fsfs", "iii" + response.toString());

                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        // mobilenum.setText(response.body().getData().getCollegeInfo().get(0).getPhone());
                        email.setText(response.body().getData().getSchoolInfo().get(0).getEmail());
                        website.setText(response.body().getData().getSchoolInfo().get(0).getWeb());

                        RequestOptions options = new RequestOptions()
                                .centerCrop()
                                .placeholder(R.drawable.schools_tri)
                                .error(R.drawable.schools_tri)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .priority(Priority.HIGH);

                        Glide.with(getActivity()).load(response.body().getData().getSchoolInfo().get(0).getSchoolImage())
                                .apply(options)
                                .into(imgbg);

                        if (response.body().getData().getSchoolInfo().get(0).getSchoolName() != null && !(response.body().getData().getSchoolInfo().get(0).getSchoolName().isEmpty())) {
                            collegename.setText(capitalize("" + response.body().getData().getSchoolInfo().get(0).getSchoolName()));
                        }

                        if (response.body().getData().getSchoolInfo().get(0).getBrochureUrl() != null && !(response.body().getData().getSchoolInfo().get(0).getBrochureUrl().isEmpty())) {
                            brouchuretxt=response.body().getData().getSchoolInfo().get(0).getBrochureUrl();
                        }

                        if (response.body().getData().getSchoolInfo().get(0).getSchoolCity() != null && !(response.body().getData().getSchoolInfo().get(0).getSchoolCity().isEmpty())) {
                            collagelocation.setText("" + response.body().getData().getSchoolInfo().get(0).getSchoolCity() + ",");
                        } else {
                            collagelocation.setText("");
                        }
                        if (response.body().getData().getSchoolInfo().get(0).getSchoolLocation() != null && !(response.body().getData().getSchoolInfo().get(0).getSchoolLocation().isEmpty())) {
                            collagelocation.append(" " + response.body().getData().getSchoolInfo().get(0).getSchoolLocation() + "");
                        } if (response.body().getData().getSchoolInfo().get(0).getSchoolLocation() != null && !(response.body().getData().getSchoolInfo().get(0).getSchoolLocation().isEmpty())) {
                            address
                                    .setText(" " + response.body().getData().getSchoolInfo().get(0).getSchoolLocation() + "");
                        }
                        if (response.body().getData().getSchoolInfo().get(0).getAccreditation() != null && !(response.body().getData().getSchoolInfo().get(0).getAccreditation().isEmpty())) {
                            accreditation.setVisibility(View.VISIBLE);

                            accreditation.setText("Accredited by: " + response.body().getData().getSchoolInfo().get(0).getAccreditation() + "");
                        } else {

                            accreditation.setVisibility(View.GONE);
                        }

                        if (response.body().getData().getSchoolInfo().get(0).getApprovedBy() != null && !(response.body().getData().getSchoolInfo().get(0).getApprovedBy().isEmpty())) {
                            approved_by.setVisibility(View.VISIBLE);
                            approved_by.setText("Approved by: " + response.body().getData().getSchoolInfo().get(0).getApprovedBy() + "");
                        } else {
                            approved_by.setVisibility(View.GONE);
                        }
 if (response.body().getData().getSchoolInfo().get(0).getCategory() != null && !(response.body().getData().getSchoolInfo().get(0).getCategory().isEmpty())) {
                            board_category.setText(capitalize("" + response.body().getData().getSchoolInfo().get(0).getCategory() + ""));
                        } else {
     board_category.setText("");
                        }

                        //Toast.makeText(getActivity(), ""+response.body().getData().getSchoolInfo().get(0).getBoardName(), Toast.LENGTH_SHORT).show();
                        if (response.body().getData().getSchoolInfo().get(0).getBoardName() != null && !(response.body().getData().getSchoolInfo().get(0).getBoardName().isEmpty())) {
                            board_name.setVisibility(View.VISIBLE);
                            board_name.setText(capitalize("" + response.body().getData().getSchoolInfo().get(0).getBoardName() + ""));
                        } else {
                            board_name.setVisibility(View.GONE);
                        }
                        if (response.body().getData().getSchoolInfo().get(0).getRank() != null) {
                            rank.setVisibility(View.VISIBLE);
                            rank.setText("Rank : " + response.body().getData().getSchoolInfo().get(0).getRank() + "");
                        } else {
                            rank.setVisibility(View.GONE);
                        }

                        if (response.body().getData().getClassDetail()!=null)
                        {
                            courseDetailArrayList.addAll(response.body().getData().getClassDetail().get(0).getClassResult());
                            Log.d("fsfs","ghjgj"+response.body().getData().getClassDetail().get(0).getClassResult());


                            if (response.body().getData().getClassDetail().size()<3)
                            {
                                firstcourseDetailArrayList.addAll(response.body().getData().getClassDetail().get(0).getClassResult());

                            }
                            else {
                                for (int i=0;i<3;i++)
                                {
                                    ClassResult courseDetail=new ClassResult();
                                    courseDetail.setClassName(""+response.body().getData().getClassDetail().get(i).getClassName());
                                    courseDetail.setBoard(""+response.body().getData().getClassDetail().get(i).getClassResult().get(i).getBoard());
                                    courseDetail.setBoardCategory(""+response.body().getData().getClassDetail().get(i).getClassResult().get(i).getBoardCategory());
                                    firstcourseDetailArrayList.add(courseDetail);
                                }

                            }

                            courses_rc.setAdapter(new CoursesSchoolHomeAdapter(firstcourseDetailArrayList,getActivity()));

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
                                    Gallery courseDetail=new Gallery();
                                    courseDetail.setImageUrl(""+response.body().getData().getGallery().get(i).getImageUrl());

                                    galleryDataArrayList.add(courseDetail);
                                }

                            }
                            rc_events.setAdapter(new SchoolGalleryAdapter(galleryDataArrayList,linearlayoutsize,getActivity()));

                        }

                        if (response.body().getData().getFacility()!=null)
                        {

                            for (int i=0;i<response.body().getData().getFacility().size();i++)
                            {
                                Facilities courseDetail=new Facilities();
                                courseDetail.setFacilityIcon(""+response.body().getData().getFacility().get(i).getFacilityIcon());
                                courseDetail.setFacilityName(""+response.body().getData().getFacility().get(i).getFacilityName());
                                courseDetail.setFacilityId(response.body().getData().getFacility().get(i).getFacilityId());

                                facilityDataArrayList.add(courseDetail);
                            }
                            rc_facility.setAdapter(new SchoolFacilityAdapter(facilityDataArrayList,getActivity()));

                        }

                        /*



                        if (response.body().getData().getSchoolInfo().get(0).getAdmission()!=null  && !(response.body().getData().getSchoolInfo().get(0).getAdmission().isEmpty()))
                        {
                            placement.setText("Placement : "+response.body().getData().getSchoolInfo().get(0).getAdmission()+"");
                        }
                        else
                        {
                            placement.setText("Placement : -");
                        }if (response.body().getData().getSchoolInfo().get(0).getRank()!=null  )
                    {
                        rank.setVisibility(View.VISIBLE);
                        rank.setText("Rank : "+response.body().getData().getSchoolInfo().get(0).getRank()+"");
                    }
                    else
                    {
                        rank.setVisibility(View.GONE);
                    }
                        if (response.body().getData().getSchoolInfo().get(0).getOwnership()!=null  && !(response.body().getData().getSchoolInfo().get(0).getOwnership().isEmpty()))
                        {
                            ownership.setText(""+response.body().getData().getSchoolInfo().get(0).getOwnership()+"");
                        }
                        else
                        {
                            ownership.setText("");
                        } if (response.body().getData().getSchoolInfo().get(0).getBoardName()!=null  && !(response.body().getData().getSchoolInfo().get(0).getBoardName().isEmpty()))
                    {
                        university_name.setText(""+response.body().getData().getSchoolInfo().get(0).getBoardName()+"");
                    }
                    else
                    {
                        university_name.setText("");
                    }


                       *//* if (response.body().getData().getSchoolInfo().get(0).getYears()!=null  && !(response.body().getData().getCollegeInfo().get(0).getYears().isEmpty()))
                        {
                            course_duration.setVisibility(View.VISIBLE);
                            course_duration.setText(""+response.body().getData().getSchoolInfo().get(0).getYears());


                        }
                        else
                        {
                            course_duration.setVisibility(View.GONE);
                        }*//**//*if (response.body().getData().getCollegeInfo().get(0).getStudyMode()!=null  && !(response.body().getData().getCollegeDetail().getStudyMode().isEmpty()))
                            {
                                study_mode.setVisibility(View.VISIBLE);
                                study_mode.setText(""+response.body().getData().getCollegeDetail().getStudyMode());


                            }
                            else
                            {
                                study_mode.setVisibility(View.GONE);
                            }*//*if (response.body().getData().getSchoolInfo().get(0).getRatings()!=null)
                    {
                        myRatingBar.setRating(Float.parseFloat(""+response.body().getData().getSchoolInfo().get(0).getRatings()));


                    }
                    else
                    {
                        myRatingBar.setRating(0);
                    }



                        //setCourse

                        if (response.body().getData().getClassDetail()!=null)
                        {
                            courseDetailArrayList.addAll(response.body().getData().getClassDetail().get(0).getClassResult());
                            Log.d("fsfs","ghjgj"+response.body().getData().getClassDetail().get(0).getClassResult());


                            if (response.body().getData().getClassDetail().size()<3)
                            {
                                firstcourseDetailArrayList.addAll(response.body().getData().getClassDetail().get(0).getClassResult());

                            }
                            else {
                                for (int i=0;i<3;i++)
                                {
                                    ClassResult courseDetail=new ClassResult();
                                    courseDetail.setClassName(""+response.body().getData().getClassDetail().get(i).getClassName());
                                    courseDetail.setBoard(""+response.body().getData().getClassDetail().get(i).getClassResult().get(i).getBoard());
                                    courseDetail.setBoardCategory(""+response.body().getData().getClassDetail().get(i).getClassResult().get(i).getBoardCategory());
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
                                courseDetail.setImageUrl(""+response.body().getData().getFacility().get(i).getFacilityIcon());
                                courseDetail.setFacilityName(""+response.body().getData().getFacility().get(i).getFacilityName());
                                courseDetail.setCollegeId(""+response.body().getData().getFacility().get(i).getFacilityId());

                                facilityDataArrayList.add(courseDetail);
                            }
                            rc_facility.setAdapter(new FacilityAdapter(facilityDataArrayList,getActivity()));

                        }
*/


                    }
                }
            }

            @Override
            public void onFailure(Call<SchoolAppHomeResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(getActivity(), "Server and Internet  " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }
        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null)
                Toast.makeText(context,"Download error: "+result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context,"File downloaded", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream("/sdcard/data.pdf");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }
    }
    public static int getScreenWidthInPXs(Context context, Activity activity) {

        DisplayMetrics dm = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
public void requestPermission()
{
    Dexter.withActivity(getActivity())
            .withPermissions(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                   )
            .withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        if (brouchuretxt!=null && !brouchuretxt.isEmpty())
                        {
                            final DownloadTask downloadTask = new DownloadTask(getActivity());
                            downloadTask.execute(brouchuretxt);

                            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    downloadTask.cancel(true); //cancel the task
                                }
                            });
                        }
                               }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied()) {
                        // show alert dialog navigating to Settings
                        showSettingsDialog();
                    }
                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }).
            withErrorListener(new PermissionRequestErrorListener() {
                @Override
                public void onError(DexterError error) {
                    Toast.makeText(getActivity(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                }
            })
            .onSameThread()
            .check();
}
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to download data. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


}
