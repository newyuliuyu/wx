package com.ez.wx.service.process;

import com.ez.wx.WxSendcjnoticeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: PublishStudentCjStarterTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午5:17 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
@Slf4j
public class PublishStudentCjStarterTest {

    @Test
    public void start() throws Exception {
        PublishStudentCjStarter starter = new PublishStudentCjStarter(null, 196);
        starter.run();
        System.out.println();
    }
}