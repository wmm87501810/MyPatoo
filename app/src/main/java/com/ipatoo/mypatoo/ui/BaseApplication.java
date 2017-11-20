package com.ipatoo.mypatoo.ui;

import android.app.Application;

/**
 * Created by dell on 2017/11/14.
 */

public class BaseApplication extends Application {
    private BaseApplication instance;

    public BaseApplication getInstance() {
        if (instance == null){
            instance = new BaseApplication();
        }
        return instance;
    }
}
