package com.ez.wx;

import com.ez.common.spring.SpringContextUtil;
import com.ez.common.util.IdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: FetchIdGeneratorTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 上午10:20 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FetchIdGeneratorTest {

    @Test
    public void fetch() throws Exception{
        IdGenerator idGenerator = SpringContextUtil.getBean("idGenerator");
        System.out.println();
    }
}
