package com.ez.common.wx;

import com.ez.common.spring.SpringContextUtil;

/**
 * ClassName: WXConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-1 下午4:01 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxConfig {

    private String appId;
    private String appSecret;
    private String token;
    private String aesKey;
    private String mchId;
    private String apiKey;
    private String notifyUrl;
    private String wxsendcj;
    private String myAccessTokenServerURL;

    //内存更新
    private volatile String accessToken;
    private volatile long expiresTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public static WxConfig getInstance() {
        WxConfig wxConfig = SpringContextUtil.getBean(WxConfig.class);
        return wxConfig;
    }


    public void expireAccessToken() {
        this.expiresTime = 0;
    }

    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }

    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getWxsendcj() {
        return wxsendcj;
    }

    public void setWxsendcj(String wxsendcj) {
        this.wxsendcj = wxsendcj;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public String getMyAccessTokenServerURL() {
        return myAccessTokenServerURL;
    }

    public void setMyAccessTokenServerURL(String myAccessTokenServerURL) {
        this.myAccessTokenServerURL = myAccessTokenServerURL;
    }
}
