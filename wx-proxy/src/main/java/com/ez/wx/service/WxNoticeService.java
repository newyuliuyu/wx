package com.ez.wx.service;


import com.ez.business.bean.wx.WxNoticeData;

/**
 * ClassName: WxNoticeService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 上午10:44 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface WxNoticeService {

    void notice(WxNoticeData data);
    void notice2(WxNoticeData data);
}
