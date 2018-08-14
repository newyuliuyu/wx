package com.ez.wx.service.process;

import com.ez.business.bean.Exam;
import com.ez.business.bean.SUUKeyHelper;
import com.ez.business.bean.StudentCj;
import com.ez.business.bean.SystemAttributeKey;
import com.ez.business.bean.wx.WxNoticeContent;
import com.ez.business.bean.wx.WxNoticeContentValue;
import com.ez.business.bean.wx.WxNoticeData;
import com.ez.common.disruptor.Processor;
import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.HttpPostBuilder;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.wx.service.SystemAttributeMgr;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * ClassName: SaveToDbProcess <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 下午6:50 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class PublishStudnetCjProcess implements Processor<StudentCj> {

    private JdbcTemplate jdbcTemplate;
    private String sendNoticeUrl;
    private String studentReportURL;
    private Exam exam;

    public PublishStudnetCjProcess(JdbcTemplate jdbcTemplate,
                                   Exam exam) {
        this.jdbcTemplate = jdbcTemplate;
        this.exam = exam;
        init();
    }

    private void init() {
        this.sendNoticeUrl = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.wxProxyURL);
        this.sendNoticeUrl = sendNoticeUrl + "/wxcjnotice";
        this.studentReportURL = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.studentReportURl);
    }

    @Override
    public Class<StudentCj> getObjClazz() {
        return StudentCj.class;
    }

    @Override
    public void process(StudentCj data) {
        String wxopenid = getWXOpenId(data);
        if (StringUtils.isEmpty(wxopenid)) {
            data.setStatusNum(false);
            String msg = String.format("%s(%s)学生没有找到对应的wx注册号", data.getName(), data.getZkzh());
            data.setMsg(msg);
            return;
        }
        pulishNotice(data, wxopenid);
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
        RequestResult rrs = hcUtils.exec(post);
        hcUtils.close();

    }

    private WxNoticeContent getContent(StudentCj data) {
        WxNoticeContent content = new WxNoticeContent();
        content.put("first", new WxNoticeContentValue(data.getName() + "同学本次考试的成绩", "#173177"));
        content.put("keyword1", new WxNoticeContentValue(exam.getName(), "#173177"));
        content.put("keyword2", new WxNoticeContentValue("", "#173177"));
        StringBuilder remark = new StringBuilder();

        Map<String, Double> cjScoreMap = data.getSubjectCj();
        for (String subjectName : cjScoreMap.keySet()) {
            double score = cjScoreMap.get(subjectName);
            remark.append(subjectName + ":" + score + "分\n");
        }
        remark.append("详细报告请点击这里进行查看!");
        content.put("remark", new WxNoticeContentValue(remark.toString(), "#173177"));
        return content;
    }

    private String getStudentReportURl(StudentCj cj) {
        String url = studentReportURL.replaceAll("EXAMID", cj.getExamId() + "");
        url = studentReportURL.replaceAll("ZKZH", cj.getZkzh());
        return url;
    }

    public String getWXOpenId(StudentCj studentCj) {
        final String sql = "SELECT wxopenid FROM wx_student_bound a WHERE a.uukey=? OR a.phone=? OR a.idCardNumber=?";
        try {
            String openId = jdbcTemplate.execute(sql, new PreparedStatementCallback<String>() {
                @Override
                public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    ps.setString(1, SUUKeyHelper.getUUKey(studentCj));
                    ps.setString(2, studentCj.getCode());
                    ps.setString(3, studentCj.getCode());
                    ResultSet rs = ps.executeQuery();
                    String result = "";
                    if (rs.next()) {
                        result = rs.getString(1);
                    }
                    return result;
                }
            });
            return openId;
        } catch (Exception e) {
            log.error(sql, e);
            throw new RuntimeException(e);
        }
    }

}