package com.ez.wx.service;

import com.ez.common.download.DownloadTemplate;
import com.ez.common.util.ExcelRowdata;
import com.ez.common.util.ExcelTable;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

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
@Service("studentInfoTemplate")
public class StudentInfoTemplate implements DownloadTemplate {
    @Override
    public String name() {
        return "学生信息魔板.xls";
    }

    @Override
    public void tmplate(OutputStream os, Map<String, String[]> params) throws IOException {
        int rowIdx = 0;
        ExcelTable et = new ExcelTable();
        et.addSheet("学生信息");
        CellStyle style = et.getHeaderStyle();

        ExcelRowdata rowdata = new ExcelRowdata();
        rowdata.append("姓名", "学校代码", "学校名字", "年级", "班级代码", "班级名称", "性别", "手机号", "身份证号");
        et.createRowAndCells(rowIdx++, style, rowdata);
        rowdata = new ExcelRowdata();
        rowdata.append("说明：\n" +
                "1.学校代码和学校名称必须为平台已有的\n" +
                "2.年级的值只能为一年级、二年级、三年级、四年级、五年级、六年级、初一、初二、初三、高一、高二、高三的其中一个\n" +
                "3.班级名称和代码必须为考试的时候的名称和代码\n" +
                "4.性别的值必须为1和2，1代表男 2代表女");
        et.createRowAndCells(rowIdx + 2, rowdata);
        et.save(os);
    }
}
