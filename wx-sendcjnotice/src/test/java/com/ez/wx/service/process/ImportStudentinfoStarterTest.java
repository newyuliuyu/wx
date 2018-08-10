package com.ez.wx.service.process;

import com.ez.business.bean.FileInfo;
import com.ez.wx.WxSendcjnoticeApplication;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: ImportStudentinfoStarterTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 下午2:24 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxSendcjnoticeApplication.class)
@Slf4j
public class ImportStudentinfoStarterTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void start() throws Exception {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setNewfile("学生信息模板.xls");
        fileInfo.setOldfile("学生信息模板.xls");
        List<FileInfo> fileInfos = Lists.newArrayList(fileInfo);
        ImportStudentinfoStarter starter = new ImportStudentinfoStarter(jdbcTemplate, null, fileInfos);
        starter.run();

        System.out.println();
    }
}