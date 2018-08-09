/**
 * Project Name:easydata.etl
 * File Name:FileProcessUtil.java
 * Package Name:com.ez.etl.util
 * Date:2017年3月15日下午6:13:21
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.ez.common.file.reader;

import com.ez.common.file.reader.spi.CsvReader;
import com.ez.common.file.reader.spi.DbfReader;
import com.ez.common.file.reader.spi.Excel2007Reader;
import com.ez.common.file.reader.spi.ExcelReader;
import com.google.common.base.Throwables;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassName: FileProcessUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月15日 下午6:13:21 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class FileProcessUtil {
    public static FileProcess getFileProcess(String filepath) {
        String prefix = getPrefix(filepath).toLowerCase();
        if (prefix.equals("xls")) {
            return new ExcelReader(filepath);
        } else if (prefix.equals("xlsx")) {
            return new Excel2007Reader(filepath);
        } else if (prefix.equals("dbf")) {
            return new DbfReader(filepath);
        } else if (prefix.equals("csv") || prefix.equals("txt")) {
            return new CsvReader(filepath);
        } else {
            Throwables.propagate(
                    new RuntimeException(String.format("没有找到处理%s文件的处理器", filepath)));
            return null;
        }
    }

    public static String getPrefix(String filepath) {
        Path path = Paths.get(filepath);
        String fileName = path.getFileName().toString();
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return prefix;
    }
}
