package com.ez.business.service.impl;

import com.ez.business.bean.Exam;
import com.ez.business.bean.Student;
import com.ez.business.bean.StudentCj;
import com.ez.business.dao.ExamDao;
import com.ez.business.dao.StudentCjDao;
import com.ez.business.service.StudentCjService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: StudentCjServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-13 下午1:32 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service("StudentCjService")
public class StudentCjServiceImpl implements StudentCjService {
    @Autowired
    private StudentCjDao studentCjDao;
    @Autowired
    private ExamDao examDao;

    @Override
    public Student getStudentInfo(String zkzh) {
        return studentCjDao.getStudentInfo(zkzh);
    }

    @Override
    public List<Exam> fetchStudentExams(String studentCode) {
        return studentCjDao.fetchStudentExams(studentCode);
    }

    @Override
    public List<Map<String, Object>> fetchStudentSubjectScores(long examId, String zkzh) {
        return studentCjDao.fetchStudentSubjectScores(examId, zkzh);
    }

    @Override
    public List<StudentCj> fetchStudentCj(long examId, StudentCj[] studentCjs) {
        Exam exam = examDao.getExam(examId);
        String[] zkzhs = Arrays.stream(studentCjs).map(StudentCj::getZkzh).collect(Collectors.toList()).toArray(new String[0]);
        Map<String, StudentCj> oldStudentCjMap = Arrays.stream(studentCjs).collect(Collectors.toMap(StudentCj::getZkzh, cj -> cj));

        Map<String, StudentCj> studentCjMap = Maps.newHashMap();
        List<Map<String, Object>> list = studentCjDao.fetchStudentCjWithZKZH(examId, zkzhs);
        for (Map<String, Object> map : list) {
            StudentCj studentCj = mapToStudentCj(map);
            StudentCj oldStudentCj = oldStudentCjMap.get(studentCj.getZkzh());
            studentCj.setId(oldStudentCj.getId());
            studentCj.setExamId(examId);
            studentCj.setEntrySchoolYear(exam.getEntranceYear());

            oldStudentCj = studentCjMap.get(studentCj.getZkzh());
            if (oldStudentCj == null) {
                oldStudentCj = studentCj;
                studentCjMap.put(oldStudentCj.getZkzh(), oldStudentCj);
            } else {
                oldStudentCj.getSubjectCj().putAll(studentCj.getSubjectCj());
            }
        }
        return Lists.newArrayList(studentCjMap.values());
    }

    @Override
    public List<StudentCj> fetchStudentCj(long examId) {
        Exam exam = examDao.getExam(examId);
        Map<String, StudentCj> studentCjMap = Maps.newHashMap();
        int start = 0;
        final int limit = 1000;
        boolean yes = true;
        while (yes) {
            List<Map<String, Object>> list = studentCjDao.fetchStudentCj(examId, start, limit);
            for (Map<String, Object> map : list) {
                StudentCj studentCj = mapToStudentCj(map);
                studentCj.setExamId(examId);
                studentCj.setEntrySchoolYear(exam.getEntranceYear());
                StudentCj oldStudentCj = studentCjMap.get(studentCj.getZkzh());
                if (oldStudentCj == null) {
                    oldStudentCj = studentCj;
                    studentCjMap.put(oldStudentCj.getZkzh(), oldStudentCj);
                } else {
                    oldStudentCj.getSubjectCj().putAll(studentCj.getSubjectCj());
                }
            }
            if (list.size() < limit) {
                yes = false;
                break;
            } else {
                start += limit;
            }
        }


        return Lists.newArrayList(studentCjMap.values());
    }

    protected StudentCj mapToStudentCj(Map<String, Object> map) {
        String zkzh = map.get("zkzh").toString();
        String code = map.get("code").toString();
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
                .code(code)
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
