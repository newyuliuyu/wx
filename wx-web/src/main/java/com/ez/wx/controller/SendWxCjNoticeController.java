package com.ez.wx.controller;

import com.ez.common.json.Json2;
import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.wx.bean.WxNoticeData;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/wxcjnotice")
@Slf4j
public class SendWxCjNoticeController {

    @RequestMapping()
    public ModelAndView sendNotice(@RequestBody WxNoticeData wxNoticeData, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println();
        log.debug(Json2.toJson(wxNoticeData));
        return ModelAndViewFactory.instance().with("ok", "ok").build();
    }
}
