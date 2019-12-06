package com.ez.wx.message.process;

import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.xml.XStreamTransformer;
import org.junit.Test;

/**
 * ClassName: WxMessageProcessorTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午4:01 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxMessageProcessorTest {

    @Test
    public void test() throws Exception {
        WxMessageHandlerMapping mapping = WxMessageHandlerMapping.initInstance("com.ez.wx.message.processor");

        String xml = "<xml>" +
                "<ToUserName><![CDATA[用户openID]]></ToUserName>" +
                "<FromUserName><![CDATA[微信账号]]></FromUserName>" +
                "<CreateTime>1348831860</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[this is a test]]></Content>" +
                "<MsgId>1234567890123456</MsgId>" +
                "</xml>";
        WxXmlMessage inMsg = XStreamTransformer.fromXml(WxXmlMessage.class, xml);

        String resultXml = WxMessageProcessor.create(inMsg).process().getXml();

        System.out.println();
    }

    @Test
    public void subscribe() throws Exception {
        WxMessageHandlerMapping mapping = WxMessageHandlerMapping.initInstance("com.ez.wx.message.processor");

        String xml = "<xml>" +
                "<ToUserName><![CDATA[微信账号]]></ToUserName>" +
                "<FromUserName><![CDATA[用户openID]]></FromUserName>" +
                "<CreateTime>1348831860</CreateTime>" +
                "<MsgType><![CDATA[event1]]></MsgType>" +
                "<Event><![CDATA[subscribe]]></Event>" +
                "</xml>";
        WxXmlMessage inMsg = XStreamTransformer.fromXml(WxXmlMessage.class, xml);

        String resultXml = WxMessageProcessor.create(inMsg).process().getXml();

        System.out.println();
    }
    @Test
    public void MenuFetchStudentPdfReportEvent() throws Exception {
        WxMessageHandlerMapping mapping = WxMessageHandlerMapping.initInstance("com.ez.wx.message.processor");

        String xml = "<xml>" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>" +
                "<FromUserName><![CDATA[FromUser]]></FromUserName>" +
                "<CreateTime>123456789</CreateTime>" +
                "<MsgType><![CDATA[event]]></MsgType>" +
                "<Event><![CDATA[CLICK]]></Event>" +
                "<EventKey><![CDATA[EVENTKEY]]></EventKey>" +
                "</xml>";
        WxXmlMessage inMsg = XStreamTransformer.fromXml(WxXmlMessage.class, xml);

        String resultXml = WxMessageProcessor.create(inMsg).process().getXml();

        System.out.println();
    }
}