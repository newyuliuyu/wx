package com.ez.business.service.impl;

import com.ez.business.bean.Subject;
import com.ez.business.dao.SubjectDao;
import com.ez.business.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: SubjectServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-16 下午3:48 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service("SubjectService")
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDao subjectDao;


    @Override
    public List<Subject> fecthExamSubjects(long examId) {
        return subjectDao.fecthExamSubjects(examId);
    }
}
