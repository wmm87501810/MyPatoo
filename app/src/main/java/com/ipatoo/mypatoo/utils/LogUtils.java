/*
 * Copyright 2014 LianXi. All rights reserved.
 * LianXi PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 * @LogUtil.java - 2014年4月14日
 */

package com.ipatoo.mypatoo.utils;

import android.text.TextUtils;
import android.util.Log;

import com.ipatoo.mypatoo.config.AppConfig;


/**
 * LogUtil
 *
 * @author OCEAN
 */
public class LogUtils {

    private static boolean IS_DEBUG = (AppConfig.APP_MODE != AppConfig.APPSTORE_MODE);

    public static void d(String tag, String msg) {
        if (IS_DEBUG) {
            Log.d(tag, cleanStr(msg));
        }
    }

    public static void d(Class<?> className, String msg) {
        if (IS_DEBUG) {
            Log.d(className.getSimpleName(), cleanStr(msg));
        }
    }

    public static void i(String tag, String msg) {
        if (IS_DEBUG) {
            Log.i(tag, cleanStr(msg));
        }
    }

    public static void i(Class<?> className, String msg) {
        if (IS_DEBUG) {
            Log.i(className.getSimpleName(), cleanStr(msg));
        }
    }

    public static void w(String tag, String msg) {
        if (IS_DEBUG) {
            Log.w(tag, cleanStr(msg));
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (IS_DEBUG) {
            Log.w(tag, cleanStr(msg), tr);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_DEBUG) {
            Log.e(tag, cleanStr(msg));
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (IS_DEBUG) {
            Log.e(tag, cleanStr(msg), tr);
        }
    }

    public static void w(Class<?> className, String msg) {
        if (IS_DEBUG) {
            Log.w(className.getSimpleName(), cleanStr(msg));
        }
    }

    public static void e(Class<?> className, String msg) {
        if (IS_DEBUG) {
            Log.e(className.getSimpleName(), cleanStr(msg));
        }
    }

    public static void e(Class<?> className, String msg, Throwable tr) {
        if (IS_DEBUG) {
            Log.e(className.getSimpleName(), cleanStr(msg), tr);
        }
    }

    public static void v(String logTag, String msg) {
        if (IS_DEBUG) {
            Log.v(logTag, msg);
        }
    }

    private static String cleanStr(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return "";
        }
        if (msg.length() > 3000) {
            return msg.substring(0, 3000);
        }
        return msg;
    }

}