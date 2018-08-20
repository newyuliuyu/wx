package com.ez.business.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: Student <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-20 下午3:36 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
@ToString
public class Student {
    private String zkzh;
    private String code;
    private String name;
    private int wl;
    private String schoolCode;
    private String schoolName;
    private String clazzCode;
    private String clazzName;
}
