package com.ez.business.dao;

import com.ez.business.bean.WxBoundStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: WxBoundStudentDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-14 下午4:44 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface WxBoundStudentDao {
    String fetchWxOpenId(@Param("uukey") String uukey,
                         @Param("code") String code);

    void boundStudent(@Param("wxBoundStudent") WxBoundStudent wxBoundStudent);

    void updateBoundStudent(@Param("wxBoundStudent") WxBoundStudent wxBoundStudent);

    WxBoundStudent getWxBoundStudent(@Param("wxopenid") String wxopenid);

    int addStudentInfo(@Param("wxBoundStudent") WxBoundStudent wxBoundStudent);
}
