package com.ipatoo.mypatoo.net;

import android.util.Pair;
import android.widget.ImageView;


import com.ipatoo.mypatoo.net.ok_http.callback.ResultCallback;
import com.ipatoo.mypatoo.net.ok_http.request.OkHttpRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KevenTao on 2017/11/6.
 */

public class HttpUtils {
    /*
    *------------------okhttp请求--------------------
    */

    //get请求
    public static void doOkHttpGetRequest(String urlConstant, Map<String, String> params,
                                          Map<String, String> headers, String tag,
                                          ResultCallback resultCallback) {
        new OkHttpRequest.Builder().url(urlConstant).params(params).headers(headers).tag(tag).get(resultCallback);
    }

    public static void doOkHttpGetRequest(String urlConstant, Map<String, String> params,
                                          Map<String, String> headers,
                                          ResultCallback resultCallback) {
        doOkHttpGetRequest(urlConstant, params, headers, null, resultCallback);
    }

    public static void doOkHttpGetRequest(String urlConstant, Map<String, String> params,
                                          ResultCallback resultCallback) {
        doOkHttpGetRequest(urlConstant, params, getBaseAuthMap(), null, resultCallback);
    }

    public static void doOkHttpGetRequest(String urlConstant, ResultCallback resultCallback) {
        Map<String, String> params = new HashMap<>();
        doOkHttpGetRequest(urlConstant, params, getBaseAuthMap(), null, resultCallback);
    }

    //post请求
    public static void doOkHttpPostRequest(String urlConstant, Map<String, String> params,
                                           Map<String, String> headers, String tag, String json,
                                           ResultCallback resultCallback) {
        new OkHttpRequest.Builder().url(urlConstant).params(params).headers(headers).tag(tag).json(json).post(resultCallback);
    }

    public static void doOkHttpPostRequest(String urlConstant, Map<String, String> params,
                                           ResultCallback resultCallback) {
        doOkHttpPostRequest(urlConstant, params, getBaseAuthMap(), null, null, resultCallback);
    }

    public static void doOkHttpPostRequest(String urlConstant, String json,
                                           ResultCallback resultCallback) {
        doOkHttpPostRequest(urlConstant, null, getBaseAuthMap(), null, json, resultCallback);
    }

    //upload
    public static void doOkHttpUploadRequest(String urlConstant, Map<String, String> params,
                                             Map<String, String> headers, String tag, Pair<String, File> files,
                                             ResultCallback resultCallback) {
        new OkHttpRequest.Builder().url(urlConstant).params(params).headers(headers).tag(tag).files(files).upload(resultCallback);
    }

    public static void doOkHttpUploadRequest(String urlConstant, Map<String, String> params,
                                             Pair<String, File> files,
                                             ResultCallback resultCallback) {
        doOkHttpUploadRequest(urlConstant, params, getBaseAuthMap(), null, files, resultCallback);
    }

    //传多个文件
    public static void doOkHttpUploadRequest(String urlConstant, Map<String, String> params, List<Pair<String, File>> filesList,
                                             ResultCallback resultCallback) {
        new OkHttpRequest.Builder().url(urlConstant).params(params).files(filesList).uploadList(resultCallback);
    }

    //download
    public static void doOkHttpDownloadRequest(String urlConstant, String destFileDir, String destFileName,
                                               ResultCallback resultCallback) {
        doOkHttpDownloadRequest(urlConstant, null, null, null, destFileDir, destFileName, resultCallback);

    }

    public static void doOkHttpDownloadRequest(String urlConstant, Map<String, String> params, Map<String, String> headers, String tag, String destFileDir, String destFileName,
                                               ResultCallback resultCallback) {

        new OkHttpRequest.Builder().url(urlConstant).params(params).headers(headers).tag(tag).destFileDir(destFileDir).destFileName(destFileName).download(resultCallback);

    }

    //displayImage

    public static void doOkHttpShowImage(String urlConstant, Map<String, String> params, Map<String, String> headers, String tag, ImageView imageview, int errorResId,
                                         ResultCallback resultCallback) {
        new OkHttpRequest.Builder().url(urlConstant).params(params).headers(headers).tag(tag).imageView(imageview).errResId(errorResId).displayImage(resultCallback);
    }

    public static void doOkHttpShowImage(String urlConstant, ImageView imageview,
                                         ResultCallback resultCallback) {
        new OkHttpRequest.Builder().url(urlConstant).imageView(imageview).displayImage(resultCallback);
    }


    public static Map<String, String> getBaseAuthMap() {
        return getBaseAuthMap("");
    }

    public static Map<String, String> getBaseAuthMap(String token) {
        Map<String, String> authMap = new HashMap<>();
//        authMap.put("token", token);
        return authMap;
    }

    //同步方法
    public static <T> T doOkHttpGetRequest(String urlConstant, String json, Class<T> clazz) throws IOException {
        return new OkHttpRequest.Builder().url(urlConstant).json(json).headers(getBaseAuthMap()).get(clazz);
    }
}
