package com.ipatoo.mypatoo.bean;

import java.io.Serializable;

/**
 * Created by KevenTao on 2017/11/21.
 */

public class VerifyMsg implements Serializable {

    /**
     * code : 1835
     * expires_time : 1499915767
     * mobile : 15052205563
     */

    private String code;
    private String expires_time;
    private String mobile;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpires_time() {
        return expires_time;
    }

    public void setExpires_time(String expires_time) {
        this.expires_time = expires_time;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
