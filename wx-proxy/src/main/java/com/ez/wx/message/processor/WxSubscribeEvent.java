package com.ez.wx.message.processor;

import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.bean.WxXmlOutMessage;
import com.ez.common.wx.bean.WxXmlOutNewsMessage;
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
        WxXmlOutNewsMessage.Item item = new WxXmlOutNewsMessage.Item();
        item.setTitle("亲,终于等到您了。个性练习提分数，加油！");
        item.setUrl("http://tfkclass.com/wxhelp.html");
        item.setDescription("点击查看大图");
        item.setPicUrl("http://tfkclass.com/wxhelp2.jpg");

        WxXmlOutNewsMessage msg = WxXmlOutNewsMessage.NEWS().fromUser(inMsg.getToUserName()).toUser(inMsg.getFromUserName()).addArticle(item).build();

        return msg;
//        return WxXmlOutTextMessage.TEXT().content("欢迎来我们的公众号！").fromUser(inMsg.getToUserName()).toUser(inMsg.getFromUserName()).build();
    }
}
