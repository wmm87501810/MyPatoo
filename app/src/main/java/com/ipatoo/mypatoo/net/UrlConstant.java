package com.ipatoo.mypatoo.net;

/**
 * Created by dell on 2017/11/14.
 */

public class UrlConstant {
    public static final String BASE_URL = "http://app1.ipatoo.cn";

    //登录接口POST
    public static final String POST_LOGIN = BASE_URL + "/rest/index.php?c=iuser&a=loginOrRegister";

    //发送验证码
    public static final String GET_SEND_MES= BASE_URL + "/rest/index.php?c=iuser&a=verify_sms";
}
