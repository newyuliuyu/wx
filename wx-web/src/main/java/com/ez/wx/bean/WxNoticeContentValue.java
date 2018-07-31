package com.ez.wx.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: WxData <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 上午10:52 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Setter
@Getter
@AllArgsConstructor
public class WxNoticeContentValue {
    private String value;
    private String color;
}
