package com.ez.business.dao;

import com.ez.business.bean.WxPayOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: WxPayOrderDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-26 下午2:22 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface WxPayOrderDao {

    int create(@Param("wxPayOrder") WxPayOrder wxPayOrder);

    int updatePayOfWxPayOrder(@Param("wxPayOrder") WxPayOrder wxPayOrder);

}
