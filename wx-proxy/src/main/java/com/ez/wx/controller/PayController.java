package com.ez.wx.controller;

import com.alibaba.fastjson.JSONPath;
import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpPostBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.wx.WxConfig;
import com.ez.wx.pay.*;
import com.ez.wx.service.PayService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ClassName: PayController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-23 下午1:49 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {


    @Autowired
    private PayService payService;


    @RequestMapping(value = "/payinfo", method = RequestMethod.POST)
    public ModelAndView menu(@RequestBody UnifiedOrderSendInfo sendInfo,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        log.debug("entry payinto .......");
        WxConfig config = WxConfig.getInstance();
        createWxPayOrderAndSetOutTradeNo(config, sendInfo);
        setSendInfoData(config, sendInfo, request);
        log.debug(sendInfo.toString());
        UnifiedOrderAcceptInfo acceptInfo = payService.getUnifiedOrderAcceptInfo(sendInfo);
        Map<String, String> data = createResponseData(config, acceptInfo);
        return ModelAndViewFactory.instance("").with("info", data).build();
    }

    private Map<String, String> createResponseData(WxConfig config, UnifiedOrderAcceptInfo acceptInfo) throws Exception {
        Map<String, String> data = Maps.newHashMap();
        data.put("appId", acceptInfo.getAppid());
        data.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
        data.put("nonceStr", WXPayUtil.generateNonceStr());
        data.put("package", "prepay_id=" + acceptInfo.getPrepayId());
        data.put("signType", "MD5");
        String sign = WXPayUtil.generateSignature(data, config.getApiKey(), WXPayConstants.SignType.MD5);
        data.put("paySign", sign);
        return data;
    }

    private void setSendInfoData(WxConfig config, UnifiedOrderSendInfo sendInfo, HttpServletRequest request) {
        String ip = IpUtils.getIpAddr(request);
        sendInfo.setSpbillCreateIp(ip);
        sendInfo.setAppid(config.getAppId());
        sendInfo.setMchId(config.getMchId());
        sendInfo.setNonceStr(WXPayUtil.generateNonceStr());
        sendInfo.setNotifyUrl(config.getNotifyUrl());
        sendInfo.setTradeType("JSAPI");
    }

    private void createWxPayOrderAndSetOutTradeNo(WxConfig config, UnifiedOrderSendInfo sendInfo) throws Exception {
        String url = config.getWxsendcj() + "/pay/order/create";
        log.debug(url);
        Map<String, String> data = Maps.newHashMap();
        data.put("wxopenid", sendInfo.getOpenid());
        data.put("moeny", sendInfo.getTotalFee() + "");
        HttpPost post = HttpPostBuilder.create(url).buildJson(Json2.toJson(data));
        HCUtils hcUtils = HCUtils.createDefault();
        try {
            RequestResult result = hcUtils.exec(post);
            String json = result.getContent();
            log.debug(json);
            Object object = JSONPath.read(json, "$.wxPayOrder.orderNum");
            if (object == null) {
                throw new RuntimeException("获取订单号的时候出错");
            }
            sendInfo.setOutTradeNo(object.toString());
        } finally {
            hcUtils.close();
        }
    }


    @RequestMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("entry callback....");
        String xml = IOUtils.toString(request.getInputStream(), "UTF-8");
        log.debug(xml);
        PayCallBackInfo payCallBackInfo = PayCallBackInfo.toPayCallBackInfo(xml);
        updatePayOfWxPayOrder(payCallBackInfo);

        StringBuilder resultText = new StringBuilder();
        resultText.append("<xml>");
        resultText.append("<return_code><![CDATA[SUCCESS]]></return_code>");
        resultText.append("<return_msg><![CDATA[OK]]></return_msg>");
        resultText.append("</xml>");
        response.getWriter().write(resultText.toString());
    }

    private void updatePayOfWxPayOrder(PayCallBackInfo payCallBackInfo) {
        WxConfig config = WxConfig.getInstance();
        String url = config.getWxsendcj() + "/pay/order/updatepay";
        log.debug(url);
        Map<String, Object> data = Maps.newHashMap();
        data.put("orderNum", payCallBackInfo.getOutTradeNo());
        data.put("wxopenid", payCallBackInfo.getOpenid());
        data.put("pay", true);
        HttpPost post = HttpPostBuilder.create(url).buildJson(Json2.toJson(data));
        HCUtils hcUtils = HCUtils.createDefault();
        try {
            RequestResult result = hcUtils.exec(post);
            log.debug(result.getContent());
        } finally {
            hcUtils.close();
        }
    }
}
