package com.ez.wx.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.wx.WxConfig;
import com.ez.wx.pay.UnifiedOrderSendInfo;
import com.ez.wx.service.WxServerFetchAccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: WxAccessTokenControoler <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-26 下午4:10 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@RestController
@RequestMapping("/wx/access")
public class WxAccessTokenControoler {
    @Autowired
    private WxServerFetchAccessToken wxServerFetchAccessToken;

    @RequestMapping()
    public ModelAndView wxAccess(@RequestBody UnifiedOrderSendInfo sendInfo,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        log.debug("entry wx access .......");
        WxConfig wxConfig = wxServerFetchAccessToken.reloadAccessToken();
        return ModelAndViewFactory.instance("")
                .with("access_token", wxConfig.getAccessToken())
                .with("expires_in", wxConfig.getExpiresTime())
                .build();
    }
}
