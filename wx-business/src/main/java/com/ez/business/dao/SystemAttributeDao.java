/**
 * Project Name:easydata.web
 * File Name:SystemAttributeDao.java
 * Package Name:com.ez.data.controller
 * Date:2017年4月6日上午9:13:56
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.ez.business.dao;


import com.ez.business.bean.SystemAttribute;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: SystemAttributeDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年4月6日 上午9:13:56 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface SystemAttributeDao {

    List<SystemAttribute> list();
}
