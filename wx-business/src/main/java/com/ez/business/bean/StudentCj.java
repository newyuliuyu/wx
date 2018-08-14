package com.ez.business.bean;

import com.google.common.collect.Maps;
import lombok.*;

import java.util.Map;

/**
 * ClassName: StudentCj <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午1:30 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudentCj {
    private int id;
    private long examId;
    private String name;
    private String zkzh;
    private String code;
    private String schoolCode;
    private String schoolName;
    private String clazzCode;
    private String clazzName;
    private int entrySchoolYear;
    private boolean statusNum;
    private String msg;
    private Map<String, Double> subjectCj = Maps.newHashMap();


}
