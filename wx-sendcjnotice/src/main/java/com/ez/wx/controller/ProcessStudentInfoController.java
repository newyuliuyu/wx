package com.ez.wx.controller;

import com.ez.business.bean.FileInfo;
import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.wx.service.ProcessStudentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: ImportStudentInfoController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 下午2:53 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@Controller
public class ProcessStudentInfoController {

    @Autowired
    private ProcessStudentInfoService processStudentInfoService;

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public ModelAndView upload(@RequestBody List<FileInfo> fileInfos,
                               HttpServletRequest request,
                               HttpServletResponse responese) throws Exception {
        log.debug("process student info");
        String onlyKey = processStudentInfoService.process(fileInfos);
        return ModelAndViewFactory.instance().with("onlyKey", onlyKey).build();
    }
}
