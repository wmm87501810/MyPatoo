package com.ipatoo.mypatoo.net.ok_http.request;

import android.util.Pair;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by zhy on 15/11/6.
 */
public class OkHttpUploadRequest extends OkHttpPostRequest {
    private Pair<String, File>[] files;
    private List<Pair<String, File>> filesList;


    protected OkHttpUploadRequest(String url, String tag, Map<String, String> params, Map<String, String> headers, Pair<String, File>[] files) {
        super(url, tag, params, headers, null, null, null,null);
        this.files = files;
    }
    protected OkHttpUploadRequest(String url, String tag, Map<String, String> params, Map<String, String> headers, List<Pair<String, File>> filesList) {
        super(url, tag, params, headers, null, null, null,null);
        this.filesList = filesList;
    }

    private void addParams(MultipartBuilder builder, Map<String, String> params) {
        if (builder == null) {
            throw new IllegalArgumentException("builder can not be null .");
        }

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, params.get(key)));

            }
        }
    }

    @Override
    public RequestBody buildRequestBody() {
        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);
        addParams(builder, params);

        if (files != null) {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++) {
                Pair<String, File> filePair = files[i];
                String fileKeyName = filePair.first;
                File file = filePair.second;
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //   builder.addPart(Headers.of("Content-Disposition",
//                                "form-data; name=\"" + fileKeyName + "\"; filename=\"" + fileName + "\""),
//                        fileBody);
                builder.addFormDataPart(fileKeyName, fileName, fileBody);
            }
        }

        if (filesList != null) {
            RequestBody fileBody = null;
            for (int i = 0; i < filesList.size(); i++) {
                Pair<String, File> filePair = filesList.get(i);
                String fileKeyName = filePair.first;
                File file = filePair.second;
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //   builder.addPart(Headers.of("Content-Disposition",
//                                "form-data; name=\"" + fileKeyName + "\"; filename=\"" + fileName + "\""),
//                        fileBody);
                builder.addFormDataPart(fileKeyName, fileName, fileBody);
            }
        }

        return builder.build();
    }


    private String guessMimeType(String path) {

        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

}
