package com.ipatoo.mypatoo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.ipatoo.mypatoo.R;

public class WelcomeActivity extends AppCompatActivity {

    private final long SPLASH_LENGTH = 3000;
    Handler mHandler = new Handler();

    private ImageView back_iv;
    private ViewPager mViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

        //使用handler的postDelayed实现延时跳转
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent;
//                if (sp.getBoolen("first_login",true)){//第一次sharepreference    true
//                    intent = new Intent(WelcomeActivity.this, GuideActy.class);
//                    sp.putBoolen("first_login",false);
//                    sp.commit;
//                } else {
//                    intent = new Intent(WelcomeActivity.this, SignActivity.class);
//                }
                Intent intent = new Intent(WelcomeActivity.this, GuideActy.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_LENGTH);//2秒后跳转到应用主界面MainActivity
    }

    /**
     * 初始化动画
     *
     * @param imageView
     */
    private void initAnimal(ImageView imageView) {
        // 展示一个透明动画，2.5秒，动画结束之后，自动登录
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setDuration(2500);
        imageView.setAnimation(alphaAnimation);
        imageView.startAnimation(alphaAnimation);//开始动画
    }

    private void initView() {
        back_iv = (ImageView) findViewById(R.id.back_iv);
        mViewPage = (ViewPager) findViewById(R.id.viewpager);

        initAnimal(back_iv);
    }
}
