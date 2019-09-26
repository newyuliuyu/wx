package com.ez.wx.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.wx.WxConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: MenuController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-3-19 下午2:56 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping("/{model}")
    public ModelAndView menu(@PathVariable String model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WxConfig wxConfig = WxConfig.getInstance();
        return ModelAndViewFactory.instance("menu/menu").with("model", model).with("appId", wxConfig.getAppId()).build();
    }
    @RequestMapping("/2/{model}")
    public ModelAndView menu2(@PathVariable String model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WxConfig wxConfig = WxConfig.getInstance();
        return ModelAndViewFactory.instance("menu/menu2").with("model", model).with("appId", wxConfig.getAppId()).build();
    }
}
