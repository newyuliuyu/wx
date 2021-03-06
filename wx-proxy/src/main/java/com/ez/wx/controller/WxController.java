package com.ez.wx.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.wx.bean.WxXmlMessage;
import com.ez.common.wx.xml.XStreamTransformer;
import com.ez.wx.bean.WxRegister;
import com.ez.wx.message.process.WxMessageHandlerMapping;
import com.ez.wx.message.process.WxMessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * ClassName: WxController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-26 下午4:44 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Controller
@RequestMapping("/wx")
@Slf4j
public class WxController {
    @RequestMapping(method = RequestMethod.GET)
    public void wxRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("wx get.....");
        String value = WxRegister.create(WxRegister::new, request).validate().getResult();
        PrintWriter out = response.getWriter();
        out.print(value);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView receiveMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WxMessageHandlerMapping mapping = WxMessageHandlerMapping.initInstance("com.ez.wx.message.processor");
        WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
        log.debug("接受消息：\n " + wx.toString());
        String xml = WxMessageProcessor.create(wx).process().getXml();

//        if (wx.getContent() != null && "你好".equals(wx.getContent().trim())) {
//            WxXmlOutTextMessage outmsg = WxXmlOutMessage.TEXT().content("你好!").toUser(wx.getFromUserName()).fromUser(wx.getToUserName()).build();
//            xml = outmsg.toXml();
//        } else if (wx.getContent() != null && "网页授权".equals(wx.getContent().trim())) {
//            WxConfig wxConfig = WxConfig.getInstance();
//            String msg = "OAuth2.0网页授权演示 \n" +
//                    "<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxConfig.getAppId() +
//                    "&redirect_uri=http://ks.easytnt.com/wx/oauth2/user-info&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\">点击这里体验v1.0</a>";
//            WxXmlOutTextMessage outmsg = WxXmlOutMessage.TEXT().content(msg).toUser(wx.getFromUserName()).fromUser(wx.getToUserName()).build();
//            xml = outmsg.toXml();
//        } else {
//            WxXmlOutTextMessage outmsg = WxXmlOutMessage.TEXT().content("你好!").toUser(wx.getFromUserName()).fromUser(wx.getToUserName()).build();
//            xml = outmsg.toXml();
//        }
        log.debug("发送消息：\n " + xml);
        return ModelAndViewFactory.instance("wx/wx").with("text", xml).build();
    }
}
