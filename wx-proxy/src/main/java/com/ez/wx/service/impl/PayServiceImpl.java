package com.ez.wx.service.impl;

import com.ez.common.wx.WxConfig;
import com.ez.wx.pay.*;
import com.ez.wx.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: PayServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-24 下午1:56 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service("wxPayServiceImpl")
@Slf4j
public class PayServiceImpl implements PayService {


    @Override
    public UnifiedOrderAcceptInfo getUnifiedOrderAcceptInfo(UnifiedOrderSendInfo sendInfo) {
        WxConfig config = WxConfig.getInstance();
        String sign = getSignature(sendInfo.toXml(), config.getApiKey());
        sendInfo.setSign(sign);
        sendInfo.valid();
        String resultText="";
        try {
            WXPayRequest wxPayRequest = new WXPayRequest(WXPayConfigFactory.factory(config));
            resultText = wxPayRequest.requestWithoutCert(WXPayConstants.UNIFIEDORDER_URL_SUFFIX,"",sendInfo.toXml(),false);
        } catch (Exception e) {
            log.error("微信生成统一订单出错", e);
            throw new RuntimeException("微信生成统一订单出错", e);
        }
//        resultText="<xml>" +
//                "   <return_code><![CDATA[SUCCESS]]></return_code>" +
//                "   <return_msg><![CDATA[OK]]></return_msg>" +
//                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>" +
//                "   <mch_id><![CDATA[10000100]]></mch_id>" +
//                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>" +
//                "   <openid><![CDATA[oFbfZwjzUU_z0DWfLn2b-LglL-QY]]></openid>" +
//                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>" +
//                "   <result_code><![CDATA[SUCCESS]]></result_code>" +
//                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" +
//                "   <trade_type><![CDATA[JSAPI]]></trade_type>" +
//                "</xml>";
        UnifiedOrderAcceptInfo result  = UnifiedOrderAcceptInfo.toUnifiedOrderAcceptInfo(resultText);
        return result;
    }

    private String getSignature(String xml, String apiKey) {
        try {
            String sign = WXPayUtil.generateSignature(xml, apiKey, WXPayConstants.SignType.MD5);
            return sign;
        } catch (Exception e) {
            log.error("生成签名出错了", e);
            throw new RuntimeException("生成签名出错了", e);
        }
    }
}
