package com.ez.wx.service;

import com.ez.business.bean.FileInfo;
import com.ez.common.progress.Progresses;
import com.ez.common.thread.ThreadExecutor;
import com.ez.wx.service.process.ImportStudentinfoStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProcessStudentInfoService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 下午4:04 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@Service
public class ProcessStudentInfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String process(List<FileInfo> fileInfos) {
        Progresses progresses = new Progresses(false);
        String key = progresses.saveToCache("ips");
        ImportStudentinfoStarter starter = new ImportStudentinfoStarter(jdbcTemplate, progresses, fileInfos);
        ThreadExecutor.getInstance().getExecutorService().submit(starter);
        return key;
    }

}
