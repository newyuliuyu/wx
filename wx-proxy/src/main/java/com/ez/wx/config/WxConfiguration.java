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
//        String accesseToken="27_Elya3C_3GumdPXuVhgRKsFW6weLQO53EQ3zBiXZ1ETQIzcJHtqJ0StXRXq3mBDm3wM_iU0TTrrcU5FBM2FJvLtepjONiark56Cf31AR4S4MwBMpxWwL2gRjDVnEKENjACAYSB";
//        wxConfig.updateAccessToken(accesseToken,7200);
        return wxConfig;
    }
}
