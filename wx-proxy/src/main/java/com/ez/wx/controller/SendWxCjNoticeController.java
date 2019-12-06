package com.ez.wx.controller;

import com.ez.business.bean.wx.WxNoticeData;
import com.ez.common.json.Json2;
import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.wx.service.WxNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: SendWxCjNoticeController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 下午3:42 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Controller
@Slf4j
public class SendWxCjNoticeController {
    @Autowired(required = false)
    @Qualifier("wxCjNoticeService")
    private WxNoticeService service;

    @RequestMapping("/wxcjnotice")
    public ModelAndView sendNotice(@RequestBody WxNoticeData wxNoticeData, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入/wxcjnotice．．．．");
        log.debug(Json2.toJson(wxNoticeData));
        service.notice(wxNoticeData);
        return ModelAndViewFactory.instance().with("ok", "ok").build();
    }
    @RequestMapping("/wxpdfreportnotice")
    public ModelAndView wxpdfreportnotice(@RequestBody WxNoticeData wxNoticeData, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入/wxpdfreportnotice．．．．");
        log.debug(Json2.toJson(wxNoticeData));
        service.notice(wxNoticeData);
        return ModelAndViewFactory.instance().with("ok", "ok").build();
    }
}
