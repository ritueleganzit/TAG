package com.eleganzit.tag.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.bean.Image;

public class TabbedDialog extends DialogFragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Integer> color;
    List<String> colorName;
    TextView txt_click;
    ImageView close;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.dialog_sample,container,false);
        color = new ArrayList<>();

        txt_click=rootview.findViewById(R.id.txt_click);
        viewPager=rootview.findViewById(R.id.viewPager);
        close=rootview.findViewById(R.id.close);
        tabLayout=rootview.findViewById(R.id.indicator);
        color.add(Color.parseColor("#008e85"));
        color.add(Color.parseColor("#1CABA0"));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        colorName = new ArrayList<>();
        colorName.add("https://ed.stanford.edu/sites/default/files/news_images/lisegagne.jpg");
        colorName.add("https://images.unsplash.com/photo-1523050854058-8df90110c9f1?ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80");


       // viewPager.setAdapter(new SliderAdapter(getActivity(), color, colorName));
        viewPager.setAdapter(new HomePopupSliderAdapter(getActivity(), color, colorName));

        tabLayout.setupWithViewPager(viewPager);color = new ArrayList<>();
        txt_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        }); close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return rootview;
    }
}