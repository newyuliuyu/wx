package com.ez.common.wx.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.InputStream;
import java.io.Writer;
import java.util.Map;

/**
 * 指定类的专属XStream
 *
 * @author antgan
 */
public class XStreamTransformer {

    protected static final Map<Class, XStream> CLASS_2_XSTREAM_INSTANCE = XStreamConfig.getConfig();

    /**
     * xml -> pojo
     *
     * @param clazz
     * @param xml
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(Class<T> clazz, String xml) {
        XStream xstream = getXStream(clazz);
        T object = (T) xstream.fromXML(xml);
        return object;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXml(Class<T> clazz, InputStream is) {
        XStream xstream = getXStream(clazz);
        T object = (T) xstream.fromXML(is);
        return object;
    }

    public static <T> XStream getXStream(Class<T> clazz) {
        XStream xstream = CLASS_2_XSTREAM_INSTANCE.get(clazz);
        if (xstream == null) {

            XStreamInitializer.getInstance();
            xstream = new XStream(new XppDriver(new NoNameCoder()) {

                @Override
                public HierarchicalStreamWriter createWriter(Writer out) {
                    return new PrettyPrintWriter(out) {
                        // 对所有xml节点的转换都增加CDATA标记
                        boolean cdata = false;

                        @Override
                        @SuppressWarnings("rawtypes")
                        public void startNode(String name, Class clazz) {
                            super.startNode(name, clazz);
                            if (Integer.class.equals(clazz)) {
                                cdata = false;
                            }
                        }

                        @Override
                        public String encodeNode(String name) {
                            return name;
                        }


                        @Override
                        protected void writeText(QuickWriter writer, String text) {
                            if (cdata) {
                                writer.write("<![CDATA[");
                                writer.write(text);
                                writer.write("]]>");
                            } else {
                                writer.write(text);
                            }
                        }
                    };
                }
            });
            xstream.processAnnotations(clazz);
            xstream.ignoreUnknownElements();//忽视null的节点
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
            xstream.setClassLoader(XStreamTransformer.class.getClassLoader());
        }
        return xstream;
    }

    /**
     * pojo -> xml
     *
     * @param clazz
     * @param object
     * @return
     */
    public static <T> String toXml(Class<T> clazz, T object) {
        XStream xstream = getXStream(clazz);
        return xstream.toXML(object);
    }


}
