package com.ez.business.service;

import com.ez.business.bean.StudentCj;

import java.util.List;

/**
 * ClassName: StudentCjService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午1:29 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface StudentCjService {

    List<StudentCj> fetchStudentCj(long examId);

    List<StudentCj> fetchStudentCj(long examId, StudentCj[] studentCjs);
}
