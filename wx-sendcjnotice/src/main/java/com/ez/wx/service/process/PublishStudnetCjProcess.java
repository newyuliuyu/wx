package com.ez.wx.service.process;

import com.ez.business.bean.StudentCj;
import com.ez.common.disruptor.Processor;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: SaveToDbProcess <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 下午6:50 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class PublishStudnetCjProcess implements Processor<StudentCj> {

    private SendStudentCjService sendStudentCjService;

    public PublishStudnetCjProcess(long examId) {
        sendStudentCjService = new SendStudentCjService(examId);

    }


    @Override
    public Class<StudentCj> getObjClazz() {
        return StudentCj.class;
    }

    @Override
    public void process(StudentCj data) {
        sendStudentCjService.sendNotice(data);
    }


}
