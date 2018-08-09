package com.ez.common.util;

import java.io.File;

/**
 * ClassName: FileUtil <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 上午9:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class FileUtil {

    public static boolean dirNotExistAndCreate(File file){
        if(file.isDirectory() && file.exists()){
            file.mkdirs();
        }
        return true;
    }

    public static String fileSuffix(String fileName) {
        int idx = fileName.lastIndexOf('.');
        return fileName.substring(idx);
    }
}
