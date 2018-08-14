package com.ez.wx.config;

import com.ez.business.bean.SystemAttribute;
import com.ez.business.bean.SystemAttributeKey;
import com.ez.common.spring.SpringContextUtil;
import com.ez.common.util.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: CommonConfig <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 上午10:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Configuration
@PropertySource("classpath:ezconfig.properties")
public class CommonConfig {
    @Value("${wx.proxy.url}")
    private String wxProxURL;
    @Value("${student.report.url}")
    private String studentReportURL;
    @Value("${sys.version:1.0.1}")
    private String sysVersion = "1.0.0";

    @Bean
    public SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }

    @Bean("sysVersion")
    public SystemAttribute sysVersion() {
        SystemAttribute attribute = new SystemAttribute();
        attribute.setAttrName("sys.version");
        attribute.setAliasName("版本号");
        attribute.setValue(sysVersion);
        return attribute;
    }

    @Bean("wxProxURL")
    public SystemAttribute wxProxURL() {
        SystemAttribute attribute = new SystemAttribute();
        attribute.setAttrName(SystemAttributeKey.wxProxyURL.toString());
        attribute.setAliasName("微信代理服务器地址");
        attribute.setValue(this.wxProxURL);
        return attribute;
    }

    @Bean("studentReportURL")
    public SystemAttribute studentReportURL() {
        SystemAttribute attribute = new SystemAttribute();
        attribute.setAttrName(SystemAttributeKey.studentReportURl.toString());
        attribute.setAliasName("学生个人报告地址");
        attribute.setValue(this.studentReportURL);
        return attribute;
    }

}
