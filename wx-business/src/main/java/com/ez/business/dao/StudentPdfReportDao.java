package com.ez.business.dao;

import com.ez.business.bean.ExamStudentInfo;
import com.ez.business.bean.PdfReportInfo;
import com.ez.business.bean.SubjectCj;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: StudentPdfReportDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-11-5 上午10:43 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface StudentPdfReportDao {

    ExamStudentInfo getStudentInfo(@Param("schoolCode") String schoolCode,
                                   @Param("clazzCode") String clazzCode,
                                   @Param("name") String name);

    List<PdfReportInfo> getPdfReportInfos(@Param("examId") long examId,
                                         @Param("code") String code);

    PdfReportInfo getPdfReportInfo(@Param("onlykey") String onlykey);

    List<SubjectCj> getStudentSubjectCjs(@Param("examId") long examId,
                                 @Param("code") String code);

}
