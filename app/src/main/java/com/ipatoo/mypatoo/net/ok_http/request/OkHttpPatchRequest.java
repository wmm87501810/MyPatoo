package com.ipatoo.mypatoo.net.ok_http.request;

import android.text.TextUtils;

import com.ipatoo.mypatoo.net.ok_http.callback.ResultCallback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

/**
 * Created by Xuhl on 15/11/6.
 */
public class OkHttpPatchRequest extends OkHttpRequest {

    private String content;

    private int type = 0;
    private static final int TYPE_PARAMS = 1;
    private static final int TYPE_STRING = 2;

    private final MediaType MEDIA_TYPE_STRING = MediaType.parse("text/plain;charset=utf-8");


    protected OkHttpPatchRequest(String url, String tag, Map<String, String> params, Map<String, String> headers, String content
                               ) {
        super(url, tag, params, headers);
        this.content = content;
        validParams();

    }

    private void validParams() {
        int count = 0;
        if (params != null && !params.isEmpty()) {
            type = TYPE_PARAMS;
            count++;
        }
        if (content != null) {
            type = TYPE_STRING;
            count++;
        }

//        if (count <= 0 || count > 1)
//        {
//            throw new IllegalArgumentException("the params , content , file , bytes must has one and only one .");
//        }
    }

    @Override
    protected Request buildRequest() {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be empty!");
        }
        Request.Builder builder = new Request.Builder();
        appendHeaders(builder, headers);
        builder.url(url).tag(tag).patch(requestBody);
        return builder.build();
    }

    @Override
    protected RequestBody buildRequestBody() {
        RequestBody requestBody = null;
            switch (type) {
            case TYPE_PARAMS:
                FormEncodingBuilder builder = new FormEncodingBuilder();
                addParams(builder, params);
                requestBody = builder.build();
                break;
            case TYPE_STRING:
                requestBody = RequestBody.create(MEDIA_TYPE_STRING, content);
                break;
        }
        return requestBody;
    }

    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final ResultCallback callback) {
        CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {

                mOkHttpClientManager.getDelivery().post(new Runnable() {
                    @Override
                    public void run() {
                        callback.inProgress(bytesWritten * 1.0f / contentLength);
                    }
                });

            }
        });
        return countingRequestBody;
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
