package com.ez.business.dao;

import com.ez.business.bean.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: SubjectDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-16 下午3:41 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface SubjectDao {
    List<Subject> fecthExamSubjects(@Param("examId") long examId);
}
