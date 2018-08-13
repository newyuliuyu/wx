package com.ez.business.service.impl;

import com.ez.business.bean.StudentCj;
import com.ez.business.service.PublishExamCjService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PublishExamCjServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 上午11:13 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
@Slf4j
public class PublishExamCjServiceImpl implements PublishExamCjService {

    @Override
    public boolean checkYetPublishStudentCj(long examId) {
        return false;
    }


    @Override
    public void publishStudentCj(long examId, String[] zkzh) {

    }

    @Override
    public List<StudentCj> fetchPublishCjLog(long examId) {
        return null;
    }
}
