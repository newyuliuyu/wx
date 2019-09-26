package com.ez.wx.pay;

import org.junit.Test;

/**
 * ClassName: UnifiedOrderSendInfoTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-23 下午4:54 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class UnifiedOrderSendInfoTest {
    @Test
    public void test01() throws Exception {

        UnifiedOrderSendInfo info = UnifiedOrderSendInfo.builder()
                .appid("appid")
                .mchId("mchId")
                .nonceStr("nonceStr")
                .sign("sign")
                .body("body")
                .outTradeNo("outTradeNo")
                .totalFee(1)
                .spbillCreateIp("spbillCreateIp")
                .notifyUrl("notifyUrl")
                .tradeType("JSAPI")
                .openid("openid")
                .build();

        info.valid();

        String xml = info.toXml();

        System.out.println(xml);
        System.out.println("运行完毕");
    }

}