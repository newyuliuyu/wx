package com.ez.wx.message.processor;

import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpGetBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.wx.WxConfig;
import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.bean.WxXmlOutMessage;
import com.ez.common.wx.bean.WxXmlOutTextMessage;
import com.ez.wx.message.process.WxEvent;
import com.ez.wx.message.process.WxProcessInvokeMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;

/**
 * ClassName: WxSubscribeEvent <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午5:51 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@WxEvent(value = "CLICK", validateClass = MenuFetchStudentPdfReportEvent.class, validateInvokeMethod = "match")
public class MenuFetchStudentPdfReportEvent {

    public boolean match(WxXmlMessage inmsge) {

        return true;
    }

    @WxProcessInvokeMethod
    public WxXmlOutMessage click(WxXmlMessage inMsg) {
        String wxopenid = inMsg.getFromUserName();
        sendPdfReport(wxopenid);
        log.debug("************************************");
        return WxXmlOutTextMessage.TEXT().content("请您请稍等，报告一会就推送给你!").fromUser(inMsg.getToUserName()).toUser(inMsg.getFromUserName()).build();
    }

    private void sendPdfReport(String wxopenid) {
        WxConfig config = WxConfig.getInstance();
        String url = config.getWxsendcj() + "/fetch/student/pdf/report/" + wxopenid;
        log.debug(url);
        HttpGet get = HttpGetBuilder.create(url).build();
        HCUtils hcUtils = HCUtils.createDefault();
        try {
            RequestResult result = hcUtils.exec(get);
            String json = result.getContent();
            log.debug(json);
        } catch (Exception e) {
            log.error("发送报告请求出错", e);
        } finally {
            hcUtils.close();
        }
    }
}
