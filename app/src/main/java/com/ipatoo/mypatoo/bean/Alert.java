package com.ipatoo.mypatoo.bean;

import java.io.Serializable;

/**
 * Created by KevenTao on 2017/11/21.
 */

public class Alert implements Serializable {

    /**
     * title :
     * msg :
     * url :
     */

    private String title;
    private String msg;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
