package com.ez.wx.message.processor;

import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.bean.WxXmlOutMessage;
import com.ez.common.wx.bean.WxXmlOutTextMessage;
import com.ez.wx.message.process.WxMsg;
import com.ez.wx.message.process.WxProcessInvokeMethod;

/**
 * ClassName: TextProcess <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午5:15 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@WxMsg(value = "text", validateClass = MatchMessage.class, validateInvokeMethod = "defaultTextMatch")
public class TextProcess {

    @WxProcessInvokeMethod
    public WxXmlOutMessage process(WxXmlMessage inMsg) {
        return WxXmlOutTextMessage.TEXT().content("暂时不提供对话!").fromUser(inMsg.getToUserName()).toUser(inMsg.getFromUserName()).build();
    }
}
