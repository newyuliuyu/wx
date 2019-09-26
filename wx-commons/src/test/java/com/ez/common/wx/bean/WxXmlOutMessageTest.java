package com.ez.common.wx.bean;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ClassName: WxXmlOutMessageTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-23 下午4:13 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxXmlOutMessageTest {

    @Test
    public void wxXmlOutMessage() throws Exception{
        WxXmlOutImageMessage data = new WxXmlOutImageMessage();
        data.setToUserName("toUserName");

        System.out.println(data.toXml());
    }

}