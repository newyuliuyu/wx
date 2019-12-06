package com.ez.wx;

import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpGetBuilder;
import com.ez.common.httpclient.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: CreateWxMenuTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-5 下午1:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxProxyApplication.class)
public class MyTest {

    @Test
    public void sendPdfReport()throws Exception{

        String url ="http://127.0.0.1/wxsendcj/fetch/student/pdf/report/oFbfZwjzUU_z0DWfLn2b-LglL-QY";
        log.debug(url);
        HttpGet get = HttpGetBuilder.create(url).build();
        HCUtils hcUtils = HCUtils.createDefault();
        try {
            RequestResult result = hcUtils.exec(get);
            String json = result.getContent();
            log.debug(json);
        } finally {
            hcUtils.close();
        }
    }
}
