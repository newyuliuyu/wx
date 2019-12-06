package com.ez.wx.message.process;

import com.ez.common.wx.bean.WxXmlOutNewsMessage;
import org.junit.Test;

/**
 * ClassName: WxXmlOutNewsMessageTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-11-20 上午9:53 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxXmlOutNewsMessageTest {

    @Test
    public void test01() throws Exception{
        WxXmlOutNewsMessage.Item item  = new WxXmlOutNewsMessage.Item();
        item.setTitle("test");
        item.setPicUrl("http://tfkclass.com/wxhelp.jpg");

        WxXmlOutNewsMessage msg = WxXmlOutNewsMessage
                .NEWS()
                .fromUser("ddd")
                .toUser("dddd")
                .addArticle(item).build();
        System.out.println(msg.toXml());
    }
}
