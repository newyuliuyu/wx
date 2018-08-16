package com.ez.wx.service.process;

import com.ez.business.bean.*;
import com.ez.business.bean.wx.WxNoticeContent;
import com.ez.business.bean.wx.WxNoticeContentValue;
import com.ez.business.bean.wx.WxNoticeData;
import com.ez.business.service.ExamService;
import com.ez.business.service.SubjectService;
import com.ez.business.service.WxBoundStudentService;
import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpPostBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.spring.SpringContextUtil;
import com.ez.wx.service.SystemAttributeMgr;
import com.google.common.primitives.Ints;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: SendStudentCjService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-14 下午3:49 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class SendStudentCjService {
    private JdbcTemplate jdbcTemplate;
    private Exam exam;
    private String sendNoticeUrl;
    private String studentReportURL;
    private WxBoundStudentService wxBoundStudentService;
    private List<Subject> subjects;

    public SendStudentCjService(long examId) {
        this.jdbcTemplate = SpringContextUtil.getBean("jdbcTemplate");
        this.wxBoundStudentService = SpringContextUtil.getBean("WxBoundStudentService");
        ExamService examService = SpringContextUtil.getBean(ExamService.class);
        this.exam = examService.getExam(examId);
        SubjectService subjectService = SpringContextUtil.getBean(SubjectService.class);
        this.subjects = subjectService.fecthExamSubjects(examId);
        this.subjects = this.subjects.stream().sorted((s1, s2) -> Ints.compare(s1.getDisplayOrder(), s2.getDisplayOrder())).collect(Collectors.toList());

        this.sendNoticeUrl = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.wxProxyUrl);
        this.sendNoticeUrl = sendNoticeUrl + "/wxcjnotice";
        this.studentReportURL = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.studentReportURl);
    }

    public void sendNotice(StudentCj studentCj) {
        studentCj.setStatusNum(true);
        studentCj.setMsg("");
        String wxopenid = getWXOpenId(studentCj);
        if (StringUtils.isEmpty(wxopenid)) {
            studentCj.setStatusNum(false);
            String msg = String.format("%s(%s)同学没有找到对应的wx注册号", studentCj.getName(), studentCj.getZkzh());
            studentCj.setMsg(msg);
            return;
        }
        pulishNotice(studentCj, wxopenid);
    }

    public void pulishNotice(StudentCj data, String wxopenid) {
        WxNoticeContent content = getContent(data);
        WxNoticeData wxNoticeData = new WxNoticeData();
        wxNoticeData.setTouser(wxopenid);
        wxNoticeData.setUrl(getStudentReportURl(data));
        wxNoticeData.setData(content);
        String json = Json2.toJson(wxNoticeData);
        HttpPost post = HttpPostBuilder.create(sendNoticeUrl).buildJson(json);
        HCUtils hcUtils = HCUtils.createDefault();
        try {
            RequestResult rrs = hcUtils.exec(post);
        } catch (Exception e) {
            data.setStatusNum(false);
            data.setMsg(String.format("发送失败,%s", e.getMessage()));
        } finally {
            hcUtils.close();
        }
    }

    private WxNoticeContent getContent(StudentCj data) {
        WxNoticeContent content = new WxNoticeContent();
        content.put("first", new WxNoticeContentValue(data.getName() + "同学本次考试的成绩", "#173177"));
        content.put("keyword1", new WxNoticeContentValue(exam.getName(), "#173177"));
        content.put("keyword2", new WxNoticeContentValue("", "#173177"));
        StringBuilder remark = new StringBuilder();

        Map<String, Double> cjScoreMap = data.getSubjectCj();
        for (Subject subject : subjects) {
            Double score = cjScoreMap.get(subject.getName());
            if (score != null) {
                remark.append(subject.getName() + ":" + score + "分\n");
            }
        }
        remark.append("详细报告请点击这里进行查看!");
        content.put("remark", new WxNoticeContentValue(remark.toString(), "#173177"));
        return content;
    }

    private String getStudentReportURl(StudentCj cj) {
        String url = studentReportURL.replaceAll("EXAMID", cj.getExamId() + "").replaceAll("ZKZH", cj.getZkzh());
        return url;
    }

    public String getWXOpenId(StudentCj studentCj) {
        return wxBoundStudentService.fetchWxOpenId(SUUKeyHelper.getUUKey(studentCj), studentCj.getCode());
    }
}
