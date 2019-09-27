package com.ez.wx.service.impl;

import com.ez.common.spring.SpringContextUtil;
import com.ez.common.wx.WxConfig;
import com.ez.wx.service.WxAccessTokenService;
import com.ez.wx.service.WxServerFetchAccessToken;
import lombok.extern.slf4j.Slf4j;
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
        return WxConfig.getInstance().getAccessToken();
    }

    private void loadAccessTokenService() {
        WxServerFetchAccessToken fetchAccessToken = SpringContextUtil.getBean(WxServerFetchAccessToken.class);
        fetchAccessToken.reloadAccessToken();
    }


}
