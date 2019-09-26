package com.ez.business.service;

import com.ez.business.bean.WxBoundStudent;

/**
 * ClassName: WxBoundStudent <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-14 下午4:35 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface WxBoundStudentService {
    /**
     * @param uukey
     * @param code  可以代码身份证号 手机号码 学生学号
     * @return
     */
    String fetchWxOpenId(String uukey, String code);

    void boundStudent(WxBoundStudent wxBoundStudent);

    void udpateBoundStudent(WxBoundStudent wxBoundStudent);

    WxBoundStudent getWxBoundStudent(String wxopenid);

    void addStudentInfo(String wxopenid);
}
