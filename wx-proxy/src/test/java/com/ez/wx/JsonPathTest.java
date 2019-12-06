package com.ez.wx;

import com.alibaba.fastjson.JSONPath;
import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpGetBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.wx.WxConfig;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

/**
 * ClassName: JsonPathTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-26 下午3:14 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class JsonPathTest {
    private String json = "{\"wxPayOrder\":{\"id\":0,\"moeny\":1,\"orderNum\":\"1177118519062560768\",\"orderTimestamp\":1569481899406,\"pay\":false,\"wxopenid\":\"oFbfZwjzUU_z0DWfLn2b-LglL-QY\"},\"status\":{\"code\":\"0\",\"detail\":\"\",\"msg\":\"\",\"success\":true}}";

    @Test
    public void test() throws Exception {
       Object object = JSONPath.read(json,"$.wxPayOrder.orderNum");
       System.out.println();
    }


}
