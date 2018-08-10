package com.ez.wx.service.process;

import com.ez.business.bean.StudentInfo;
import com.ez.common.disruptor.EzTask;

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
public class ImportStudentInfoTask implements EzTask {
    private List<StudentInfo> studentInfos;
    private int curIdx = 0;
    private int taskNum = 0;

    public ImportStudentInfoTask(List<StudentInfo> studentInfos) {
        this.studentInfos = studentInfos;
        taskNum = studentInfos.size();
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
        return (T) studentInfos.get(curIdx++);
    }
}
