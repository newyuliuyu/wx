package com.ez.wx.message.process;

import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.bean.WxXmlOutMessage;
import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * ClassName: ProcessInvoker <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午3:49 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Setter
@Getter
@AllArgsConstructor
public class MsgProcessInvoker {
    private MessageProcessDefine define;
    private Object processObj;

    public WxXmlOutMessage invoke(WxXmlMessage wxXmlMessage) {
        WxXmlOutMessage outMessage = null;
        try {
            Method method = processObj.getClass().getMethod(define.getProcessInvokeMethod(), WxXmlMessage.class);
            outMessage = (WxXmlOutMessage) method.invoke(processObj, wxXmlMessage);
        } catch (Exception e) {
            Throwables.propagate(e);
        }
        return outMessage;
    }

}
