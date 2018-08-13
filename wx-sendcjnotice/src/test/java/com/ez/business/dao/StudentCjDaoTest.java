package com.ez.business.dao;

import com.ez.business.bean.StudentCj;
import com.ez.wx.WxSendcjnoticeApplication;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ClassName: StudentCjDaoTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午2:27 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
public class StudentCjDaoTest {

    @Autowired
    private StudentCjDao studentCjDao;

    @Test
    public void test() throws Exception {
        List<Map<String, Object>> dataset = studentCjDao.fetchStudentCj(196, 0, 1000);
//        dataset.stream().map(this::mapToStudentCj).forEach(System.out::println);
        Map<String, Optional<StudentCj>> datamap = dataset.stream().map(this::mapToStudentCj)
                .collect(Collectors.groupingBy(StudentCj::getZkzh,
                        Collectors.reducing((o1, o2) -> {
                            o1.getSubjectCj().putAll(o2.getSubjectCj());
                            return o1;
                        })
                ));

        System.out.println();
    }

    protected StudentCj mapToStudentCj(Map<String, Object> map) {
        String zkzh = map.get("zkzh").toString();
        String name = map.get("name").toString();
        String clazzCode = map.get("classCode").toString();
        String clazzName = map.get("className").toString();
        String schoolCode = map.get("schoolCode").toString();
        String schoolName = map.get("schoolName").toString();
        String subjectName = map.get("subjectName").toString();
        double score = Double.parseDouble(map.get("score").toString());
        StudentCj studentCj = StudentCj.builder()
                .zkzh(zkzh)
                .name(name)
                .schoolCode(schoolCode)
                .schoolName(schoolName)
                .clazzCode(clazzCode)
                .clazzName(clazzName)
                .subjectCj(Maps.newHashMap())
                .build();
        studentCj.getSubjectCj().put(subjectName, score);
        return studentCj;
    }
}
