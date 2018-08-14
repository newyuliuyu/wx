package com.ez.wx.service;

import com.ez.business.bean.StudentCj;
import com.ez.business.service.PublishExamCjService;
import com.ez.business.service.StudentCjService;
import com.ez.common.progress.Progresses;
import com.ez.common.thread.ThreadExecutor;
import com.ez.wx.service.process.PublishStudentCjStarter;
import com.ez.wx.service.process.SendStudentCjService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: StudentInfoTemplate <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 上午10:51 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
public class StartPublishCjStarter {

    @Autowired
    private StudentCjService studentCjService;
    @Autowired
    private PublishExamCjService publishExamCjService;

    public void start(long examId) {
        String key = "publishexamcj-" + examId;
        Progresses progresses = new Progresses(false);
        progresses.saveToCacheWithCustomKey(key);
        PublishStudentCjStarter starter = new PublishStudentCjStarter(progresses, examId);
        ThreadExecutor.getInstance().getExecutorService().submit(starter);
    }

    public void publishStudentCj(long examId, StudentCj[] studentCjs) {
        SendStudentCjService sendStudentCjService = new SendStudentCjService(examId);
        List<StudentCj> newStudentCjs = studentCjService.fetchStudentCj(examId, studentCjs);
        for (StudentCj studentCj : newStudentCjs) {
            sendStudentCjService.sendNotice(studentCj);
        }
        publishExamCjService.updatePublishStudentCj(Lists.newArrayList(newStudentCjs));
    }

}
