package com.ez.business.service.impl;

import com.ez.business.bean.Exam;
import com.ez.business.dao.ExamDao;
import com.ez.business.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ExamServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午4:32 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;

    @Override
    public Exam getExam(long examId) {
        return examDao.getExam(examId);
    }
}
