package com.ez.wx.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: PingController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 下午1:43 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Controller
@Slf4j
public class PingController {
    @RequestMapping(value = "/ping")
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse responese) throws Exception {
        log.debug("file upload");
        return ModelAndViewFactory.instance().with("status", "tong").build();
    }
}
