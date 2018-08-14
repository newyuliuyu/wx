package com.ez.business.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ClassName: StudentCjDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午1:33 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface StudentCjDao {

    List<Map<String, Object>> fetchStudentCj(@Param("examId") long examId,
                                             @Param("start") int start,
                                             @Param("limit") int limit);

    List<Map<String, Object>> fetchStudentCjWithZKZH(@Param("examId") long examId,
                                                     @Param("zkzhs") String[] zkzhs);
}
