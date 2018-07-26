package com.ez.common.wx.bean;


import com.ez.common.json.Json2;

/**
 * 微信AccessToken，微信API接口调用凭证
 *
 * @author antgan
 */
public class WxAccessToken {
    private String access_token;
    private int expires_in = -1;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public static WxAccessToken fromJson(String json) {
        WxAccessToken wxAccessToken = Json2.fromJson(json, WxAccessToken.class);
        return wxAccessToken;
    }

    @Override
    public String toString() {
        return "WxAccessToken [access_token=" + access_token + ", expires_in=" + expires_in + "]";
    }

}
