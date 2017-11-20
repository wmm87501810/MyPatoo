package com.ipatoo.mypatoo.net.ok_http.request;

import android.text.TextUtils;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

/**
 * Created by zhy on 15/11/6.
 */
public class OkHttpPutRequest extends OkHttpRequest {


    private String content = "put";

    private String json;

    private int type = 0;

    private static final int TYPE_PARAMS = 1;
    private static final int TYPE_JSON = 2;

    private final MediaType MEDIA_TYPE_STRING = MediaType.parse("text/plain;charset=utf-8");
    private final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");

    private static final int TYPE_STRING = 2;

    protected OkHttpPutRequest(String url, String tag, Map<String, String> params, Map<String, String> headers, String json) {
        super(url, tag, params, headers);
        this.json=json;
        validParams();
    }

    private void validParams() {
        int count = 0;
        if (params != null && !params.isEmpty()) {
            type = TYPE_PARAMS;
            count++;
        }
        if (json != null) {
            type = TYPE_JSON;
            count++;
        }
    }

    @Override
    protected Request buildRequest() {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be empty!");
        }
        //append params , if necessary
       // url = appendParams(url, params);
        Request.Builder builder = new Request.Builder();
        //add headers , if necessary
        appendHeaders(builder, headers);
        builder.url(url).tag(tag).put(requestBody);
        return builder.build();
    }


//    private String appendParams(String url, Map<String, String> params) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(url);
//        if (params != null && !params.isEmpty()) {
//            for (String key : params.keySet()) {
//                sb.append("/").append(params.get(key));
//            }
//        }
//        return sb.toString();
//    }

    @Override
    protected RequestBody buildRequestBody() {
        RequestBody requestBody = null;
        switch (type) {
            case TYPE_PARAMS:
                FormEncodingBuilder builder = new FormEncodingBuilder();
                addParams(builder, params);
                requestBody = builder.build();
                //requestBody = RequestBody.create(MEDIA_TYPE_STRING, content);
                break;
            case TYPE_JSON:
                requestBody = RequestBody.create(MEDIA_TYPE_JSON, json);
                break;
        }
        return requestBody;
    }

    private void addParams(FormEncodingBuilder builder, Map<String, String> params) {
        if (builder == null) {
            throw new IllegalArgumentException("builder can not be null .");
        }

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }
}
