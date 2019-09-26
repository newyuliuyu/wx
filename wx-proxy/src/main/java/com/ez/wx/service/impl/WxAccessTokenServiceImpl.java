package com.ez.wx.service.impl;

import com.alibaba.fastjson.JSONPath;
import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpGetBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.spring.SpringContextUtil;
import com.ez.common.wx.WxConfig;
import com.ez.wx.service.WxAccessTokenService;
import com.ez.wx.service.WxServerFetchAccessToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class WxAccessTokenServiceImpl implements WxAccessTokenService {
    private Object monitorObj = new Object();

    @Override
    public String fetchAccessToken() {
        WxConfig wxConfig = WxConfig.getInstance();
        if (wxConfig.isAccessTokenExpired()) {
            log.warn("access token已过期,重新获取");
            loadAccessTokenService();
        }

        if (wxConfig.isAccessTokenExpired()) {
            log.warn("access token已过期,重新获取");
            updateAccessToken();
        }
        return WxConfig.getInstance().getAccessToken();
    }

    private void loadAccessTokenService() {
        WxConfig config = WxConfig.getInstance();
        if (StringUtils.isEmpty(config.getMyAccessTokenServerURL())) {
            WxServerFetchAccessToken fetchAccessToken = SpringContextUtil.getBean(WxServerFetchAccessToken.class);
            fetchAccessToken.reloadAccessToken();
        } else {
            updateAccessToken();
        }
    }

    private void updateAccessToken() {
        WxConfig wxConfig = WxConfig.getInstance();
        synchronized (monitorObj) {
            if (wxConfig.isAccessTokenExpired()) {
                String url = wxConfig.getMyAccessTokenServerURL() + "/wx/access";
                HttpGet get = HttpGetBuilder.create(url)
                        .addHeader("Content-type", "application/json; charset=utf-8")
                        .build();
                HCUtils hcUtils = HCUtils.createDefault();
                String json = "";
                try {
                    RequestResult result = hcUtils.exec(get);
                    json = result.getContent();
                    log.debug("获取Access-Token的json[{}]", json);
                } finally {
                    hcUtils.close();
                }
                Object jsonObj = Json2.parse(json);
                String accessToken = JSONPath.eval(jsonObj, "$.access_token").toString();
                int expiresIn = Integer.parseInt(JSONPath.eval(jsonObj, "$.expires_in").toString());
                wxConfig.updateAccessToken(accessToken, expiresIn);
            }
        }
    }
}
