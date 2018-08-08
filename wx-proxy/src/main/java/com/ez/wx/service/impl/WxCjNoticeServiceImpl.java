package com.ez.wx.service.impl;

import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpPostBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.wx.bean.WxConsts;
import com.ez.wx.bean.WxNoticeData;
import com.ez.wx.service.WxAccessTokenService;
import com.ez.wx.service.WxNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ClassName: WxCjNoticeServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 上午10:46 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service("wxCjNoticeService")
@Slf4j
public class WxCjNoticeServiceImpl implements WxNoticeService {

    @Value("${wx.cj.tmplate.id}")
    private String cjTmplateId;
    @Autowired
    private WxAccessTokenService accessTokenService;

    @Override
    public void notice(WxNoticeData data) {

        data.setTemplate_id(cjTmplateId);
        String accessToken = accessTokenService.fetchAccessToken();
        String url = WxConsts.URL_TEMPLATE_SEND;
        url = url.replaceAll("ACCESS_TOKEN", accessToken);
        HttpPost post = HttpPostBuilder.create(url).buildJson(Json2.toJson(data));
        RequestResult result = HCUtils.createDefault().exec(post);
        log.debug(result.getContent());
    }
}
