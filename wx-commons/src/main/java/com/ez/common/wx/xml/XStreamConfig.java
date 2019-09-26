package com.ez.common.wx.xml;

import com.ez.common.wx.bean.*;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;

import java.util.Map;

/**
 * ClassName: XStreamConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-1 下午5:05 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class XStreamConfig {
    public static Map<Class, XStream> getConfig() {

        Map<Class, XStream> map = Maps.newHashMap();
        map.put(WxXmlMessage.class, config_WxXmlMessage());
        map.put(WxXmlOutNewsMessage.class, config_WxXmlOutNewsMessage());
        map.put(WxXmlOutTextMessage.class, config_WxXmlOutTextMessage());
        map.put(WxXmlOutImageMessage.class, config_WxXmlOutImageMessage());
        map.put(WxXmlOutVideoMessage.class, config_WxXmlOutVideoMessage());
        map.put(WxXmlOutVoiceMessage.class, config_WxXmlOutVoiceMessage());
        return map;
    }




    private static XStream config_WxXmlMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxXmlMessage.class);
        xstream.processAnnotations(WxXmlMessage.ScanCodeInfo.class);
        xstream.processAnnotations(WxXmlMessage.SendPicsInfo.class);
        xstream.processAnnotations(WxXmlMessage.SendPicsInfo.Item.class);
        xstream.processAnnotations(WxXmlMessage.SendLocationInfo.class);
        return xstream;
    }

    private static XStream config_WxXmlOutImageMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxXmlOutMessage.class);
        xstream.processAnnotations(WxXmlOutImageMessage.class);
        return xstream;
    }

    private static XStream config_WxXmlOutNewsMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxXmlOutMessage.class);
        xstream.processAnnotations(WxXmlOutNewsMessage.class);
        xstream.processAnnotations(WxXmlOutNewsMessage.Item.class);
        return xstream;
    }

    private static XStream config_WxXmlOutTextMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxXmlOutMessage.class);
        xstream.processAnnotations(WxXmlOutTextMessage.class);
        return xstream;
    }

    private static XStream config_WxXmlOutVideoMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxXmlOutMessage.class);
        xstream.processAnnotations(WxXmlOutVideoMessage.class);
        xstream.processAnnotations(WxXmlOutVideoMessage.Video.class);
        return xstream;
    }

    private static XStream config_WxXmlOutVoiceMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxXmlOutMessage.class);
        xstream.processAnnotations(WxXmlOutVoiceMessage.class);
        return xstream;
    }
}
