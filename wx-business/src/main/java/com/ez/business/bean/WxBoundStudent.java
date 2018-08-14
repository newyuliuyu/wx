package com.ez.business.bean;

import lombok.*;

/**
 * ClassName: WxBoundStudent <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-14 下午4:32 <br/>
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
public class WxBoundStudent {
    private int id;
    private String wxopenid;
    private String name;
    private String schoolCode;
    private String schoolName;
    private String grade;
    private String clazzCode;
    private String clazzName;
    private String phone;
    private String idCardNumber;
    private int gender;
    private int entrySchoolYear;

    public String getUukey() {
        return SUUKeyHelper.getUUKey(this);
    }
}
