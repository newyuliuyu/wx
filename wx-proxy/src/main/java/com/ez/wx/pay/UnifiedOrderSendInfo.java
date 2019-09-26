package com.ez.wx.pay;

import com.ez.common.wx.xml.XStreamCDataConverter;
import com.ez.common.wx.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import lombok.*;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

/**
 * ClassName: UnifiedOrderSendInfo <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-23 下午2:59 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@XStreamAlias("xml")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnifiedOrderSendInfo {

    /**
     * 公众号ID Y
     */
    @XStreamAlias("appid")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String appid;
    /**
     * 微信支付商户号 Y
     */
    @XStreamAlias("mch_id")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mchId;
    /**
     * 设备号 自定义参数 微信公众号和网页传WEB N
     */
    @XStreamAlias("device_info")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String deviceInfo;
    /**
     * 随机字符串，长度要求在32位以内 Y
     */
    @XStreamAlias("nonce_str")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String nonceStr;
    /**
     * 通过签名算法计算得出的签名值 Y
     */
    @XStreamAlias("sign")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String sign;
    /**
     * MD5	签名类型，默认为MD5，支持HMAC-SHA256和MD5 N
     */
    @XStreamAlias("sign_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String signType;
    /**
     * 商品简单描述 Y
     */
    @XStreamAlias("body")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String body;
    /**
     * 商品详细描述 N
     */
    @XStreamAlias("detail")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String detail;
    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用 N
     */
    @XStreamAlias("attach")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String attach;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一 Y
     */
    @XStreamAlias("out_trade_no")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String outTradeNo;
    /**
     * 货币类型 默认人民币：CNY N
     */
    @XStreamAlias("fee_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String feeType;
    /**
     * 订单总金额，单位为分 Y
     */
    @XStreamAlias("total_fee")
    @XStreamConverter(value = IntConverter.class)
    @Builder.Default
    private int totalFee = -1;
    /**
     * 用户的客户端IP Y
     */
    @XStreamAlias("spbill_create_ip")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String spbillCreateIp;
    /**
     * 订单生成时间 格式为yyyyMMddHHmmss N
     */
    @XStreamAlias("time_start")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String timeStart;
    /**
     * 订单失效时间 格式为yyyyMMddHHmmss N
     */
    @XStreamAlias("time_expire")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String timeExpire;
    /**
     * 使用代金券或立减优惠功能时需要的参数 N
     */
    @XStreamAlias("goods_tag")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String goodsTag;
    /**
     * 异步接收微信支付结果通知的回调地址 Y
     */
    @XStreamAlias("notify_url")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String notifyUrl;
    /**
     * 交易类型 JSAPI -JSAPI支付 NATIVE -Native支付 APP -APP支付 Y
     */
    @XStreamAlias("trade_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String tradeType;
    /**
     * 商品ID NATIVE时，此参数必传 N
     */
    @XStreamAlias("product_id")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String productId;
    /**
     * 指定支付方式 N
     */
    @XStreamAlias("limit_pay")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String limitPay;
    /**
     * 用户标识 trade_type=JSAPI时（即JSAPI支付），此参数必传 N
     */
    @XStreamAlias("openid")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String openid;
    /**
     * 电子发票入口开放标识 N
     */
    @XStreamAlias("receipt")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String receipt;
    /**
     * 场景信息 N
     */
    @XStreamAlias("scene_info")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String sceneInfo;

    public UnifiedOrderSendInfo valid() {

        if (StringUtils.isEmpty(appid)) {
            throw new RuntimeException("公众号ID不能为空");
        }

        if (StringUtils.isEmpty(mchId)) {
            throw new RuntimeException("微信支付商户号不能为空");
        }
        if (StringUtils.isEmpty(nonceStr) || nonceStr.length() > 32) {
            throw new RuntimeException("随机字符串不能为空,并且长度不能大于32");
        }
        if (StringUtils.isEmpty(sign) || sign.length() > 32) {
            throw new RuntimeException("签名字符串不能为空,并且长度不能大于32");
        }
        if (StringUtils.isEmpty(body) || body.length() > 128) {
            throw new RuntimeException("商品简单描述不能为空,并且长度不能大于128");
        }
        if (StringUtils.isEmpty(outTradeNo) || outTradeNo.length() > 128) {
            throw new RuntimeException(MessageFormat.format("商户系统内部订单号不能为空," +
                    "并且长度不能大于128.只能是数字、大小写字母_-|* 且在同一个商户号下唯一;[{0}]", outTradeNo));
        }
        if (totalFee <= 0) {
            throw new RuntimeException("订单总金额必须大于0");
        }
        if (StringUtils.isEmpty(spbillCreateIp)) {
            throw new RuntimeException("终端IP不能为空");
        }
        if (StringUtils.isEmpty(notifyUrl)) {
            throw new RuntimeException("微信回调地址不能为空");
        }
        if (StringUtils.isEmpty(tradeType)) {
            throw new RuntimeException("交易类型不能为空");
        }
        if ("NATIVE".equals(tradeType) && StringUtils.isEmpty(tradeType)) {
            throw new RuntimeException("交易类型NATIVE时，商品ID不能为空");
        }
        if ("JSAPI".equals(tradeType) && StringUtils.isEmpty(tradeType)) {
            throw new RuntimeException("用户的微信ID号不能为空");
        }

        return this;
    }

    public String toXml() {
        return XStreamTransformer.toXml((Class) this.getClass(), this);
    }

}
