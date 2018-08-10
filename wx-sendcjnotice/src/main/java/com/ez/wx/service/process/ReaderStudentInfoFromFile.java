package com.ez.wx.service.process;

import com.ez.business.bean.FileInfo;
import com.ez.business.bean.StudentInfo;
import com.ez.business.bean.SystemAttributeKey;
import com.ez.common.file.reader.FileProcess;
import com.ez.common.file.reader.FileProcessUtil;
import com.ez.common.file.reader.Rowdata;
import com.ez.common.util.GradeInfo;
import com.ez.common.util.GradeNameOrderHelper;
import com.ez.wx.service.SystemAttributeMgr;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * ClassName: ReaderStudentInfoFromFile <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 下午6:08 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ReaderStudentInfoFromFile {
    private List<FileInfo> fileInfos;

    public ReaderStudentInfoFromFile(List<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    public List<StudentInfo> read() {
        List<StudentInfo> rowdatas = Lists.newArrayList();
        for (FileInfo fileInfo : fileInfos) {
            Path filePath = getFilePath(fileInfo);
            FileProcess fileProcess = FileProcessUtil.getFileProcess(filePath);
            while (fileProcess.next()) {
                Rowdata rowdata = fileProcess.getRowdata();
                rowdatas.add(toStudentInfo(rowdata));
            }
        }
        return rowdatas;
    }

    private StudentInfo toStudentInfo(Rowdata rowdata) {
        StudentInfo studentInfo = new StudentInfo();
        String name = rowdata.getData("姓名");
        if (StringUtils.isEmpty(name)) {
            Throwables.propagate(new RuntimeException("学生姓名不能为空"));
        }
        String schoolCode = rowdata.getData("学校代码");
        String schoolName = rowdata.getData("学校名字");
        if (StringUtils.isEmpty(schoolCode) || StringUtils.isEmpty(schoolName)) {
            Throwables.propagate(new RuntimeException(String.format("学生%s所在学校不能为空", studentInfo.getName())));
        }
        String grade = rowdata.getData("年级");
        if (StringUtils.isEmpty(grade)) {
            Throwables.propagate(new RuntimeException(String.format("学生%s所在年级不能为空", studentInfo.getName())));
        }
        String clazzCode = rowdata.getData("班级代码");
        String clazzName = rowdata.getData("班级名称");
        if (StringUtils.isEmpty(clazzCode) || StringUtils.isEmpty(clazzName)) {
            Throwables.propagate(new RuntimeException(String.format("学生%s所在班级不能为空", studentInfo.getName())));
        }
        String phone = rowdata.getData("手机号");
        String idCardNumber = rowdata.getData("身份证号");
        int gender = 1;
        if (rowdata.getData("性别") != null && !rowdata.getData("性别").equals("")) {
            try {
                gender = Integer.parseInt(rowdata.getData("性别"));
            } catch (Exception e) {
                Throwables.propagate(new RuntimeException(String.format("学生%s在转换性别的时候出错", studentInfo.getName()), e));
            }
        } else {
            Throwables.propagate(new RuntimeException(String.format("学生%s的性别不能为空", studentInfo.getName())));
        }

        GradeInfo gradeInfo = GradeNameOrderHelper.getGradeInfo(name);
        studentInfo.setName(name);
        studentInfo.setSchoolCode(schoolCode);
        studentInfo.setSchoolName(schoolName);
        studentInfo.setGrade(grade);
        studentInfo.setEntrySchoolYear(gradeInfo.getEntranceYear());
        studentInfo.setClazzCode(clazzCode);
        studentInfo.setClazzName(clazzName);
        studentInfo.setPhone(phone);
        studentInfo.setIdCardNumber(idCardNumber);
        studentInfo.setGender(gender);
        return studentInfo;
    }

    private Path getFilePath(FileInfo fileInfo) {
        String dir = SystemAttributeMgr.newInstance().getPathValue(SystemAttributeKey.UPLOAD_PATH);
        return Paths.get(dir + File.separator + fileInfo.getNewfile());
    }
}
