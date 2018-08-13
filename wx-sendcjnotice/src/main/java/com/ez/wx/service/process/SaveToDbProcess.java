package com.ez.wx.service.process;

import com.ez.business.bean.SUUKeyHelper;
import com.ez.business.bean.StudentInfo;
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
public class SaveToDbProcess implements Processor<StudentInfo> {
    private final int limits = 10000;
    private JdbcTemplate jdbcTemplate;
    private Lock lock = new ReentrantLock();
    private List<StudentInfo> studentInfos = Lists.newArrayListWithCapacity(limits);

    public SaveToDbProcess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Class<StudentInfo> getObjClazz() {
        return StudentInfo.class;
    }

    @Override
    public void process(StudentInfo data) {
        List<StudentInfo> tmp = null;
        lock.lock();
        try {
            studentInfos.add(data);
            if (studentInfos.size() >= limits) {
                tmp = Lists.newArrayListWithCapacity(studentInfos.size());
                tmp.addAll(studentInfos);
                studentInfos = Lists.newArrayListWithCapacity(limits);
            }
        } finally {
            lock.unlock();
        }
        if (tmp != null) {
            save(tmp);
        }
    }

    private void save(List<StudentInfo> studentInfos) {
        final String sql = "insert into wx_student_info(name,schoolCode,schoolName," +
                "grade,clazzCode,clazzName,phone,idCardNumber,gender,uukey,entrySchoolYear) " +
                "values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            jdbcTemplate.execute(new ConnectionCallback<Boolean>() {
                @Override
                public Boolean doInConnection(Connection con) throws SQLException, DataAccessException {

                    PreparedStatement pst = con.prepareStatement(sql);

                    for (StudentInfo info : studentInfos) {
                        int idx = 1;
                        pst.setString(idx++, info.getName());
                        pst.setString(idx++, info.getSchoolCode());
                        pst.setString(idx++, info.getSchoolName());
                        pst.setString(idx++, info.getGrade());
                        pst.setString(idx++, info.getClazzCode());
                        pst.setString(idx++, info.getClazzName());
                        pst.setString(idx++, info.getPhone());
                        pst.setString(idx++, info.getIdCardNumber());
                        pst.setInt(idx++, info.getGender());
                        pst.setString(idx++, SUUKeyHelper.getUUKey(info));
                        pst.setInt(idx++, info.getEntrySchoolYear());
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
        if (!studentInfos.isEmpty()) {
            save(studentInfos);
            studentInfos = null;
        }
    }
}
