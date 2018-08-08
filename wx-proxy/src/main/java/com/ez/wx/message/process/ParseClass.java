package com.ez.wx.message.process;

import com.ez.common.util.ThrowableToString;
import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.bean.WxXmlOutMessage;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.ClassReader;
import org.springframework.core.io.Resource;

import java.lang.reflect.Method;

/**
 * ClassName: ParseClass <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-30 下午2:29 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class ParseClass {

    private Class objClass;
    private Class msgValidateClass;
    private Class eventValidateClass;
    private WxMsg wxMsg;
    private WxEvent wxEvent;

    private MessageProcessDefine msgProcessDefine = new MessageProcessDefine();
    private MessageProcessDefine eventProcessDefine = new MessageProcessDefine();

    public ParseClass(String objClass) {
        try {
            this.objClass = Class.forName(objClass);
        } catch (Exception e) {
            Throwables.propagate(e);
            log.error(ThrowableToString.toString(e));
        }
        parse();
    }

    public ParseClass(Resource rs) {
        try {
            ClassReader classReader = new ClassReader(rs.getInputStream());
            String className = classReader.getClassName();
            className = className.replaceAll("/", "\\.");
            this.objClass = Class.forName(className);
        } catch (Exception e) {
            Throwables.propagate(e);
            log.error(ThrowableToString.toString(e));
        }
        parse();
    }

    public boolean hasValidClass() {
        if (wxMsg == null && wxEvent == null) {
            return false;
        }
        return true;
    }

    public boolean hasEventClass() {
        if (wxEvent == null) {
            return false;
        }
        return true;
    }

    public boolean hasMsgClass() {
        if (wxMsg == null) {
            return false;
        }
        return true;
    }

    public MessageProcessDefine getMsgDefine() {
        return msgProcessDefine;
    }

    public MessageProcessDefine getEventDefine() {
        return eventProcessDefine;
    }


    private void parse() {
        wxMsg = (WxMsg) objClass.getAnnotation(WxMsg.class);
        wxEvent = (WxEvent) objClass.getAnnotation(WxEvent.class);
        if (wxMsg != null) {
            parseWxMsg();
        }
        if (wxEvent != null) {
            parseWxEvent();
        }
        if (wxMsg != null || wxEvent != null) {
            parseObjClass();
        }
    }

    private void parseWxMsg() {
        String msgType = wxMsg.value();
        msgValidateClass = wxMsg.validateClass();
        String validateInvokeMethod = wxMsg.validateInvokeMethod();
        validateInvokeMethod = validateInvokeMethod.equals("") ? "match" : validateInvokeMethod;
        if (hasMethod(msgValidateClass, validateInvokeMethod, WxXmlMessage.class)) {
            msgProcessDefine.setMsgType(msgType);
            msgProcessDefine.setValidateClass(msgValidateClass);
            msgProcessDefine.setValidateInvokeMethod(validateInvokeMethod);
        } else {
            Throwables.propagate(new RuntimeException(msgValidateClass + "的方法" + validateInvokeMethod + "不是这样xx(WxXmlMessage)的方法"));
        }
    }

    private void parseWxEvent() {
        String event = wxEvent.value();
        eventValidateClass = wxEvent.validateClass();
        String validateInvokeMethod = wxEvent.validateInvokeMethod();
        validateInvokeMethod = validateInvokeMethod.equals("") ? "match" : validateInvokeMethod;
        if (hasMethod(eventValidateClass, validateInvokeMethod, WxXmlMessage.class)) {
            eventProcessDefine.setMsgType("event");
            eventProcessDefine.setEvent(event);
            eventProcessDefine.setValidateClass(eventValidateClass);
            eventProcessDefine.setValidateInvokeMethod(validateInvokeMethod);
        } else {
            Throwables.propagate(new RuntimeException(eventValidateClass + "的方法" + validateInvokeMethod + "不是这样xx(WxXmlMessage)的方法"));
        }
    }

    private void parseObjClass() {
        Method[] methods = objClass.getDeclaredMethods();
        Method invokeMethod = null;
        for (Method method : methods) {
            WxProcessInvokeMethod annotation = method.getAnnotation(WxProcessInvokeMethod.class);
            if (annotation != null) {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == 1 && paramTypes[0].equals(WxXmlMessage.class)) {
                    invokeMethod = method;
                    break;
                }
            }
        }

        try {
            if (invokeMethod == null && hasMethod(objClass, "process", WxXmlMessage.class)) {
                invokeMethod = objClass.getDeclaredMethod("process", WxXmlMessage.class);
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }

        if (invokeMethod == null) {
            Throwables.propagate(new RuntimeException(objClass.getName() + "没有执行方法，执行方法必须为xx(WxXmlMessage)"));
        }
        Class returnType = invokeMethod.getReturnType();
        if (!WxXmlOutMessage.class.isAssignableFrom(returnType)) {
            Throwables.propagate(new RuntimeException(objClass.getName() + "的" + invokeMethod.getName() + "方法返回值必须为" + WxXmlOutMessage.class + "的子类"));
        }

        msgProcessDefine.setProcessClass(objClass);
        msgProcessDefine.setProcessInvokeMethod(invokeMethod.getName());

        eventProcessDefine.setProcessClass(objClass);
        eventProcessDefine.setProcessInvokeMethod(invokeMethod.getName());
    }

    private boolean hasMethod(Class myclass, String methodName, Class<?>... parameterTypes) {
        try {
            Method method = myclass.getDeclaredMethod(methodName, parameterTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Object getMsgObj() {
        try {
            return msgValidateClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getEventObj() {
        try {
            return eventValidateClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getObj() {
        try {
            return objClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
