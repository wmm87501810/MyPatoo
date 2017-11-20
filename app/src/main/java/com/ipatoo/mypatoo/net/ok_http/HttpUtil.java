package com.ipatoo.mypatoo.net.ok_http;



import com.ipatoo.mypatoo.net.ok_http.callback.ResultCallback;
import com.ipatoo.mypatoo.net.ok_http.request.OkHttpRequest;

import java.util.Map;

/**
 * Created by KevenTao on 2017/11/6.
 */
public class HttpUtil {

    //download
    public static void doOkHttpDownloadRequest(String urlConstant, String destFileDir, String destFileName,
                                               ResultCallback resultCallback) {
        doOkHttpDownloadRequest(urlConstant, null, null, null, destFileDir, destFileName, resultCallback);

    }

    public static void doOkHttpDownloadRequest(String urlConstant, Map<String, String> params, Map<String, String> headers, String tag, String destFileDir, String destFileName,
                                               ResultCallback resultCallback) {
        new OkHttpRequest.Builder().url(urlConstant).params(params).headers(headers).tag(tag).destFileDir(destFileDir).destFileName(destFileName).download(resultCallback);

    }

}
