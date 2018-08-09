package com.ez.wx.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.spring.SpringContextUtil;
import com.ez.common.util.FileUtil;
import com.ez.common.util.IdGenerator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UploadFileController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 上午9:19 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@Controller
public class UploadFileController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse responese) throws Exception {
        log.debug("file upload");
        // String a = request.getParameter("a");

//        Path dirPath = checkAndCreateUploadFileDir("");

        IdGenerator idGenerator = SpringContextUtil.getBean("idGenerator");
        List<Map<String, String>> filesMap = Lists.newArrayList();
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iter = mRequest.getFileNames();
        while (iter.hasNext()) {
            String filesName = iter.next();
            List<MultipartFile> files = mRequest.getFiles(filesName);
            for (MultipartFile oldFile : files) {
                String originalFilename = oldFile.getOriginalFilename();
                String suffix = FileUtil.fileSuffix(originalFilename);
                String newFileName = idGenerator.nextId() + suffix;
//                File newFile = dirPath.resolve(newFileName).toFile();
//                FileUtils.copyInputStreamToFile(oldFile.getInputStream(), newFile);
                Map<String, String> fileMap = Maps.newHashMap();
                fileMap.put("old", originalFilename);
                fileMap.put("new", newFileName);
                filesMap.add(fileMap);
            }
        }
        return ModelAndViewFactory.instance("/uploadData").with("files", filesMap).build();
    }

    private Path checkAndCreateUploadFileDir(String uploadFileDir) {
        String dir = uploadFileDir;
        Path path = Paths.get(dir);
        File dirFile = path.toFile();
        FileUtil.dirNotExistAndCreate(dirFile);
        return path;
    }
}
