package com.eleganzit.tag.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.eleganzit.tag.R;
import com.eleganzit.tag.utils.HomeFirstSliderAdapter;
import com.eleganzit.tag.utils.HomeSecondSliderAdapter;
import com.eleganzit.tag.utils.HomeSliderAdapter;
import com.eleganzit.tag.utils.HomeThirdSliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    ViewPager viewPager;
    TabLayout indicator;
    RecyclerView firstrc,secondrc,thirdrc,fourthrc;
    LinearLayout linearLayout,lin2,lin3,lin4,lin5,lin6;

    List<Integer> color;
    ArrayList colorName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        linearLayout=root.findViewById(R.id.lin1);
        lin2=root.findViewById(R.id.lin2);
        lin3=root.findViewById(R.id.lin3);
        lin4=root.findViewById(R.id.lin4);
        lin6=root.findViewById(R.id.lin6);
        lin5=root.findViewById(R.id.lin5);
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
        //colorName.add("https://images.unsplash.com/photo-1523050854058-8df90110c9f1?ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int sheight = displayMetrics.heightPixels;
        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.height= (int) (sheight/2.5);

        RelativeLayout.LayoutParams layoutParams2= (RelativeLayout.LayoutParams) lin2.getLayoutParams();
        layoutParams2.height= (int) (sheight/7.5);

        RelativeLayout.LayoutParams layoutParams3= (RelativeLayout.LayoutParams) lin3.getLayoutParams();
        layoutParams3.height= (int) (sheight/6.5);


        RelativeLayout.LayoutParams layoutParams4= (RelativeLayout.LayoutParams) lin4.getLayoutParams();
        layoutParams4.height= (int) (sheight/3.5);



        RelativeLayout.LayoutParams layoutParams5= (RelativeLayout.LayoutParams) lin5.getLayoutParams();
        layoutParams5.height= (int) (sheight/8.5);



        RelativeLayout.LayoutParams layoutParams6= (RelativeLayout.LayoutParams) lin6.getLayoutParams();
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