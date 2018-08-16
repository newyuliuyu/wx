package com.ez.common.wx.webuser;

import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.wx.WxConfig;
import com.ez.common.wx.bean.WxConsts;
import org.apache.http.client.methods.HttpGet;

/**
 * ClassName: GetWebUserInfo <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-3-19 下午2:13 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class GetWebUserInfo {
    private String code;
    private WebUserInfo webUserInfo;
    private WebAccessToken webAccessToken;

    public GetWebUserInfo(String code) {
        this.code = code;
    }

    public WebAccessToken getWebAccessToken() {
        if (webAccessToken == null) {
            webAccessToken = parseWebAccessToken();
        }
        return webAccessToken;
    }

    private WebAccessToken parseWebAccessToken() {
        WxConfig wxConfig = WxConfig.getInstance();
        String url = WxConsts.URL_OAUTH2_GET_ACCESSTOKEN.replace("APPID", wxConfig.getAppId())
                .replace("SECRET", wxConfig.getAppSecret())
                .replace("CODE", code);
        HttpGet get = new HttpGet(url);
        get.addHeader("Content-type", "application/json; charset=utf-8");
        HCUtils hcUtils = HCUtils.createDefault();
        RequestResult result = hcUtils.exec(get);
        hcUtils.close();
        WebAccessToken webAccessToken = Json2.fromJson(result.getContent(), WebAccessToken.class);
        return webAccessToken;
    }

    public WebUserInfo getWebUserInfo() {
        if (webUserInfo == null) {
            webUserInfo = parseWebUserInfo();
        }
        return webUserInfo;
    }

    public WebUserInfo parseWebUserInfo() {
        WebAccessToken webAccessToken = getWebAccessToken();
        WxConfig wxConfig = WxConfig.getInstance();
        String url = WxConsts.URL_OAUTH2_GET_USER_INFO.replace("ACCESS_TOKEN", webAccessToken.getAccess_token())
                .replace("OPENID", webAccessToken.getOpenid());
        HttpGet get = new HttpGet(url);
        get.addHeader("Content-type", "application/json; charset=utf-8");
        HCUtils hcUtils = HCUtils.createDefault();
        RequestResult result = hcUtils.exec(get);
        hcUtils.close();
        WebUserInfo webUserInfo = Json2.fromJson(result.getContent(), WebUserInfo.class);
        return webUserInfo;
    }
}
