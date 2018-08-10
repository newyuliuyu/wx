package com.ez.business.service;

import com.ez.business.bean.SystemAttribute;
import com.ez.wx.WxSendcjnoticeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: SystemAttributeServiceTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 上午10:18 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
public class SystemAttributeServiceTest {
    @Autowired
    private SystemAttributeService service;

    @Test
    public void test11() throws Exception {
        List<SystemAttribute> attributes = service.list();
        System.out.println();
    }
}
