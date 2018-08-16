package com.ez.wx.service;

import com.alibaba.fastjson.JSONPath;
import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpGetBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.wx.WxConfig;
import com.ez.common.wx.bean.WxConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Component;

/**
 * ClassName: AccessTokenService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午6:46 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Component
@Slf4j
public class WxAccessTokenService {
    private Object monitorObj = new Object();

    public String fetchAccessToken() {
        WxConfig wxConfig = WxConfig.getInstance();
        if (wxConfig.isAccessTokenExpired()) {
            log.warn("access token已过期,重新获取");
            //updateAccessToken();
        }
        return WxConfig.getInstance().getAccessToken();
    }

    private void updateAccessToken() {
        WxConfig wxConfig = WxConfig.getInstance();
        synchronized (monitorObj) {
            if (wxConfig.isAccessTokenExpired()) {
                String url = WxConsts.URL_GET_ACCESSTOEKN;
                url = url.replaceAll("APPID", wxConfig.getAppId());
                url = url.replaceAll("APPSECRET", wxConfig.getAppSecret());
                HttpGet get = HttpGetBuilder.create(url)
                        .addHeader("Content-type", "application/json; charset=utf-8")
                        .build();
                HCUtils hcUtils = HCUtils.createDefault();
                RequestResult result = hcUtils.exec(get);
                String json = result.getContent();
                Object jsonObj = Json2.parse(json);
                String accessToken = JSONPath.eval(jsonObj, "$.access_token").toString();
                int expiresIn = Integer.parseInt(JSONPath.eval(jsonObj, "$.expires_in").toString());
                wxConfig.updateAccessToken(accessToken, expiresIn);
            }
        }
    }
}
