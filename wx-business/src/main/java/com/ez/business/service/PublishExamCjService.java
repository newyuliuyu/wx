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

    boolean isYetPublishStudentCj(long examId);

    void publishStudentCj(long examId, String[] zkzh);

    List<StudentCj> fetchPublishCjLog(long examId);
}
