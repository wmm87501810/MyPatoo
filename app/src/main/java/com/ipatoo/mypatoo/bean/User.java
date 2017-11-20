package com.ipatoo.mypatoo.bean;

import java.io.Serializable;

/**
 * Created by dell on 2017/11/16.
 */

public class User implements Serializable {

    /**
     * uid : 14
     * nickname : 老郭
     * mobile : 12300000000
     * password : 1234
     * sex : 2
     * identity : 1
     * img_top : uploadfile/2017/0727/20170727061047905_thumb.jpg
     * intro :
     * client_id :
     * name :
     * token : 18c78188ecdf1e28dd909008d46c0ecb
     * deviceName : veoVKDvI7fsSE0
     * deviceSecret : kqGwaQk9NuPvO8z90w6tO7d7zpK0VM46
     * productKey : oVKDvI7fsSE
     * score : 106
     * percentage : 89
     * date : 2017-07-07 10:35:53
     */

    private String uid;
    private String nickname;
    private String mobile;
    private String password;
    private String sex;
    private String identity;
    private String img_top;
    private String intro;
    private String client_id;
    private String name;
    private String token;
    private String deviceName;
    private String deviceSecret;
    private String productKey;
    private String score;
    private String percentage;
    private String date;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getImg_top() {
        return img_top;
    }

    public void setImg_top(String img_top) {
        this.img_top = img_top;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", identity='" + identity + '\'' +
                ", img_top='" + img_top + '\'' +
                ", intro='" + intro + '\'' +
                ", client_id='" + client_id + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceSecret='" + deviceSecret + '\'' +
                ", productKey='" + productKey + '\'' +
                ", score='" + score + '\'' +
                ", percentage='" + percentage + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
