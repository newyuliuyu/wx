package com.ez.business.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: Subject <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-16 下午3:34 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
public class Subject {
    private long id;
    private Long examId;
    private String name;
    private Double fullscore = 0d;
    private Double kgFullscore = 0d;
    private Double zgFullscore = 0d;
    private int wl = 0;
    private boolean isMultiSubject = false;
    private String childSubjects = "";
    private boolean examSubjects = true;
    private int displayOrder = 0;
    private String parentSubject = "";
    private int dataStatus = 0;
    private boolean isZf = false;
    private boolean zfNotInclude = false;//计算总分不包含该科目
    private boolean zfCjNotInclude = false;//计算总分成绩不包含改科目
    private boolean hasTeachClazz = false;
}
