package com.ez.business;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.junit.Test;

/**
 * ClassName: ATest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 下午5:20 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class ATest {

    @Test
    public void kk() {
        @val String mm = "fdfdfd";
        @var String kk = "ddd";
        mm = "dfdf";
        kk = "22";
        try{
            Integer.parseInt("dddd000");
        }catch (Exception e){
            if(log.isErrorEnabled()){
            }
            log.error("*******************************");
            log.debug("ddddddddddddddddddddddddddddddddddd");
        }

    }

    private void test(@val String mm, @var String kk) {

    }
}
