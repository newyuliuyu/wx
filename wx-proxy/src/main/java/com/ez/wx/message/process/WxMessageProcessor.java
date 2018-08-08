package com.ez.wx.message.process;

import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.bean.WxXmlOutMessage;
import com.ez.common.wx.bean.WxXmlOutTextMessage;

/**
 * ClassName: WxMessageProcessor <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-27 下午4:50 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxMessageProcessor {

    private WxXmlMessage wxXmlMessage;
    private WxXmlOutMessage outMessage;

    private WxMessageProcessor(WxXmlMessage wxXmlMessage) {
        this.wxXmlMessage = wxXmlMessage;
    }

    public static WxMessageProcessor create(WxXmlMessage wxXmlMessage) {
        return new WxMessageProcessor(wxXmlMessage);
    }

    public WxMessageProcessor process() {
        WxMessageHandlerMapping mapping = WxMessageHandlerMapping.getInstance();
        MsgProcessInvoker invoker = mapping.findProcess(wxXmlMessage);
        if (invoker != null) {
            outMessage = invoker.invoke(wxXmlMessage);
        }
        return this;
    }

    public String getXml() {
        if (outMessage == null) {
            return "";
        }
        return outMessage.toXml();
    }

    public WxXmlOutMessage getWxXmlOutMessage() {
        if (outMessage == null) {
            return WxXmlOutTextMessage.TEXT().content("").fromUser(wxXmlMessage.getToUserName()).toUser(wxXmlMessage.getFromUserName()).build();
        }
        return outMessage;
    }
}
