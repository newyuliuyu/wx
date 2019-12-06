package com.ez.business.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: PdfReportInfo <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-11-5 上午10:44 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
@ToString
public class PdfReportInfo {
    private String code;
    private long examId;
    private long subjectId;
    private String subjectName;
    private String onlykey;
    private String addr;
}
