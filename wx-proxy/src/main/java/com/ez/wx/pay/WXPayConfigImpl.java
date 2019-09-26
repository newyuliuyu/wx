package com.ez.wx.pay;

import java.io.InputStream;

/**
 * ClassName: WXPayConfigImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-24 下午3:42 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WXPayConfigImpl extends WXPayConfig {

    private String appid;
    private String mchId;
    private String key;

    public WXPayConfigImpl(String appId, String mchId, String key) {
        this.appid = appId;
        this.mchId = mchId;
        this.key = key;
    }

    @Override
    String getAppID() {
        return appid;
    }

    @Override
    String getMchID() {
        return mchId;
    }

    @Override
    String getKey() {
        return key;
    }

    @Override
    InputStream getCertStream() {
        //目前不去获取正是内容
        return null;
    }

    @Override
    public boolean shouldAutoReport() {
        return false;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomainImpl();
    }
}
