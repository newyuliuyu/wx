package com.ez.business.service.impl;

import com.ez.business.bean.WxBoundStudent;
import com.ez.business.dao.WxBoundStudentDao;
import com.ez.business.service.WxBoundStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: WxBoundStudentServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-14 下午4:37 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service("WxBoundStudentService")
public class WxBoundStudentServiceImpl implements WxBoundStudentService {


    @Autowired
    private WxBoundStudentDao wxBoundStudentDao;

    @Override
    public String fetchWxOpenId(String uukey, String code) {
        return wxBoundStudentDao.fetchWxOpenId(uukey, code);
    }

    @Override
    public void boundStudent(WxBoundStudent wxBoundStudent) {
        wxBoundStudentDao.boundStudent(wxBoundStudent);
    }

    @Override
    public void udpateBoundStudent(WxBoundStudent wxBoundStudent) {
        wxBoundStudentDao.updateBoundStudent(wxBoundStudent);
    }

    @Override
    public WxBoundStudent getWxBoundStudent(String wxopenid) {
        return wxBoundStudentDao.getWxBoundStudent(wxopenid);
    }

    @Override
    public void addStudentInfo(String wxopenid) {
        WxBoundStudent wxBoundStudent = wxBoundStudentDao.getWxBoundStudent(wxopenid);
        wxBoundStudentDao.addStudentInfo(wxBoundStudent);
    }
}
