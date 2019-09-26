package com.ez.wx.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.wx.webuser.GetWebUserInfo;
import com.ez.common.wx.webuser.WebAccessToken;
import com.ez.common.wx.webuser.WebUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: WxOauth2Controller <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-6 上午9:45 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Controller
@Slf4j
public class WxOauth2Controller {

    @RequestMapping(value = "/oauth2/{model}")
    public ModelAndView oauth2(@PathVariable String model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        GetWebUserInfo getWebUserInfo = getWebUserInfo(request);
        WebAccessToken webAccessToken = getWebUserInfo.getWebAccessToken();
        return ModelAndViewFactory.instance("/menu/model/" + model).with("openid", webAccessToken.getOpenid()).build();
    }

    private GetWebUserInfo getWebUserInfo(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        log.debug("code:{},state:{}", code, state);
        return new GetWebUserInfo(code);
    }

    @RequestMapping(value = "/oauth2/2/{model}")
    public ModelAndView oauth22(@PathVariable String model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        GetWebUserInfo getWebUserInfo = getWebUserInfo(request);
        WebAccessToken webAccessToken = getWebUserInfo.getWebAccessToken();
        WebUserInfo webUserInfo = getWebUserInfo.getWebUserInfo();
        log.debug(webUserInfo.toString());
        return ModelAndViewFactory.instance("/menu/model/" + model).with("openid", webAccessToken.getOpenid()).build();
    }


}
