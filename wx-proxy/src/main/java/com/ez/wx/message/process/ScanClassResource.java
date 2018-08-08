package com.ez.wx.message.process;

import com.google.common.base.Throwables;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * ClassName: ScanClassResource <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午2:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ScanClassResource {
    private final String pkgSplitSymbol = "\\.";
    private final String pathSplitSymbol = "/";
    private final String scanPathPrefix = "classpath*:";
    private final String scanPathSuffix = "/**/*.class";
    private String pkg;

    public ScanClassResource(String pkg) {
        this.pkg = pkg;
    }

    public Resource[] doScan() {
        String scanPackage = parsePackage();
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resourceResolver.getResources(scanPackage);
        } catch (Exception e) {
            Throwables.propagate(e);
        }
        return resources;
    }

    private String parsePackage() {
        String scanPackage = pkg.replaceAll(pkgSplitSymbol, pathSplitSymbol);
        return scanPathPrefix + scanPackage + scanPathSuffix;
    }
}
