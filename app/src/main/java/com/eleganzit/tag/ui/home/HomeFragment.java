package com.eleganzit.tag.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.councelling.CouncellingExamActivity;
import com.eleganzit.tag.ui.activity.exam.ExamActivity;
import com.eleganzit.tag.ui.activity.result.ResultsActivity;
import com.eleganzit.tag.utils.HomeFirstSliderAdapter;
import com.eleganzit.tag.utils.HomeSecondSliderAdapter;
import com.eleganzit.tag.utils.HomeSliderAdapter;
import com.eleganzit.tag.utils.HomeThirdSliderAdapter;
import com.eleganzit.tag.utils.TabbedDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static android.content.Context.WINDOW_SERVICE;

public class HomeFragment extends Fragment {
    ViewPager viewPager;
    TabLayout indicator;
    RecyclerView firstrc,secondrc,thirdrc,fourthrc;
    LinearLayout linearLayout,lin2,lin3,lin4,lin5,lin6,linear_exam,linresult,linear_counselling;

    List<Integer> color;
    ArrayList colorName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        linearLayout=root.findViewById(R.id.lin1);
        linear_exam=root.findViewById(R.id.linear_exam);
        lin2=root.findViewById(R.id.lin2);
        lin3=root.findViewById(R.id.lin3);
        lin4=root.findViewById(R.id.lin4);
        lin6=root.findViewById(R.id.lin6);
        lin5=root.findViewById(R.id.lin5);
        linear_counselling=root.findViewById(R.id.linear_counselling);
        linresult=root.findViewById(R.id.linresult);
        firstrc=root.findViewById(R.id.firstrc);
        secondrc=root.findViewById(R.id.secondrc);
        fourthrc=root.findViewById(R.id.fourthrc);
        thirdrc=root.findViewById(R.id.thirdrc);
        viewPager=(ViewPager)root.findViewById(R.id.viewPager);
        indicator=(TabLayout)root.findViewById(R.id.indicator);
        color = new ArrayList<>();
        color.add(Color.parseColor("#F5F5F5"));
        color.add(Color.parseColor("#F5F5F5"));
        color.add(Color.parseColor("#F5F5F5"));

        colorName = new ArrayList<>();
        colorName.add(R.drawable.slidertwo);
        colorName.add(R.drawable.sliderone);
        colorName.add(R.drawable.slidertwo);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(firstrc);

        //colorName.add("https://images.unsplash.com/photo-1523050854058-8df90110c9f1?ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int sheight = getScreenWidthInPXs(getContext(),getActivity());
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.height= (int) (sheight/2.5);

        LinearLayout.LayoutParams layoutParams2= (LinearLayout.LayoutParams) lin2.getLayoutParams();
        layoutParams2.height= (int) (sheight/7.5);

        LinearLayout.LayoutParams layoutParams3= (LinearLayout.LayoutParams) lin3.getLayoutParams();
        layoutParams3.height= (int) (sheight/6.5);


        LinearLayout.LayoutParams layoutParams4= (LinearLayout.LayoutParams) lin4.getLayoutParams();
        layoutParams4.height= (int) (sheight/3.5);



        LinearLayout.LayoutParams layoutParams5= (LinearLayout.LayoutParams) lin5.getLayoutParams();
        layoutParams5.height= (int) (sheight/8.5);



        LinearLayout.LayoutParams layoutParams6= (LinearLayout.LayoutParams) lin6.getLayoutParams();
        layoutParams6.height= (int) (sheight/3.5);




        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        lp.height= (int) (layoutParams.height/1.3);
     /*   Log.d("heightttt",""+lp.height);
        Log.d("heightttt",""+sheight);
        Log.d("heightttt",""+layoutParams.height);*/
        viewPager.setLayoutParams(lp);
        viewPager.setAdapter(new HomeSliderAdapter(getActivity(), color, colorName));
        firstrc.setAdapter(new HomeFirstSliderAdapter(colorName,getActivity()));
        thirdrc.setAdapter(new HomeThirdSliderAdapter(colorName,getActivity()));
        secondrc.setAdapter(new HomeSecondSliderAdapter(layoutParams4.height,colorName,getActivity()));
        fourthrc.setAdapter(new HomeSecondSliderAdapter(layoutParams4.height, colorName,getActivity()));
        indicator.setupWithViewPager(viewPager, true);
        int remaining=layoutParams.height-lp.height;
        Log.d("heighttttr",""+remaining);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(!prefs.getBoolean("firstTime", false)) {
            // run your one time code here
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            // Create and show the dialog.
            TabbedDialog dialogFragment = new TabbedDialog();
            dialogFragment.show(ft,"dialog");
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
        linear_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ExamActivity.class));
            }
        });linresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ResultsActivity.class));
            }
        });linear_counselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CouncellingExamActivity.class));
            }
        });
        /*ViewGroup.LayoutParams params = indicator.getLayoutParams();
//Change the height in 'Pixels'
        params.height = remaining;*/

       // indicator.setLayoutParams(params);
        //int width = displayMetrics.widthPixels;

       /* ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.height=sheight/4;

//setting margins around imageimageview
        lp.height=sheight/2; //left, top, right, bottom

//adding attributes to the imageview
        viewPager.setLayoutParams(lp);
        viewPager.setAdapter(new HomeSliderAdapter(getActivity(), color, colorName));
        firstrc.setAdapter(new HomeFirstSliderAdapter(colorName,getActivity()));
        secondrc.setAdapter(new HomeSecondSliderAdapter(colorName,getActivity()));
        indicator.setupWithViewPager(viewPager, true);
*/
        //Timer timer = new Timer();
       // timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        return root;
    }
    public static int getScreenWidthInPXs(Context context, Activity activity){

        DisplayMetrics dm = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.heightPixels;
        return width;
    }

    public class CustomAdapter extends FragmentPagerAdapter {
        List<Fragment> mFragmentCollection = new ArrayList<>();
        List<String> mTitleCollection = new ArrayList<>();
        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }
        public void addFragment(String title, Fragment fragment)
        {
            mTitleCollection.add(title);
            mFragmentCollection.add(fragment);
        }
        //Needed for
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleCollection.get(position);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentCollection.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentCollection.size();
        }
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < color.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}