package com.ez.wx.service.process;

import com.ez.business.dao.WxBoundStudentDao;
import com.ez.wx.WxSendcjnoticeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: PublishStudentPdfReportStarterTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-11-5 下午1:48 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
@Slf4j
public class PublishStudentPdfReportStarterTest {

    @Autowired
    private WxBoundStudentDao wxBoundStudentDao;

    @Test
    public void test1() throws Exception{
       System.out.println(wxBoundStudentDao.isPay("wxBoundStudentDao",""));
       System.out.println(wxBoundStudentDao.isPay("wxBoundStudentDao","511623198509124563"));
       System.out.println(wxBoundStudentDao.isPay("e1c59e62c4ea9d62cfe5d79ea0bb5d5e",""));
    }

    @Test
    public void test() throws Exception{
        PublishStudentPdfReportStarter starter = new PublishStudentPdfReportStarter("oFbfZwjzUU_z0DWfLn2b-LglL-QY");
        starter.run();
    }
}