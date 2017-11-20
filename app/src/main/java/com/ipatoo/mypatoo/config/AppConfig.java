package com.ipatoo.mypatoo.config;

/**
 * Created by KevenTao on 2017/11/20.
 */

public class AppConfig {
    //内测环境 debug
    public static final int DEV_MODE = 1000;
    //上线的市场环境
    public static final int APPSTORE_MODE = 3000;

    public static final int APP_MODE = DEV_MODE;

    public static final boolean IS_DEBUG = (APP_MODE != APPSTORE_MODE);
}
