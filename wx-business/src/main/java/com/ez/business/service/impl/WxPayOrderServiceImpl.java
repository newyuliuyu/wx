package com.ez.business.service.impl;

import com.ez.business.bean.WxPayOrder;
import com.ez.business.dao.WxPayOrderDao;
import com.ez.business.service.WxPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: WxPayOrderServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-26 下午2:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service("WxPayOrderServiceImpl")
public class WxPayOrderServiceImpl implements WxPayOrderService {

    @Autowired
    private WxPayOrderDao wxPayOrderDao;

    @Override
    public int create(WxPayOrder wxPayOrder) {
        return wxPayOrderDao.create(wxPayOrder);
    }

    @Override
    public int updatePayOfWxPayOrder(WxPayOrder wxPayOrder) {
        return wxPayOrderDao.updatePayOfWxPayOrder(wxPayOrder);
    }
}
