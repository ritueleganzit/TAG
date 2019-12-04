package com.eleganzit.tag.ui.fragment;


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
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.SelectCourseActivity;
import com.eleganzit.tag.SelectSpecializationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecializationFragment extends Fragment {
    WebView web;


    public SpecializationFragment() {
        // Required empty public constructor
    }
    TextView txt_content;
String strtext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View  v=inflater.inflate(R.layout.fragment_specialization, container, false);
        web=v.findViewById(R.id.webview);


        //txt_content=v.findViewById(R.id.txt_content);
        if (getArguments()!=null)
        {
            strtext = getArguments().getString("edttext");

        }
        Log.d("hfskdjhfs",""+strtext);

        if (strtext!=null && !(strtext.isEmpty()))
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                web.setWebChromeClient(new WebChromeClient());

                WebSettings webSettings = web.getSettings();
                webSettings.setJavaScriptEnabled(true);
                web.loadDataWithBaseURL("", strtext , "text/html",  "UTF-8", "");

            } else {
                web.setWebChromeClient(new WebChromeClient());

                WebSettings webSettings = web.getSettings();
                webSettings.setJavaScriptEnabled(true);
                web.loadDataWithBaseURL("", strtext , "text/html",  "UTF-8", "");

            }

        }


        return v;
    }

}
