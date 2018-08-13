package com.ez.wx.service.process;

import com.ez.business.bean.SUUKeyHelper;
import com.ez.business.bean.StudentCj;
import com.ez.common.disruptor.Processor;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
public class SaveStudentCjLogToDbProcess implements Processor<StudentCj> {
    private final int limits = 10000;
    private JdbcTemplate jdbcTemplate;
    private Lock lock = new ReentrantLock();
    private List<StudentCj> studentCjs = Lists.newArrayListWithCapacity(limits);

    public SaveStudentCjLogToDbProcess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Class<StudentCj> getObjClazz() {
        return StudentCj.class;
    }

    @Override
    public void process(StudentCj data) {
        List<StudentCj> tmp = null;
        lock.lock();
        try {
            studentCjs.add(data);
            if (studentCjs.size() >= limits) {
                tmp = Lists.newArrayListWithCapacity(studentCjs.size());
                tmp.addAll(studentCjs);
                studentCjs = Lists.newArrayListWithCapacity(limits);
            }
        } finally {
            lock.unlock();
        }
        if (tmp != null) {
            save(tmp);
        }
    }

    private void save(List<StudentCj> studentCjs) {
        final String sql = "INSERT INTO wx_send_notice_log (examId,zkzh,name,schoolCode,schoolName,clazzCode,clazzName,uukey,statusNum,msg) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            jdbcTemplate.execute(new ConnectionCallback<Boolean>() {
                @Override
                public Boolean doInConnection(Connection con) throws SQLException, DataAccessException {

                    PreparedStatement pst = con.prepareStatement(sql);

                    for (StudentCj cj : studentCjs) {
                        int idx = 1;
                        pst.setLong(idx++, cj.getExamId());
                        pst.setString(idx++, cj.getZkzh());
                        pst.setString(idx++, cj.getName());
                        pst.setString(idx++, cj.getSchoolCode());
                        pst.setString(idx++, cj.getSchoolName());
                        pst.setString(idx++, cj.getClazzCode());
                        pst.setString(idx++, cj.getClazzName());
                        pst.setString(idx++, SUUKeyHelper.getUUKey(cj));
                        pst.setBoolean(idx++, cj.isStatusNum());
                        pst.setString(idx++, cj.getMsg());
                        pst.addBatch();
                    }
                    pst.executeBatch();
                    pst.close();
                    return true;
                }
            });
        } catch (Exception e) {
            log.error(sql, e);
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        if (!studentCjs.isEmpty()) {
            save(studentCjs);
            studentCjs = null;
        }
    }
}
