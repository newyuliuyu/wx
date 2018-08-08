package com.ez.wx.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: CorsControoler <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 下午4:51 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Controller
@RequestMapping("/cors")
@Slf4j
public class CorsControoler {

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public ModelAndView aMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("aMethod======");
        return ModelAndViewFactory.instance().with("ok", "cors get").build();
    }

    @RequestMapping(value = "/b", method = RequestMethod.POST)
    public ModelAndView bMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("bMethod========");
        String a = request.getParameter("a");
        return ModelAndViewFactory.instance().with("ok", "cors post").build();
    }
}
