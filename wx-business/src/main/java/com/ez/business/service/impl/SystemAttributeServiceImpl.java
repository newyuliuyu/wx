package com.ez.business.service.impl;

import com.ez.business.bean.SystemAttribute;
import com.ez.business.dao.SystemAttributeDao;
import com.ez.business.service.SystemAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: SystemAttributeServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 上午10:13 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
public class SystemAttributeServiceImpl implements SystemAttributeService {

    @Autowired
    private SystemAttributeDao dao;

    @Override
    public List<SystemAttribute> list() {
        return dao.list();
    }
}
