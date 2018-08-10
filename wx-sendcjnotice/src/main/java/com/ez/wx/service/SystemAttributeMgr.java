/**
 * Project Name:easydata.web
 * File Name:SystemAttributeMgr.java
 * Package Name:com.ez.data.service.impl
 * Date:2017年3月21日下午3:37:48
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.ez.wx.service;

import com.ez.business.bean.SystemAttribute;
import com.ez.business.bean.SystemAttributeKey;
import com.ez.business.dao.SystemAttributeDao;
import com.ez.common.spring.SpringContextUtil;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: SystemAttributeMgr <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月21日 下午3:37:48 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class SystemAttributeMgr {
    private Logger logger = LoggerFactory.getLogger(SystemAttributeMgr.class);
    private static SystemAttributeMgr that = new SystemAttributeMgr();
    private Map<String, SystemAttribute> systemAttributeMap = Maps.newHashMap();
    private Lock lock = new ReentrantLock();

    private void laod() {
        SystemAttributeDao systemAttributeDao = SpringContextUtil.getBean("systemAttributeDao");
        List<SystemAttribute> systemAttributes = systemAttributeDao.list();
        for (SystemAttribute systemAttribute : systemAttributes) {
            systemAttributeMap.put(systemAttribute.getAttrName(), systemAttribute);
        }
        try {
            SystemAttribute systemAttribute = SpringContextUtil.getBean("sysVersion");
            systemAttributeMap.put(systemAttribute.getAttrName(), systemAttribute);
        } catch (Exception e) {
            log.warn("系统版本好没有找到", e);
        }

    }

    public SystemAttribute get(SystemAttributeKey SystemAttributeKey) {
        lock.lock();
        try {
            if (systemAttributeMap.isEmpty()) {
                laod();
            }
        } finally {
            lock.unlock();
        }

        return systemAttributeMap.get(SystemAttributeKey.toString());
    }

    public String getValue(SystemAttributeKey SystemAttributeKey) {
        SystemAttribute systemAttribute = get(SystemAttributeKey);
        if (systemAttribute != null) {
            return systemAttribute.getValue();
        } else {
            return "";
        }
    }

    public String getPathValue(SystemAttributeKey SystemAttributeKey) {
        SystemAttribute systemAttribute = get(SystemAttributeKey);
        if (systemAttribute != null) {
            String rootPath = systemAttribute.getValue();
            if (rootPath.endsWith("/") || rootPath.endsWith("\\")) {
                rootPath = rootPath.substring(0, rootPath.length() - 1);
            }
            return rootPath;
        } else {
            return "";
        }
    }

    public void reload() {
        logger.debug("从新加载系统信息");
        lock.lock();
        try {
            laod();
        } finally {
            lock.unlock();
        }
        logger.debug("从新加载系统信息完毕");
    }

    public List<SystemAttribute> list() {
        return Lists.newArrayList(systemAttributeMap.values());
    }

    public String getSavePath() {
        SystemAttribute systemAttribute = get(SystemAttributeKey.SAVE_PATH);
        String saveDir = systemAttribute.getValue();
        if (saveDir == null || saveDir.equals("")) {
            Throwables.propagate(new RuntimeException("保存报名库的文件目录不存在"));
        }
        char lastChar = saveDir.charAt(saveDir.length() - 1);
        if (lastChar != '/') {
            saveDir = saveDir + "/";
        }
        return saveDir;
    }

    public static SystemAttributeMgr newInstance() {
        return that;
    }
}
