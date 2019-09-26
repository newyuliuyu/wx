package com.ez.business.bean;

import lombok.*;

/**
 * ClassName: WxPayOrder <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-26 下午2:15 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WxPayOrder {
    private int id;
    private String orderNum;
    private String wxopenid;
    private int moeny;
    private long orderTimestamp;
    private boolean pay;
}
