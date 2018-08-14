package com.ez.wx.controller;

import com.ez.business.bean.StudentCj;
import com.ez.business.service.PublishExamCjService;
import com.ez.common.cache.MemoryCache;
import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.progress.Progresses;
import com.ez.wx.service.StartPublishCjStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: PublishExamCjController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 上午11:08 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@Controller
@RequestMapping("/publish")
public class PublishExamCjController {

    @Autowired
    private PublishExamCjService publishExamCjService;
    @Autowired
    private StartPublishCjStarter startPublishCjStarter;

    @RequestMapping(value = "/checksendcj/{examId}")
    public ModelAndView checksendcj(@PathVariable long examId,
                                    HttpServletRequest request,
                                    HttpServletResponse responese) throws Exception {
        log.debug("checksendcj......");
        String key = "publishexamcj-" + examId;
        int sendStatus = 0;
        Progresses progresses = MemoryCache.instance().getValue(key);
        if (progresses != null) {
            //正在发布...
            sendStatus = 2;
        }
        if (sendStatus == 0) {
            boolean yetPublish = publishExamCjService.isYetPublishStudentCj(examId);
            sendStatus = yetPublish ? 1 : sendStatus;
        }
        return ModelAndViewFactory.instance().with("sendStatus", sendStatus).build();
    }

    @RequestMapping(value = "/cj/{examId}")
    public ModelAndView publishexamcj(@PathVariable long examId,
                                      HttpServletRequest request,
                                      HttpServletResponse responese) throws Exception {
        log.debug("publishexamcj......");
        startPublishCjStarter.start(examId);
        return ModelAndViewFactory.instance().build();
    }

    @RequestMapping(value = "/student/cj/{examId}")
    public ModelAndView publishstudentcj(@PathVariable long examId,
                                         @RequestBody String[] zkzhs,
                                         HttpServletRequest request,
                                         HttpServletResponse responese) throws Exception {
        log.debug("publishstudentcj......");

        return ModelAndViewFactory.instance().build();
    }

    @RequestMapping(value = "/loadpublishstudent/{examId}")
    public ModelAndView loadpublishstudent(@PathVariable long examId,
                                           HttpServletRequest request,
                                           HttpServletResponse responese) throws Exception {
        log.debug("loadpublishstudent......");
        List<StudentCj> studentCjs = publishExamCjService.fetchPublishCjLog(examId);
        return ModelAndViewFactory.instance().with("students", studentCjs).build();
    }
}
