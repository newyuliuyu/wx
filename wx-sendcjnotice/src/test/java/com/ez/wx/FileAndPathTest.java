package com.ez.wx;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassName: FileAndPathTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 上午9:58 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class FileAndPathTest {

    @Test
    public void pathTest() throws Exception {
        URL url = Resources.getResource("");
        Path path = Paths.get(url.toURI());

        log.debug("path.isAbsolute:{}", path.isAbsolute());
        log.debug("path.toString:{}", path);
        log.debug("path.getParent:{}", path.getParent());
        log.debug("path.getRoot:{}", path.getRoot());
        log.debug("path.resolve(a):{}", path.resolve("a"));
        log.debug("path.resolve(/a):{}", path.resolve("/a"));
        log.debug("path.resolveSibling(a):{}", path.resolveSibling("a"));
        log.debug("path.resolveSibling(/a):{}", path.resolveSibling("/a"));
        log.debug("path.relativize(a):{}", path.relativize(Paths.get("/home/liuyu")));

    }

    @Test
    public void createPath() throws Exception {

        Path path = Paths.get("upload");
        log.debug(path.toAbsolutePath().toString());
//        path.toFile().mkdirs();

    }
}
