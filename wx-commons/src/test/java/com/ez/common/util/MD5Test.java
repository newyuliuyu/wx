package com.ez.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ClassName: MD5Test <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 下午2:58 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class MD5Test {

    @Test
    public void test() throws Exception{
        String md5 = MD5.toMD5("123");
        System.out.println(md5);
    }

}