package com.ez.business.service;

import com.ez.wx.WxSendcjnoticeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: PublishExamCjServiceTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午5:35 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
@Slf4j
public class PublishExamCjServiceTest {

    @Autowired
    private PublishExamCjService publishExamCjService;

    @Test
    public void checkYetPublishStudentCj() throws Exception {
       boolean yes =  publishExamCjService.isYetPublishStudentCj(196);
       System.out.println();
    }

}
