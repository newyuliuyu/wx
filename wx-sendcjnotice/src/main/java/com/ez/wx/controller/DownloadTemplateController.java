package com.ez.wx.controller;

import com.ez.common.download.DownloadTemplate;
import com.ez.common.spring.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * ClassName: DownloadTemplateController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 上午10:47 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Controller
@Slf4j
public class DownloadTemplateController {

    @RequestMapping("/download/{tmplateId}")
    public void downlaod(@PathVariable String tmplateId,
                         HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        DownloadTemplate tmplate = SpringContextUtil.getBean(tmplateId);
        String fileName = tmplate.name();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1") + ".xls");
        Map<String, String[]> params = request.getParameterMap();
        OutputStream os = response.getOutputStream();
        tmplate.tmplate(os, params);
        os.close();
    }
}
