package com.ipatoo.mypatoo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ipatoo.mypatoo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KevenTao on 2017/11/25.
 * <p>
 * 引导页
 */

public class GuideActy extends AppCompatActivity {

    private ViewPager viewpager;
    private LinearLayout indicator;

    private List<View> viewList;
    private ImageView[] indicatorImgs;
    private static final int GUIDE_PAGE_COUNT = 3;
    private int[] imgResArr = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (LinearLayout) findViewById(R.id.indicator);
        initData();
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化页面数据
     */
    private void initData() {
        indicatorImgs = new ImageView[GUIDE_PAGE_COUNT];
        viewList = new ArrayList<View>(GUIDE_PAGE_COUNT);
        for (int i = 0; i < GUIDE_PAGE_COUNT; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.activity_guide_item, null);
            view.setBackgroundResource(R.color.white);
            ((ImageView) view.findViewById(R.id.guide_image)).setBackgroundResource(imgResArr[i]);
            viewList.add(view);
            indicatorImgs[i] = new ImageView(this);
            if (i == 0) {
                indicatorImgs[i].setBackgroundResource(R.mipmap.circular_yellow);
            } else {
                indicatorImgs[i].setBackgroundResource(R.mipmap.circular_black);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(20, 0, 0, 0);
                indicatorImgs[i].setLayoutParams(layoutParams);
            }
            indicator.addView(indicatorImgs[i]);
        }
    }

    /**
     * 滑动到指定页面后，更改点的背景
     *
     * @param targetIndex
     */
    public void setIndicator(int targetIndex) {
        for (int i = 0; i < indicatorImgs.length; i++) {
            indicatorImgs[i].setBackgroundResource(R.mipmap.circular_yellow);
            if (targetIndex != i) {
                indicatorImgs[i].setBackgroundResource(R.mipmap.circular_black);
            }
        }
    }

}
