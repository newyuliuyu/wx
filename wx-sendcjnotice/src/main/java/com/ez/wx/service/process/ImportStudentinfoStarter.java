package com.ez.wx.service.process;

import com.ez.business.bean.FileInfo;
import com.ez.business.bean.StudentInfo;
import com.ez.common.disruptor.EzEventPerformer;
import com.ez.common.disruptor.ResultProcessor;
import com.ez.common.progress.ProgressEvent;
import com.ez.common.progress.Progresses;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * ClassName: ImportStudentinfoStarter <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-10 下午1:47 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ImportStudentinfoStarter implements Runnable {
    private JdbcTemplate jdbcTemplate;
    private Progresses progresses;
    private List<FileInfo> fileInfos;

    public ImportStudentinfoStarter(JdbcTemplate jdbcTemplate, Progresses progresses, List<FileInfo> fileInfos) {
        this.jdbcTemplate = jdbcTemplate;
        this.progresses = progresses;
        this.fileInfos = fileInfos;
    }

    @Override
    public void run() {
        try {
            starter();
        } finally {
            ProgressEvent progressEvent = ProgressEvent.create(ProgressEvent::new).message(100, 100, "运行完毕", true);
            publishEvent(progressEvent);
        }

    }

    private void starter() {
        List<StudentInfo> studentInfos = readStudentinfo();

        ImportStudentInfoTask task = new ImportStudentInfoTask(studentInfos);
        SaveToDbProcess saveToDbProcess = new SaveToDbProcess(jdbcTemplate);

        ProgressEvent progressEvent = new ProgressEvent(0, 100, "开始导入数据...");
        publishEvent(progressEvent);
        EzEventPerformer ezEventPerformer = new EzEventPerformer();
        ezEventPerformer.setTask(task).setProgresses(progresses);
        ezEventPerformer.addProcessorPool(saveToDbProcess);
        ezEventPerformer.setResultProcessor(new MyResult(saveToDbProcess));
        ezEventPerformer.process();
    }

    private List<StudentInfo> readStudentinfo() {
        ProgressEvent progressEvent = new ProgressEvent(0, 100, "从文件中读取学生信息数据...");
        publishEvent(progressEvent);
        ReaderStudentInfoFromFile readFile = new ReaderStudentInfoFromFile(fileInfos);
        List<StudentInfo> studentInfos = readFile.read();
        return studentInfos;
    }

    private void publishEvent(ProgressEvent progressEvent) {
        if (progresses != null) {
            progresses.publish(progressEvent);
        }
    }

    class MyResult implements ResultProcessor {
        private SaveToDbProcess saveToDbProcess;

        public MyResult(SaveToDbProcess saveToDbProcess) {
            this.saveToDbProcess = saveToDbProcess;
        }

        @Override
        public void fail() {

        }

        @Override
        public void sucess() {
            saveToDbProcess.clear();
        }

        @Override
        public void finallys() {

        }
    }
}
