package com.ez.wx.service;

import com.ez.business.bean.SystemAttributeKey;
import com.ez.wx.WxSendcjnoticeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: SystemAttributeMgrTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 上午11:13 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
@Slf4j
public class SystemAttributeMgrTest {

    @Test
    public void testUploadDir() throws Exception {
        String url = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.UPLOAD_PATH);
        log.debug(url);
    }
}