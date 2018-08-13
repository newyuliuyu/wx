package com.ez.wx.service;

import com.ez.common.progress.Progresses;
import com.ez.common.thread.ThreadExecutor;
import com.ez.wx.service.process.PublishStudentCjStarter;
import org.springframework.stereotype.Service;

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

    public void start(long examId) {
        String key = "publishexamcj-" + examId;
        Progresses progresses = new Progresses(false);
        progresses.saveToCacheWithCustomKey(key);
        PublishStudentCjStarter starter = new PublishStudentCjStarter(progresses, examId);
        ThreadExecutor.getInstance().getExecutorService().submit(starter);
    }

}
