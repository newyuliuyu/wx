package com.ez.wx.config;

import com.ez.common.wx.WxConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: WxConfig <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午6:43 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Configuration
@PropertySource("classpath:wx.properties")
public class WxConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "wx")
    public WxConfig wxConfig() {
        WxConfig wxConfig = new WxConfig();
        String accesseToken="12_EVnZa99mKrY7ERAC6YwP4yuR-VfyQcdy1dfDQqBSzYf79V2BRGP-rOiNk6tbmPHa8_JpTUAbzH4SFasjbOONh3pf4vPag0430g-v9E0p3ixsUPv--cbJ69B9TsW3986-L8wydNNjQ2Sv-taCJOCcAEAAFT";
        wxConfig.updateAccessToken(accesseToken,7200);
        return wxConfig;
    }
}
