package com.ez.wx.pay;

import com.ez.common.wx.xml.XStreamCDataConverter;
import com.ez.common.wx.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
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
public class UnifiedOrderAcceptInfo {
    /**
     * 返回状态码
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XStreamAlias("return_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String returnCode;
    /**
     * 当return_code为FAIL时返回信息为错误原因
     */
    @XStreamAlias("return_msg")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String returnMsg;
    /**
     * 公众账号ID
     */
    @XStreamAlias("appid")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String appid;
    /**
     * 商户号
     */
    @XStreamAlias("mch_id")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mchid;
    /**
     * 设备号
     */
    @XStreamAlias("device_info")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String deviceInfo;
    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String nonceStr;
    /**
     * 签名
     */
    @XStreamAlias("sign")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String sign;
    /**
     * 业务结果
     */
    @XStreamAlias("result_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String resultCode;
    /**
     * 错误代码
     */
    @XStreamAlias("err_code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errCode;
    /**
     * 错误代码描述
     */
    @XStreamAlias("err_code_des")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errCodeDes;
    /**
     * 交易类型
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     */
    @XStreamAlias("trade_type")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String tradeType;
    /**
     * 预支付交易会话标识
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @XStreamAlias("prepay_id")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String prepayId;
    /**
     * 二维码链接
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     * 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
     */
    @XStreamAlias("code_url")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String codeUrl;

    public boolean isReturnCodeTrue() {
        return "SUCCESS".equalsIgnoreCase(returnCode);
    }

    public boolean isResultCodeTrue() {
        return "SUCCESS".equalsIgnoreCase(resultCode);
    }

    public boolean isValid() {
        return isReturnCodeTrue() && isResultCodeTrue();
    }

    public static UnifiedOrderAcceptInfo toUnifiedOrderAcceptInfo(String fromXml) {
        return XStreamTransformer.fromXml(UnifiedOrderAcceptInfo.class, fromXml);
    }

}
