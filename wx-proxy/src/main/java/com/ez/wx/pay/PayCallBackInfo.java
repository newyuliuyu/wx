package com.ez.wx.pay;

import com.ez.common.wx.xml.XStreamCDataConverter;
import com.ez.common.wx.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
public class PayCallBackInfo {

    /**
     *返回状态码
     */
    @XStreamAlias("return_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String returnCode;
    /**
     *返回信息
     */
    @XStreamAlias("return_msg")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String returnMsg;
    /**
     *公众账号ID
     */
    @XStreamAlias("appid")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String appid;
    /**
     *商户号
     */
    @XStreamAlias("mch_id")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mchid;
    /**
     *设备号
     */
    @XStreamAlias("device_info")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String deviceInfo;
    /**
     *随机字符串
     */
    @XStreamAlias("nonce_str")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String nonceStr;
    /**
     *签名
     */
    @XStreamAlias("sign")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String sign;
    /**
     *签名类型
     */
    @XStreamAlias("sign_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String signType;
    /**
     *业务结果
     */
    @XStreamAlias("result_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String resultCode;
    /**
     *错误代码
     */
    @XStreamAlias("err_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errCode;
    /**
     *错误代码描述
     */
    @XStreamAlias("err_code_des")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errCodeDes;
    /**
     *用户标识
     */
    @XStreamAlias("openid")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String openid;
    /**
     *用户是否关注公众账号，Y-关注，N-未关注
     */
    @XStreamAlias("is_subscribe")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String isSubscribe;
    /**
     *交易类型 JSAPI、NATIVE、APP
     */
    @XStreamAlias("trade_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String tradeType;
    /**
     *付款银行
     */
    @XStreamAlias("bank_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String bankType;
    /**
     *订单总金额，单位为分
     */
    @XStreamAlias("total_fee")
    @XStreamConverter(value = IntConverter.class)
    private int totalFee;
    /**
     *应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @XStreamAlias("settlement_total_fee")
    @XStreamConverter(value = IntConverter.class)
    private int settlementTotalFee;
    /**
     *货币种类
     */
    @XStreamAlias("fee_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String feeType;
    /**
     *现金支付金额
     */
    @XStreamAlias("cash_fee")
    @XStreamConverter(value = IntConverter.class)
    private int cashFee;
    /**
     *现金支付货币类型
     */
    @XStreamAlias("cash_fee_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String cashFeeType;
    /**
     *总代金券金额
     * 代金券金额<=订单金额，订单金额-代金券金额=现金支付金额
     */
    @XStreamAlias("coupon_fee")
    @XStreamConverter(value = IntConverter.class)
    private int couponFee;
    /**
     *代金券使用数量
     */
    @XStreamAlias("coupon_count")
    @XStreamConverter(value = IntConverter.class)
    private int couponCount;
//    /**
//     *代金券类型
//     * CASH--充值代金券
//     * NO_CASH---非充值代金券
//     * 并且订单使用了免充值券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
//     */
//    @XStreamAlias("coupon_type_$n")
//    @XStreamConverter(value = XStreamCDataConverter.class)
//    private String couponType_$n;
//    /**
//     *代金券ID,$n为下标，从0开始编号
//     */
//    @XStreamAlias("coupon_id_$n")
//    @XStreamConverter(value = XStreamCDataConverter.class)
//    private String couponId_$n;
//    /**
//     *单个代金券支付金额,$n为下标，从0开始编号
//     */
//    @XStreamAlias("coupon_fee_$n")
//    @XStreamConverter(value = XStreamCDataConverter.class)
//    private String couponFee_$n;
    /**
     *微信支付订单号
     */
    @XStreamAlias("transaction_id")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String transactionId;
    /**
     *商户订单号
     */
    @XStreamAlias("out_trade_no")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String outTradeNo;
    /**
     *商家数据包，原样返回
     */
    @XStreamAlias("attach")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String attach;
    /**
     *支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
     */
    @XStreamAlias("time_end")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String timeEnd;


    public boolean isReturnCodeTrue() {
        return "SUCCESS".equalsIgnoreCase(returnCode);
    }

    public boolean isResultCodeTrue() {
        return "SUCCESS".equalsIgnoreCase(resultCode);
    }

    public boolean isValid() {
        return isReturnCodeTrue() && isResultCodeTrue();
    }

    public static PayCallBackInfo toPayCallBackInfo(String fromXml) {
        return XStreamTransformer.fromXml(PayCallBackInfo.class, fromXml);
    }
}
