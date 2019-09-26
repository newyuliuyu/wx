package com.ez.wx.pay;

import org.junit.Test;

/**
 * ClassName: UnifiedOrderAcceptInfoTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-23 下午3:53 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class UnifiedOrderAcceptInfoTest {

    @Test
    public void test01() throws Exception {

        String xml="<xml>" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>" +
                "   <return_msg><![CDATA[OK]]></return_msg>" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>" +
                "   <mch_id><![CDATA[10000100]]></mch_id>" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>" +
                "   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>" +
                "</xml>";
        UnifiedOrderAcceptInfo info = UnifiedOrderAcceptInfo.toUnifiedOrderAcceptInfo(xml);

        System.out.println("程序运行完毕");
    }
    @Test
    public void test02() throws Exception {

        String xml="<xml>" +
                "   <return_code>SUCCESS</return_code>" +
                "   <return_msg><![CDATA[OK]]></return_msg>" +
                "   <appid>wx2421b1c4370ec43b</appid>" +
                "   <mch_id><![CDATA[10000100]]></mch_id>" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>" +
                "   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>" +
                "</xml>";

        xml="<xml>" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>" +
                "   <return_msg><![CDATA[OK]]></return_msg>" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>" +
                "   <mch_id><![CDATA[10000100]]></mch_id>" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>" +
                "   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>" +
                "</xml>";
        UnifiedOrderAcceptInfo info = UnifiedOrderAcceptInfo.toUnifiedOrderAcceptInfo(xml);

        System.out.println("程序运行完毕");
    }

}