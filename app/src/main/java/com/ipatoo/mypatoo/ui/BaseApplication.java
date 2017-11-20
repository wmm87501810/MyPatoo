package com.ipatoo.mypatoo.ui;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.ipatoo.mypatoo.config.AppConfig;
import com.ipatoo.mypatoo.net.ok_http.OkHttpClientManager;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by dell on 2017/11/14.
 */

public class BaseApplication extends Application {
    private BaseApplication instance;

    public OkHttpClient okHttpclient;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //开启facebook网络调试工具
        if (AppConfig.IS_DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        //初始化okhttoClient
        okHttpclient = OkHttpClientManager.getInstance().getOkHttpClient();
    }

    public BaseApplication getInstance() {
        if (instance == null){
            instance = new BaseApplication();
        }
        return instance;
    }


}
