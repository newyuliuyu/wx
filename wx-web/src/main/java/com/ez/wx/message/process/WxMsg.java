package com.ez.wx.message.process;

import java.lang.annotation.*;

/**
 * ClassName: WxMsg <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 上午9:39 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WxMsg {

    String value();

    Class validateClass() default NoClass.class;

    String validateInvokeMethod() default "match";
}
