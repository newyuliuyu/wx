package com.ez.wx.service.impl;

import com.ez.common.json.Json2;
import com.ez.wx.bean.WxNoticeContent;
import com.ez.wx.bean.WxNoticeContentValue;
import com.ez.wx.bean.WxNoticeData;
import com.ez.wx.service.WxNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: WxCjNoticeServiceImplTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 上午10:57 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WxCjNoticeServiceImplTest {

    @Autowired(required = false)
    @Qualifier("wxCjNoticeService")
    private WxNoticeService service;

    @Test
    public void notice() throws Exception {
        WxNoticeContent content = new WxNoticeContent();
        content.put("first", new WxNoticeContentValue("XX同学本次考试的成绩", "#173177"));
        content.put("keyword1", new WxNoticeContentValue("2017-2018年XXX期末考试", "#173177"));
        content.put("keyword2", new WxNoticeContentValue("月考", "#173177"));
        StringBuilder remark = new StringBuilder();
        remark.append("语文:20分\n");
        remark.append("英语:20分\n");
        remark.append("数学:20分\n");
        remark.append("物理:20分\n");
        remark.append("化学:20分\n");
        remark.append("生物:20分\n");
        remark.append("历史:20分\n");
        remark.append("地理:20分\n");
        remark.append("政治:20分\n");
        remark.append("详细报告请点击这里进行查看!");
        content.put("remark", new WxNoticeContentValue(remark.toString(), "#173177"));

        WxNoticeData wxNoticeData = new WxNoticeData();
        wxNoticeData.setTouser("oIlKj1B_ihA7N1FceIm9cL_kpJmU");
        wxNoticeData.setUrl("qq.com");
        wxNoticeData.setData(content);
        System.out.println();
        String json = Json2.toJson(wxNoticeData);
        log.debug(json);
        service.notice(wxNoticeData);
    }
}