package com.ez.business.bean;

import com.ez.common.util.MD5;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: StudentInfo <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 下午6:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Setter
@Getter
@ToString
public class StudentInfo {
    private String name;
    private int gender;
    private String schoolCode;
    private String schoolName;
    private String grade;
    private String clazzCode;
    private String clazzName;
    private String phone;
    private String idCardNumber;
    private int entrySchoolYear;

    public String getUUKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(entrySchoolYear).append(schoolCode).append(clazzCode);
        return MD5.toMD5(sb.toString());
    }

}
