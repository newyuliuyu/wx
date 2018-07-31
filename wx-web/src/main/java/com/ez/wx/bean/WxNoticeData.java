package com.ez.wx.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: WxNotice <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 上午10:49 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
public class WxNoticeData {
    private String touser;
    private String template_id;
    private String url;
    private Miniprogram miniprogram;
    private WxNoticeContent data;
}
