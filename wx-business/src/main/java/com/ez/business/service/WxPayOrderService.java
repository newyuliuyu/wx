package com.ez.business.service;

import com.ez.business.bean.WxPayOrder;

/**
 * ClassName: WxPayOrderService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-26 下午2:19 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface WxPayOrderService {

    int create(WxPayOrder wxPayOrder);

    int updatePayOfWxPayOrder(WxPayOrder wxPayOrder);
}
