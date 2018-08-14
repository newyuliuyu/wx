package com.ez.wx.service;

import com.ez.business.bean.StudentCj;
import com.ez.wx.WxSendcjnoticeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: StartPublishCjStarterTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-14 下午4:02 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
@Slf4j
public class StartPublishCjStarterTest {

    @Autowired
    StartPublishCjStarter startPublishCjStarter;

    @Test
    public void publishStudentCj() throws Exception {
        StudentCj studentCj = StudentCj.builder().zkzh("16901002").id(29).build();

        startPublishCjStarter.publishStudentCj(196, new StudentCj[]{studentCj});
    }
}