package com.ez.common.wx.exception;

/**
 * ClassName: WxErrorException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-1 下午4:53 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxErrorException extends Exception {

    private static final long serialVersionUID = -6357149550353160810L;

    private WxError error;

    public WxErrorException(WxError error) {
        super(error.toString());
        this.error = error;
    }

    public WxErrorException(String msg){
        super(msg);
    }

    public WxError getError() {
        return error;
    }

}
