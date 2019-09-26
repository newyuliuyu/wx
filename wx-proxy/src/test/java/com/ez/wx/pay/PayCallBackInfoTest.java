package com.ez.wx.pay;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: PayCallBackInfoTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-23 下午5:31 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class PayCallBackInfoTest {
    @Test
    public void test01() throws Exception {

        String xml = "<xml>\n" +
                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>" +
                "  <attach><![CDATA[支付测试]]></attach>" +
                "  <bank_type><![CDATA[CFT]]></bank_type>" +
                "  <fee_type><![CDATA[CNY]]></fee_type>" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>" +
                "  <mch_id><![CDATA[10000100]]></mch_id>" +
                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>" +
                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>" +
                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>" +
                "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>" +
                "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>" +
                "  <time_end><![CDATA[20140903131540]]></time_end>" +
                "  <total_fee>1</total_fee>" +
                "<coupon_fee_0><![CDATA[10]]></coupon_fee_0>" +
                "<coupon_count><![CDATA[1]]></coupon_count>" +
                "<coupon_type><![CDATA[CASH]]></coupon_type>" +
                "<coupon_id><![CDATA[10000]]></coupon_id> " +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>" +
                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>" +
                "</xml>";



        XStream xStream = new XStream();
        xStream.registerConverter(new MapEntryConverter());
        xStream.alias("xml", Map.class);
        Map<String,String> o = (Map<String,String>)xStream.fromXML(xml);
        System.out.println("程序运行完毕");
    }




    @Test
    public void test02() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "chris");
        map.put("island", "faranga");

// convert to XML
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("map", java.util.Map.class);
        String xml = xStream.toXML(map);

        System.out.println(xml);

        System.out.println("程序运行完毕");
    }
}