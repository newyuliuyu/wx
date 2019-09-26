package com.ez.wx.pay;

import com.ez.common.wx.WxConfig;

/**
 * ClassName: WXPayConfigFactory <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-24 下午3:49 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WXPayConfigFactory {
    public static WXPayConfig factory(WxConfig wxConfig) {
        return new WXPayConfigImpl(wxConfig.getAppId(), wxConfig.getMchId(), wxConfig.getApiKey());
    }
}
