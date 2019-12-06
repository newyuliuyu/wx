package com.ez.wx.message.process;

import com.ez.common.wx.bean.WxXmlMessage;
import com.google.common.base.Throwables;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.springframework.core.io.Resource;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * ClassName: WxMessageHandlerMapping <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-27 下午4:55 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WxMessageHandlerMapping {

    private static WxMessageHandlerMapping mapping = new WxMessageHandlerMapping();
    private boolean isInit = false;
    private Object monitor = new Object();
    private Map<Class, Object> objMap = Maps.newHashMap();
    private Multimap<String, MessageProcessDefine> mpdMap = HashMultimap.create();
    private String[] packages;

    public WxMessageHandlerMapping() {
    }

    public void setPackages(String... packages) {
        this.packages = packages;
    }

    public boolean isInit() {
        return isInit;
    }

    public static WxMessageHandlerMapping getInstance() {
        if (!mapping.isInit()) {
            Throwables.propagate(new RuntimeException(WxMessageHandlerMapping.class.getName() + "还没有初始化"));
        }
        return mapping;
    }

    public static WxMessageHandlerMapping initInstance(String... packages) {
        mapping.setPackages(packages);
        mapping.init();
        return mapping;
    }

    public void init() {
        synchronized (monitor) {
            if (!isInit) {
                if (packages != null) Arrays.stream(packages).forEach(this::scanPkg);
            }
            isInit = true;
        }
    }

    private void scanPkg(String kpg) {
        Resource[] resources = new ScanClassResource(kpg).doScan();
        if (resources != null) Arrays.stream(resources).forEach(this::parseClass);
    }

    private void parseClass(Resource rs) {
        ParseClass parseClass = new ParseClass(rs);
        if (parseClass.hasMsgClass()) {
            MessageProcessDefine define = parseClass.getMsgDefine();
            mpdMap.put(define.getMsgType(), define);
            initMsgValidateClass(define, parseClass);
            initObjClass(define, parseClass);
        }
        if (parseClass.hasEventClass()) {
            MessageProcessDefine define = parseClass.getEventDefine();
            mpdMap.put(define.getEvent(), define);
            initEventValidateClass(define, parseClass);
            initObjClass(define, parseClass);
        }
    }

    private void initMsgValidateClass(MessageProcessDefine define, ParseClass parseClass) {
        Object obj = objMap.get(define.getValidateClass());
        if (obj == null) {
            objMap.put(define.getValidateClass(), parseClass.getMsgObj());
        }
    }

    private void initEventValidateClass(MessageProcessDefine define, ParseClass parseClass) {
        Object obj = objMap.get(define.getValidateClass());
        if (obj == null) {
            objMap.put(define.getValidateClass(), parseClass.getEventObj());
        }
    }

    private void initObjClass(MessageProcessDefine define, ParseClass parseClass) {
        Object obj = objMap.get(define.getProcessClass());
        if (obj == null) {
            objMap.put(define.getProcessClass(), parseClass.getObj());
        }
    }

    public MsgProcessInvoker findProcess(WxXmlMessage wxXmlMessage) {
        Collection<MessageProcessDefine> defines = null;
        if (!wxXmlMessage.getMsgType().equalsIgnoreCase("event")) {
            defines = mpdMap.get(wxXmlMessage.getMsgType());
        } else {
            defines = mpdMap.get(wxXmlMessage.getEvent());
        }

        MessageProcessDefine goodDefine = findGoodDefine(defines, wxXmlMessage);
        MsgProcessInvoker invoker = null;
        if (goodDefine != null) {
            Object obj = objMap.get(goodDefine.getProcessClass());
            invoker = new MsgProcessInvoker(goodDefine, obj);
        }

        return invoker;
    }

    private MessageProcessDefine findGoodDefine(Collection<MessageProcessDefine> defines, WxXmlMessage wxXmlMessage) {
        MessageProcessDefine result = null;
        try {
            for (MessageProcessDefine define : defines) {
                Object validateObj = objMap.get(define.getValidateClass());

                Method method = validateObj.getClass().getDeclaredMethod(define.getValidateInvokeMethod(), WxXmlMessage.class);
                boolean bool = (Boolean) method.invoke(validateObj, wxXmlMessage);
                if (bool) {
                    result = define;
                    break;
                }
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }
        if (result == null && !defines.isEmpty()) {
            Iterator<MessageProcessDefine> iterator = defines.iterator();
            result = iterator.next();
        }
        return result;
    }

}
