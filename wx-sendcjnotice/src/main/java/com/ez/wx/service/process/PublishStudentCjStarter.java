package com.ez.wx.service.process;

import com.ez.business.bean.Exam;
import com.ez.business.service.ExamService;
import com.ez.common.disruptor.EzEventPerformer;
import com.ez.common.disruptor.ResultProcessor;
import com.ez.common.progress.ProgressEvent;
import com.ez.common.progress.Progresses;
import com.ez.common.spring.SpringContextUtil;
import org.springframework.jdbc.core.JdbcTemplate;

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
public class PublishStudentCjStarter implements Runnable {
    private Progresses progresses;
    private Exam exam;
    private JdbcTemplate jdbcTemplate;

    public PublishStudentCjStarter(Progresses progresses, long examId) {
        this.progresses = progresses;
        init(examId);
    }

    private void init(long examId) {
        jdbcTemplate = SpringContextUtil.getBean("jdbcTemplate");
        ExamService examService = SpringContextUtil.getBean(ExamService.class);
        exam = examService.getExam(examId);
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

        PublishCjTask task = new PublishCjTask(exam.getId());
        PublishStudnetCjProcess publishStudnetCjProcess = new PublishStudnetCjProcess(jdbcTemplate, exam);
        SaveStudentCjLogToDbProcess saveStudentCjLogToDbProcess = new SaveStudentCjLogToDbProcess(jdbcTemplate);


        ProgressEvent progressEvent = new ProgressEvent(0, 100, "开始发成绩sss...");
        publishEvent(progressEvent);
        EzEventPerformer ezEventPerformer = new EzEventPerformer();
        ezEventPerformer.setTask(task).setProgresses(progresses);
        ezEventPerformer.addProcessorPool(publishStudnetCjProcess);
        ezEventPerformer.addProcessorPool(saveStudentCjLogToDbProcess);
        ezEventPerformer.setResultProcessor(new MyResult(saveStudentCjLogToDbProcess));
        ezEventPerformer.process();
    }


    private void publishEvent(ProgressEvent progressEvent) {
        if (progresses != null) {
            progresses.publish(progressEvent);
        }
    }

    class MyResult implements ResultProcessor {
        private SaveStudentCjLogToDbProcess saveStudentCjLogToDbProcess;

        public MyResult(SaveStudentCjLogToDbProcess saveStudentCjLogToDbProcess) {
            this.saveStudentCjLogToDbProcess = saveStudentCjLogToDbProcess;
        }

        @Override
        public void fail() {

        }

        @Override
        public void sucess() {
            saveStudentCjLogToDbProcess.clear();
        }

        @Override
        public void finallys() {

        }
    }
}
