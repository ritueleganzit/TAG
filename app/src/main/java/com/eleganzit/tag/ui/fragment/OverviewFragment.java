package com.eleganzit.tag.ui.fragment;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.SelectCourseActivity;
import com.eleganzit.tag.SelectSpecializationActivity;
import com.eleganzit.tag.SelectedCourseActivity;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CoursesData;
import com.eleganzit.tag.model.GetCoursesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {


    SelectCourseActivity mActivity;
    WebView web;
    public OverviewFragment() {
        // Required empty public constructor
    }

    ProgressDialog progressDialog;
    TextView txt_content;
    String strtext;
    private View mCustomView;
    String overviewFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_overview, container, false);

        txt_content=v.findViewById(R.id.txt_content);
        web=v.findViewById(R.id.webview);

       // overviewFragment=getArguments().getString("special");
        if (getArguments()!=null)
        {
             strtext = getArguments().getString("edttext");

        }
        else
        {
            Toast.makeText(getActivity(), "Nothing to show", Toast.LENGTH_SHORT).show();
        }
        Log.d("hfskdjhfs",""+strtext);


        if (strtext!=null && !(strtext.isEmpty()))
        {
            //txt_content.setText(SelectSpecializationActivity.getSpecialization.getOverview());



/*

            web.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
*/
            web.getSettings().setBuiltInZoomControls(true);
            web.setWebViewClient(new WebViewClient());
            web.setWebChromeClient(new WebChromeClient());
            web.getSettings().setUseWideViewPort(true);
            web.getSettings().setLoadWithOverviewMode(true);
            WebSettings webSettings = web.getSettings();
            Resources res = getResources();
            float fontSize = res.getDimension(R.dimen.txtSize);
            webSettings.setDefaultFontSize((int)fontSize);
            webSettings.setJavaScriptEnabled(true);
            web.loadDataWithBaseURL("", strtext , "text/html",  "UTF-8", "");


        } else {



            web.getSettings().setBuiltInZoomControls(true);
            web.setWebViewClient(new WebViewClient());
            web.setWebChromeClient(new WebChromeClient());
            web.getSettings().setUseWideViewPort(true);
            web.getSettings().setLoadWithOverviewMode(true);
            WebSettings webSettings = web.getSettings();
            Resources res = getResources();
            float fontSize = res.getDimension(R.dimen.txtSize);
            webSettings.setDefaultFontSize((int)fontSize);

            webSettings.setJavaScriptEnabled(true);
            web.loadDataWithBaseURL("", strtext , "text/html",  "UTF-8", "");


            // txt_content.setText(Html.fromHtml(strtext));




        }

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return v;
    }


    public void getCourse(int id){

        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<GetCoursesResponse> call=myInterface.getCoursesbyId(id);
        call.enqueue(new Callback<GetCoursesResponse>() {
            @Override
            public void onResponse(Call<GetCoursesResponse> call, Response<GetCoursesResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        for(int i=0;i<response.body().getData().size();i++){

                            CoursesData coursesData=new CoursesData(response.body().getData().get(i).getCourceId(),response.body().getData().get(i).getCourceName(),response.body().getData().get(i).getOverview(),response.body().getData().get(i).getSpecialization(),response.body().getData().get(i).getEligibility());

                        }

                    }
                    else
                    {

                        Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(getActivity(), ""+response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GetCoursesResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(getActivity(), "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

/*
    @Override
    public void onDataReceived(CoursesData coursesData) {
        Toast.makeText(mActivity, ">> "+coursesData.getCourse_overview(), Toast.LENGTH_SHORT).show();

        getCourse(coursesData.getId());

    }*/




}
