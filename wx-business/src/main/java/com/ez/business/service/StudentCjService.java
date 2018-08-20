package com.ez.business.service;

import com.ez.business.bean.Exam;
import com.ez.business.bean.Student;
import com.ez.business.bean.StudentCj;

import java.util.List;
import java.util.Map;

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

    Student getStudentInfo(String zkzh);

    List<Exam> fetchStudentExams(String studentCode);

    List<Map<String, Object>> fetchStudentSubjectScores(long examId, String zkzh);

    List<StudentCj> fetchStudentCj(long examId);

    List<StudentCj> fetchStudentCj(long examId, StudentCj[] studentCjs);
}
