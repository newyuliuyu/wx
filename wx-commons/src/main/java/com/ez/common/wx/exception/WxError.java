package com.ez.common.wx.exception;


import com.ez.common.json.Json2;

/**
 * 微信错误码说明 http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html
 *
 * @author antgan
 */
public class WxError {

    private int errcode;

    private String errmsg;

    public WxError() {
        // TODO Auto-generated constructor stub
    }

    public WxError(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }


    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }


    public String getErrmsg() {
        return errmsg;
    }


    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }


    public static WxError fromJson(String json) {
        WxError wxError = Json2.fromJson(json, WxError.class);
        return wxError;
    }


    @Override
    public String toString() {
        return "WxError [errcode=" + errcode + ", errmsg=" + errmsg + "]";
    }


}
