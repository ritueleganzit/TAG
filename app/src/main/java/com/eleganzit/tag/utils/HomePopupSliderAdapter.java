package com.eleganzit.tag.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.eleganzit.tag.R;

import java.util.List;

public class HomePopupSliderAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> color;
    private List<String> colorName;


    public HomePopupSliderAdapter(Context context, List<Integer> color, List colorName) {
        this.context = context;
        this.color = color;
        this.colorName = colorName;
    }

    @Override
    public int getCount() {
        return color.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home_popup_slider, null);

        ImageView textView = (ImageView) view.findViewById(R.id.textView);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);

        //RequestOptions requestOptions = new RequestOptions();
        //requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide
                .with(context)
                .load(R.drawable.school)
              //  .apply(requestOptions)
                .into(textView);
        //textView.setText(colorName.get(position));
        linearLayout.setBackgroundColor(color.get(position));

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

}
