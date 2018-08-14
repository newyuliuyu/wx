package com.ez.business.dao;

import com.ez.business.bean.StudentCj;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: PublishLogDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午5:25 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface PublishLogDao {
    List<StudentCj> fetchPublishStudentCj(@Param("examId") long examId,
                                          @Param("start") int start,
                                          @Param("limit") int limit);

    void updatePublishStudentCj(@Param("studentCjs") List<StudentCj> studentCjs);
}
