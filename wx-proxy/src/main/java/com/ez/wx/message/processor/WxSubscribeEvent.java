package com.ez.wx.message.processor;

import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.bean.WxXmlOutMessage;
import com.ez.common.wx.bean.WxXmlOutTextMessage;
import com.ez.wx.message.process.WxEvent;
import com.ez.wx.message.process.WxProcessInvokeMethod;

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
@WxEvent(value = "subscribe", validateClass = MatchMessage.class, validateInvokeMethod = "subscribeEvent")
public class WxSubscribeEvent {

    @WxProcessInvokeMethod
    public WxXmlOutMessage subscribe(WxXmlMessage inMsg) {

        return WxXmlOutTextMessage.TEXT().content("欢迎来到易考乐学公众号！").fromUser(inMsg.getToUserName()).toUser(inMsg.getFromUserName()).build();
    }
}
