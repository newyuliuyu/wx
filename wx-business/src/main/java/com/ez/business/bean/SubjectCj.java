package com.ez.business.bean;

import lombok.Data;
import lombok.ToString;

/**
 * ClassName: SubjectCj <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-11-25 下午2:01 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Data
@ToString
public class SubjectCj {
    private long subjectId;
    private String subjectName;
    private double score;
}
