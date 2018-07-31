package com.ez.wx.message.process;

import com.ez.common.wx.bean.WxXmlOutMessage;
import com.ez.common.wx.bean.WxXmlOutNewsMessage;
import org.junit.Test;

/**
 * ClassName: WxMessageHandlerMappingTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 上午11:27 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxMessageHandlerMappingTest {

    @Test
    public void test() throws Exception {
        WxMessageHandlerMapping mapping = new WxMessageHandlerMapping();
        mapping.setPackages("com.ez.wx.message");
        mapping.init();
        System.out.println();
    }

    @Test
    public void test2() throws Exception {
        Class myclass = WxXmlOutNewsMessage.class;
        boolean a = WxXmlOutMessage.class.isAssignableFrom(myclass);
        System.out.println();
    }
}