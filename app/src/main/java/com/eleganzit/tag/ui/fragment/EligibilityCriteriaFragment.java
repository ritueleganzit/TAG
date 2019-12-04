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
public class EligibilityCriteriaFragment extends Fragment {
    WebView web;
    String strtext;
    public EligibilityCriteriaFragment() {
        // Required empty public constructor
    }
    TextView txt_content;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_eligibility_criteria, container, false);
        web=v.findViewById(R.id.webview);


        txt_content=v.findViewById(R.id.txt_content);
        if (getArguments()!=null)
        {
             strtext = getArguments().getString("edttext");

        }


        Log.d("asda",""+strtext);
        if (strtext!=null && !(strtext.isEmpty()))
        {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                web.setWebChromeClient(new WebChromeClient());

                WebSettings webSettings = web.getSettings();
                webSettings.setJavaScriptEnabled(true);
                web.loadDataWithBaseURL("", strtext , "text/html",  "UTF-8", "");

                //txt_content.setText(Html.fromHtml(strtext, Html.FROM_HTML_MODE_LEGACY));
            } else {
                web.setWebChromeClient(new WebChromeClient());

                WebSettings webSettings = web.getSettings();
                webSettings.setJavaScriptEnabled(true);
                web.loadDataWithBaseURL("", strtext , "text/html",  "UTF-8", "");

                //txt_content.setText(Html.fromHtml(strtext));
            }

        }

        return v;
    }

}
