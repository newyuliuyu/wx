package com.ez.wx.pay;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ClassName: IWXPayDomainImplTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-24 下午3:59 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class IWXPayDomainImplTest {

    @Test
    public void report() throws Exception {
        IWXPayDomainImpl impl = new IWXPayDomainImpl();
        impl.report("aaa",1L,null);
    }
}