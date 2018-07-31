package com.ez.wx.message.process;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: MessageProcessDefine <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午1:59 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
public class MessageProcessDefine {
    private String msgType;
    private String event;
    private Class validateClass;
    private String validateInvokeMethod = "match";
    private Class processClass;
    private String processInvokeMethod = "process";
}
