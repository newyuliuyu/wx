package com.ez.business.service;

import com.ez.business.bean.StudentCj;

import java.util.List;

/**
 * ClassName: PublishExamCjService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 上午11:12 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface PublishExamCjService {

    void updatePublishStudentCj(List<StudentCj> studentCjs);

    boolean isYetPublishStudentCj(long examId);


    List<StudentCj> fetchPublishCjLog(long examId);
}
