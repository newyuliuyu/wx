package com.ez.wx.service.process;

import com.ez.business.bean.StudentCj;
import com.ez.business.service.StudentCjService;
import com.ez.common.disruptor.EzTask;
import com.ez.common.spring.SpringContextUtil;

import java.util.List;

/**
 * ClassName: ImportStudentInfoTask <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 下午6:46 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class PublishCjTask implements EzTask {
    private List<StudentCj> studentCjs;
    private int curIdx = 0;
    private int taskNum = 0;

    public PublishCjTask(long examId) {
        StudentCjService studentCjService = SpringContextUtil.getBean("StudentCjService");
        studentCjs = studentCjService.fetchStudentCj(examId);
        taskNum = studentCjs.size();
    }

    @Override
    public int getTaskTotlaNum() {
        return taskNum;
    }

    @Override
    public boolean next() {
        boolean result = false;
        if (curIdx < taskNum) {
            result = true;
        }
        return result;
    }

    @Override
    public <T> T get() {
        return (T) studentCjs.get(curIdx++);
    }
}
