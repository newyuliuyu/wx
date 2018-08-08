package com.ez.wx.bean;

import com.ez.common.wx.WxHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

/**
 * ClassName: WxRegister <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-26 下午4:47 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
@ToString
public class WxRegister {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;
    private boolean isOk;

    public static WxRegister create(Supplier<WxRegister> wxRegisterSupplier, HttpServletRequest request) {
        WxRegister wxRegister = wxRegisterSupplier.get();
        wxRegister.setSignature(request.getParameter("signature"));
        wxRegister.setTimestamp(request.getParameter("timestamp"));
        wxRegister.setNonce(request.getParameter("nonce"));
        wxRegister.setEchostr(request.getParameter("echostr"));
        return wxRegister;
    }


    public WxRegister validate() {
        if (StringUtils.isEmpty(signature)
                || StringUtils.isEmpty(timestamp)
                || StringUtils.isEmpty(nonce)) {
            isOk = false;
        } else {
            isOk = WxHelper.checkSignature(signature, timestamp, nonce);
        }
        return this;
    }

    public String getResult() {
        return isOk ? echostr : "";
    }
}
