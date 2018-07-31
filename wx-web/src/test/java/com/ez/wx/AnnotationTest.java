package com.ez.wx;

import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 * ClassName: AnnotationTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 上午9:40 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class AnnotationTest {

    @Test
    public void test01() throws Exception{
        Annotation[] annotations = WxMsgAnnotation.class.getAnnotations();

        System.out.println();
    }
}
