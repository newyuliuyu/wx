package com.ez.wx;

import com.alibaba.fastjson.JSONPath;
import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpGetBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.wx.bean.WxConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

/**
 * ClassName: FetchAccessTokenTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-16 上午11:09 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class FetchAccessTokenTest {

    @Test
    public void fetch() throws Exception {
        String url = WxConsts.URL_GET_ACCESSTOEKN;
        url = url.replaceAll("APPID", "wx9eaba18ff17ce38f");
        url = url.replaceAll("APPSECRET", "23d4adc9ad252c6bfc47de92619b20e5");
//        log.debug(url);

        HttpGet get = HttpGetBuilder.create(url)
                .addHeader("Content-type", "application/json; encoding=utf-8")
                .build();
        HCUtils hcUtils = HCUtils.createDefault();
        RequestResult result = hcUtils.exec(get);
        String json = result.getContent();
        log.debug(json);
        Object jsonObj = Json2.parse(json);
        String accessToken = JSONPath.eval(jsonObj, "$.access_token").toString();
        int expiresIn = Integer.parseInt(JSONPath.eval(jsonObj, "$.expires_in").toString());

        log.debug(accessToken);
    }
    @Test
    public void parseJson() throws Exception {

        String json = "{\"access_token\":\"12_wgVAworaHHLEigxp9Yx1VAzz-HFpZ3Pcl-bJdSDqzw-IWifSGCQrZwR5OD3feknf_B2kYV3PRPdjAq6D1YvMu44ckzSXSaSTe7RNoNF5icDTyxstFqHjeQ-5fDoFSYfAHALYE\",\"expires_in\":7200}";
        log.debug(json);
        Object jsonObj = Json2.parse(json);
        String accessToken = JSONPath.eval(jsonObj, "$.access_token").toString();
        int expiresIn = Integer.parseInt(JSONPath.eval(jsonObj, "$.expires_in").toString());

        log.debug(accessToken);
    }
}
