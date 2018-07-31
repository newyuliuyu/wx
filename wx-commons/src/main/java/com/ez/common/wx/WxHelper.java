package com.ez.common.wx;

import com.ez.common.crypto.SHA2;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * ClassName: WxHelper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-1 下午4:24 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxHelper {

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String token = WxConfig.getInstance().getToken();
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuffer content = new StringBuffer();
        for (String a : arr) {
            content.append(a);
        }
//        String newSignature = DigestUtils.shaHex(content.toString());
        String newSignature = SHA2.encode(content.toString());
        return newSignature.equals(signature);
    }
}
