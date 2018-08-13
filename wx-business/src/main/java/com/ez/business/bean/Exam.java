package com.ez.business.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * ClassName: Exam <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午3:14 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
public class Exam {
    private long id;
    private String name;
    private String grade;
    private int semester;        // 学期
    private int entranceYear;
    private int graduationYear;
    private int studySection;    // 学段
    private int wl;//0 没有文理 1 只有理科 2只有文科 3 文理都有
    private String examlevel;        // 考试级别 联考 校考 班级
    private String subjectTag;
    private Date createDate;
    private Date beginDate;
    private Date endDate;
    private int year;
    private int month;
    private int weekOfyear;
    private int weekOfmonth;
    private String uniqueKey;
    private int analysisState;
    private boolean isUnionExam;
    private int unionExamType;
    private boolean schoolShowUnionExam;
    private boolean hasTeachClazzSubject;
}
