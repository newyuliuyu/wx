package com.ez.wx.controller;

import com.ez.business.bean.WxBoundStudent;
import com.ez.business.service.WxBoundStudentService;
import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.util.GradeInfo;
import com.ez.common.util.GradeNameOrderHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: WxBoundStudentController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-14 下午4:58 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@Controller
public class WxBoundStudentController {

    @Autowired
    private WxBoundStudentService wxBoundStudentService;

    @RequestMapping(value = "/fetch/studentinfo/{wxopenid}")
    public ModelAndView fetchStudentinfoWithWxopendid(@PathVariable String wxopenid,
                                                      HttpServletRequest request,
                                                      HttpServletResponse responese) throws Exception {
        log.debug("/fetch/studentinfo/{}", wxopenid);
        WxBoundStudent wxBoundStudent = wxBoundStudentService.getWxBoundStudent(wxopenid);
        return ModelAndViewFactory.instance().with("student", wxBoundStudent).build();
    }

    @RequestMapping(value = "/bound", method = RequestMethod.POST)
    public ModelAndView bound(@RequestBody WxBoundStudent wxBoundStudent,
                              HttpServletRequest request,
                              HttpServletResponse responese) throws Exception {
        log.debug("post /bound/{}", wxBoundStudent.toString());
        GradeInfo gradeInfo = GradeNameOrderHelper.getGradeInfo(wxBoundStudent.getGrade());
        wxBoundStudent.setEntrySchoolYear(gradeInfo.getEntranceYear());
        wxBoundStudentService.boundStudent(wxBoundStudent);
        return ModelAndViewFactory.instance().with("status", "success").build();
    }

    @RequestMapping(value = "/bound/update", method = RequestMethod.POST)
    public ModelAndView boundUpdate(@RequestBody WxBoundStudent wxBoundStudent,
                                    HttpServletRequest request,
                                    HttpServletResponse responese) throws Exception {
        log.debug("post /bound/update/{}", wxBoundStudent.toString());
        GradeInfo gradeInfo = GradeNameOrderHelper.getGradeInfo(wxBoundStudent.getGrade());
        wxBoundStudent.setEntrySchoolYear(gradeInfo.getEntranceYear());
        wxBoundStudentService.udpateBoundStudent(wxBoundStudent);
        return ModelAndViewFactory.instance().with("status", "success").build();
    }

}
