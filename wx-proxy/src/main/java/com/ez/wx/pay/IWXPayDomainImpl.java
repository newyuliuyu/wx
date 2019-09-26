package com.ez.wx.pay;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: IWXPayDomainImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-24 下午3:44 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class IWXPayDomainImpl implements IWXPayDomain {
    @Override
    public void report(String domain, long elapsedTimeMillis, Exception ex) {
        log.info("域名[{}]运行时间[{}],异常[{}]", domain, elapsedTimeMillis, ex);
    }

    @Override
    public DomainInfo getDomain(WXPayConfig config) {
        DomainInfo info = new DomainInfo(WXPayConstants.DOMAIN_API, true);
        return info;
    }
}
