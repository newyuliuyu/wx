package com.ez.wx.message.processor;

import com.ez.common.wx.bean.WxXmlMessage;

/**
 * ClassName: MatchMessage <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午5:18 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class MatchMessage {

    public boolean defaultTextMatch(WxXmlMessage inmsge) {
        System.out.println("=====defaultTextMatch");
        return true;
    }
    public boolean subscribeEvent(WxXmlMessage inmsge) {
        System.out.println("=====defaultTextMatch");
        return true;
    }
}
