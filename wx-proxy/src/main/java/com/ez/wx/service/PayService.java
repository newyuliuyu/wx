package com.ez.wx.service;

import com.ez.wx.pay.UnifiedOrderAcceptInfo;
import com.ez.wx.pay.UnifiedOrderSendInfo;

/**
 * ClassName: PayService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-24 下午1:55 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface PayService {
    UnifiedOrderAcceptInfo getUnifiedOrderAcceptInfo(UnifiedOrderSendInfo sendInfo);
}
