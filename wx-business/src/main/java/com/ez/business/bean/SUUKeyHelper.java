package com.ez.business.bean;

import com.ez.common.util.MD5;

/**
 * ClassName: StudentUUKeyHelper <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午3:26 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class SUUKeyHelper {
    public static String getUUKey(StudentInfo studentInfo) {
        return getUUKey(studentInfo.getName(), studentInfo.getEntrySchoolYear(), studentInfo.getSchoolCode(), studentInfo.getClazzCode());
    }

    public static String getUUKey(StudentCj studentCj) {
        return getUUKey(studentCj.getName(), studentCj.getEntrySchoolYear(), studentCj.getSchoolCode(), studentCj.getClazzCode());
    }

    public static String getUUKey(String name, int entrySchoolYear, String schoolCode, String clazzCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(entrySchoolYear).append(schoolCode).append(clazzCode);
        return MD5.toMD5(sb.toString());
    }
}
