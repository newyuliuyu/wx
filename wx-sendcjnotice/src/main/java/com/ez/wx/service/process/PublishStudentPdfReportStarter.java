package com.ez.wx.service.process;

import com.ez.business.bean.*;
import com.ez.business.bean.wx.WxNoticeContent;
import com.ez.business.bean.wx.WxNoticeContentValue;
import com.ez.business.bean.wx.WxNoticeData;
import com.ez.business.dao.ExamDao;
import com.ez.business.dao.StudentPdfReportDao;
import com.ez.business.dao.WxBoundStudentDao;
import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpPostBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.spring.SpringContextUtil;
import com.ez.wx.service.SystemAttributeMgr;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: ImportStudentinfoStarter <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 下午1:47 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class PublishStudentPdfReportStarter implements Runnable {
    private String wxopenId;
    private String pdfReportURL;
    private String sendNoticeUrl;
    private WxBoundStudentDao wxBoundStudentDao;
    private ExamDao examDao;
    private StudentPdfReportDao studentPdfReportDao;
//    private String studentPdfReportDemoUrl;


    public PublishStudentPdfReportStarter(String wxopenId) {
        this.wxopenId = wxopenId;
        this.wxBoundStudentDao = SpringContextUtil.getBean(WxBoundStudentDao.class);
        this.examDao = SpringContextUtil.getBean(ExamDao.class);
        this.studentPdfReportDao = SpringContextUtil.getBean(StudentPdfReportDao.class);
        this.sendNoticeUrl = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.wxProxyUrl);
        this.sendNoticeUrl += "/wxpdfreportnotice";
        this.pdfReportURL = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.wxPublishCjUrl);
