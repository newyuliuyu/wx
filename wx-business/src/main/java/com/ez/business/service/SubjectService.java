package com.ez.business.service;

import com.ez.business.bean.Subject;

import java.util.List;

/**
 * ClassName: SubjectService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-16 下午3:45 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface SubjectService {
    List<Subject> fecthExamSubjects(long examId);
}
