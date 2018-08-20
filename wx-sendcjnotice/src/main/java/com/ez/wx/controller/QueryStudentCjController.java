package com.ez.wx.controller;

import com.ez.business.bean.Exam;
import com.ez.business.bean.Student;
import com.ez.business.service.ExamService;
import com.ez.business.service.StudentCjService;
import com.ez.common.mvc.ModelAndViewFactory;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * ClassName: QueryStudentCjController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-20 下午3:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@Controller
@RequestMapping("/student")
public class QueryStudentCjController {
    @Autowired
    private StudentCjService studentCjService;
    @Autowired
    private ExamService examService;

    @RequestMapping("/info")
    public ModelAndView studentInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uid = request.getParameter("uid");
        String zkzh = request.getParameter("zkzh");
        Preconditions.checkArgument(zkzh != null || "".equals(zkzh), "准考证好不能为null");
        Student student = studentCjService.getStudentInfo(zkzh);
        List<Exam> exams = Lists.newArrayList();
        if (student != null) {
            exams = studentCjService.fetchStudentExams(student.getCode());
        }

        return ModelAndViewFactory.instance()
                .with("student", student)
                .with("exams", exams)
                .with("uid", uid)
                .build();
    }

    @RequestMapping("/subject/score")
    public ModelAndView studentSubjectScores(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Preconditions.checkArgument(request.getParameter("examId") != null || "".equals(request.getParameter("examId")), "考试ID不能为空");
        long examId = Long.parseLong(request.getParameter("examId"));
        String zkzh = request.getParameter("zkzh");
        Preconditions.checkArgument(zkzh != null || "".equals(zkzh), "准考证好不能为null");

        List<Map<String, Object>> subjectScores = studentCjService.fetchStudentSubjectScores(examId, zkzh);
        Exam exam = examService.getExam(examId);
        return ModelAndViewFactory.instance()
                .with("subjectScores", subjectScores)
                .with("exam", exam)
                .build();
    }
}
