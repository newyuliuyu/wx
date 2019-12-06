package com.ez.business.dao;

import com.ez.business.bean.SubjectCj;
import com.ez.wx.WxSendcjnoticeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: StudentPdfReportDaoTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-11-25 下午2:06 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
public class StudentPdfReportDaoTest {

    @Autowired
    private StudentPdfReportDao dao;



    @Test
    public void getStudentSubjectCj() throws Exception{
        List<SubjectCj> subjectCjs1 = dao.getStudentSubjectCjs(165L,"16901001");
        List<SubjectCj> subjectCjs2 = dao.getStudentSubjectCjs(1650L,"16901001");

        System.out.println();
    }
}
