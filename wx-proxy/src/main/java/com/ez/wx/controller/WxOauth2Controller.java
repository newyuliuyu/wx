package com.ez.wx.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.wx.webuser.GetWebUserInfo;
import com.ez.common.wx.webuser.WebAccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class WxOauth2Controller {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/oauth2/{model}")
    public ModelAndView oauth2(@PathVariable String model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.debug("code:{},state:{}", code, state);

        GetWebUserInfo getWebUserInfo = new GetWebUserInfo(code);
        WebAccessToken webAccessToken = getWebUserInfo.getWebAccessToken();
        return ModelAndViewFactory.instance("/menu/model/" + model).with("openid", webAccessToken.getOpenid()).build();
    }


}
