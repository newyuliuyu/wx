package com.ez.business.service;

import com.ez.business.bean.Exam;
import com.ez.business.bean.Student;
import com.ez.wx.WxSendcjnoticeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * ClassName: StudentCjServiceTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-20 下午3:43 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
public class StudentCjServiceTest {
    @Autowired
    StudentCjService studentCjService;

    @Test
    public void getStudentInfo() throws Exception {
        Student student = studentCjService.getStudentInfo("16901009");
        System.out.println();
    }

    @Test
    public void fetchStudentExams() throws Exception {
        List<Exam> exams = studentCjService.fetchStudentExams("16901009");
        System.out.println();
    }

    @Test
    public void fetchStudentSubjectScores() throws Exception {
        List<Map<String, Object>> scores = studentCjService.fetchStudentSubjectScores(165, "16901009");
        System.out.println();
    }
}
