package com.ez.business.service.impl;

import com.ez.business.bean.StudentCj;
import com.ez.business.dao.PublishLogDao;
import com.ez.business.service.PublishExamCjService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PublishLogDao publishLogDao;

    @Override
    public void updatePublishStudentCj(List<StudentCj> studentCjs) {
        publishLogDao.updatePublishStudentCj(studentCjs);
    }

    @Override
    public boolean isYetPublishStudentCj(long examId) {
        List<StudentCj> studentCjs = publishLogDao.fetchPublishStudentCj(examId, 0, 1);
        return !studentCjs.isEmpty();
    }


    @Override
    public List<StudentCj> fetchPublishCjLog(long examId) {
        List<StudentCj> studentCjs = publishLogDao.fetchPublishStudentCj(examId, 0, 100000);
        return studentCjs;
    }
}
