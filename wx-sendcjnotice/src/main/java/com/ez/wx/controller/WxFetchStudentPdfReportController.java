package com.ez.wx.controller;

import com.ez.business.bean.PdfReportInfo;
import com.ez.business.bean.SystemAttributeKey;
import com.ez.business.dao.StudentPdfReportDao;
import com.ez.business.dao.WxBoundStudentDao;
import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.thread.ThreadExecutor;
import com.ez.wx.service.SystemAttributeMgr;
import com.ez.wx.service.process.PublishStudentPdfReportStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * ClassName: WxFetchStudentPdfReport <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-11-5 上午10:27 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@Controller
public class WxFetchStudentPdfReportController {
    @Autowired
    private StudentPdfReportDao studentPdfReportDao;
    @Autowired
    private WxBoundStudentDao wxBoundStudentDao;


    @RequestMapping(value = "/fetch/student/pdf/report/{wxopenid}")
    public ModelAndView fetchStudentinfoWithWxopendid(@PathVariable String wxopenid,
                                                      HttpServletRequest request,
                                                      HttpServletResponse responese) throws Exception {
        PublishStudentPdfReportStarter starter = new PublishStudentPdfReportStarter(wxopenid);
        ThreadExecutor.getInstance().getExecutorService().submit(starter);
        return ModelAndViewFactory.instance().with("ok","ok").build();
    }

    @RequestMapping(value = "/pdf/{onlykey}")
    public ModelAndView pdf(@PathVariable String onlykey,
                            HttpServletRequest request,
                            HttpServletResponse responese) throws Exception {




        String uukey = request.getParameter("uukey");
        uukey=uukey==null?"":uukey;
        String code = request.getParameter("code");
        code=code==null?"":code;
        log.debug("uukey[{}],code[{}]",uukey,code);
        String url="";
        if (!"demo123".equalsIgnoreCase(onlykey) && !wxBoundStudentDao.isPay(uukey,code)) {
            String noPermissionUrl = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.noPermissionUrl);
            url = noPermissionUrl;
        }else{
            String root = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.studentPdfReportUrlPrefix);
            PdfReportInfo pdfReportInfo = studentPdfReportDao.getPdfReportInfo(onlykey);
            url = root + pdfReportInfo.getAddr();
            url = root + URLEncoder.encode(pdfReportInfo.getAddr(), "UTF-8");
        }



        return ModelAndViewFactory.instance("redirect:" + url).build();
    }
}