//        this.studentPdfReportDemoUrl = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.studentPdfReportDemoUrl);
    }


    @Override
    public void run() {
        starter();
    }

    private void starter() {
        WxBoundStudent wxBoundStudent = wxBoundStudentDao.getWxBoundStudent(wxopenId);
        if (wxBoundStudent == null) {
            log.error("对于wxopenID[{}],没有找到对应的学生", wxopenId);
            sendDemo();
            return;
        }
        ExamStudentInfo examStudentInfo = studentPdfReportDao.getStudentInfo(wxBoundStudent.getSchoolCode(),
                wxBoundStudent.getClazzCode(),
                wxBoundStudent.getName());
        if (examStudentInfo == null) {
            log.error("对于学生[{}],没有找到对应的学生考试学生信息", wxBoundStudent);
            sendDemo();
            return;
        }

        List<PdfReportInfo> pdfReportInfos = studentPdfReportDao.getPdfReportInfos(examStudentInfo.getExamId(), examStudentInfo.getCode());
        if(pdfReportInfos==null|| pdfReportInfos.isEmpty()){
            log.error("对于学生[{}],没有数据，发送示例报告", wxBoundStudent);
            sendDemo();
            return;
        }


        List<SubjectCj> subjectCjs = studentPdfReportDao.getStudentSubjectCjs(examStudentInfo.getExamId(), examStudentInfo.getCode());
        Map<String, SubjectCj> subjectCjMap = subjectCjs.stream().collect(Collectors.toMap(SubjectCj::getSubjectName, subjectCj -> subjectCj));
        Exam exam = examDao.getExam(examStudentInfo.getExamId());

        sendReportToWx(exam, wxBoundStudent, pdfReportInfos, subjectCjMap);
    }


    private void sendDemo(){
        Exam exam = new Exam();
        exam.setId(0L);
        exam.setName("示例考试");
        WxBoundStudent wxBoundStudent=WxBoundStudent.builder()
                .wxopenid(wxopenId)
                .name("XX")
                .schoolCode("demoschoolcode")
                .schoolName("示例学校")
                .grade("高一")
                .clazzCode("democlazzcode")
                .clazzCode("示例班级")
                .phone("test123")
                .idCardNumber("test123")
                .gender(1)
                .entrySchoolYear(2017)
                .dbUukey("test123")
                .build();

        PdfReportInfo pdfReportInfo = new PdfReportInfo();
        pdfReportInfo.setCode("test123");
        pdfReportInfo.setExamId(0L);
        pdfReportInfo.setSubjectId(0L);
        pdfReportInfo.setSubjectName("数学");
        pdfReportInfo.setOnlykey("demo123");
        pdfReportInfo.setAddr("");

        SubjectCj subjectCj = new SubjectCj();
        subjectCj.setSubjectId(0L);
        subjectCj.setSubjectName("数学");
        subjectCj.setScore(86);

        Map<String, SubjectCj> subjectCjMap = Maps.newHashMap();
        subjectCjMap.put(subjectCj.getSubjectName(),subjectCj);
        pulishNotice(exam,wxBoundStudent,pdfReportInfo,subjectCjMap);
    }

    private void sendReportToWx(Exam exam,
                                WxBoundStudent wxBoundStudent,
                                List<PdfReportInfo> pdfReportInfos,
                                Map<String, SubjectCj> subjectCjMap) {
        if (pdfReportInfos == null || pdfReportInfos.isEmpty()) {
            log.warn("没有报告数据[{}-{}]", exam, wxBoundStudent);
            return;
        }
        pdfReportInfos.forEach(pdfReportInfo -> pulishNotice(exam, wxBoundStudent, pdfReportInfo, subjectCjMap));
    }

    public void pulishNotice(Exam exam,
                             WxBoundStudent wxBoundStudent,
                             PdfReportInfo pdfReportInfo,
                             Map<String, SubjectCj> subjectCjMap) {
        WxNoticeContent content = getContent(exam, wxBoundStudent, pdfReportInfo, subjectCjMap);

        WxNoticeData wxNoticeData = new WxNoticeData();
        wxNoticeData.setTouser(wxBoundStudent.getWxopenid());
        wxNoticeData.setUrl(getStudentReportURl(wxBoundStudent, pdfReportInfo));
        wxNoticeData.setData(content);
        String json = Json2.toJson(wxNoticeData);
        log.debug(json);
        HttpPost post = HttpPostBuilder.create(sendNoticeUrl).buildJson(json);
        HCUtils hcUtils = HCUtils.createDefault();
        try {
            RequestResult rrs = hcUtils.exec(post);
        } catch (Exception e) {
            log.error(String.format("发送失败,%s", e.getMessage()), e);
        } finally {
            hcUtils.close();
        }
    }

    private WxNoticeContent getContent(Exam exam,
                                       WxBoundStudent wxBoundStudent,
                                       PdfReportInfo pdfReportInfo,
                                       Map<String, SubjectCj> subjectCjMap) {
        WxNoticeContent content = new WxNoticeContent();
        content.put("first", new WxNoticeContentValue(wxBoundStudent.getName() + "同学" + exam.getName() + "成绩分析及辅导", "#173177"));
        content.put("keyword1", new WxNoticeContentValue(pdfReportInfo.getSubjectName(), "#173177"));
        SubjectCj subjectCj = subjectCjMap.get(pdfReportInfo.getSubjectName());
        String score = subjectCj == null ? "" : subjectCj.getScore() + "";
        content.put("keyword2", new WxNoticeContentValue(score, "#173177"));
        StringBuilder remark = new StringBuilder();
        remark.append("详细报告请点击这里进行查看!");
        content.put("remark", new WxNoticeContentValue(remark.toString(), "#173177"));
        return content;
    }

    private String getStudentReportURl(WxBoundStudent wxBoundStudent, PdfReportInfo pdfReportInfo) {
        String url = pdfReportURL + "/pdf/" + pdfReportInfo.getOnlykey() + "?uukey=" + wxBoundStudent.getDbUukey() + "&code=" + pdfReportInfo.getCode();

        return url;
    }


}
