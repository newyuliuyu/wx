package com.ez.wx.service.process;

import com.ez.business.bean.FileInfo;
import com.ez.business.bean.SUUKeyHelper;
import com.ez.business.bean.StudentInfo;
import com.ez.wx.WxSendcjnoticeApplication;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: ReaderStudentInfoFromFileTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 上午11:25 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
@Slf4j
public class ReaderStudentInfoFromFileTest {

    @Test
    public void read() throws Exception {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setNewfile("学生信息模板.xls");
        fileInfo.setOldfile("学生信息模板.xls");
        ReaderStudentInfoFromFile readFile = new ReaderStudentInfoFromFile(Lists.newArrayList(fileInfo));
        List<StudentInfo> studentInfos = readFile.read();
        studentInfos.stream().forEach(item -> {
            System.out.println(SUUKeyHelper.getUUKey(item));
        });

        Assert.assertTrue(studentInfos.size() == 20);
    }

}