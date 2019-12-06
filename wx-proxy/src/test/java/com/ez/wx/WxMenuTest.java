package com.ez.wx;

import com.ez.common.httpclient.HCUtils;
import com.ez.common.httpclient.RequestResult;
import com.ez.common.json.Json2;
import com.ez.common.wx.WxConfig;
import com.ez.common.wx.bean.WxConsts;
import com.ez.wx.service.WxAccessTokenService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * ClassName: CreateWxMenuTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-5 下午1:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxProxyApplication.class)
public class WxMenuTest {

    @Autowired
    private WxAccessTokenService accessTokenService;

    @Test
    public void getAccesToken() throws Exception{
        String accessToken = accessTokenService.fetchAccessToken();
        System.out.println(accessToken);
    }

    @Test
    public void getMenu() throws Exception {
        WxConfig wxConfig = WxConfig.getInstance();
        Assert.assertTrue("AccessToken已超时", !wxConfig.isAccessTokenExpired());

        System.out.println(wxConfig.getAccessToken());
        String url = WxConsts.URL_GET_MENU.replace("ACCESS_TOKEN", wxConfig.getAccessToken());
        System.out.println(url);
        HttpGet get = new HttpGet(url);
        get.addHeader("Content-type", "application/json; charset=utf-8");
        HCUtils hcUtils = HCUtils.createDefault();
        RequestResult result = hcUtils.exec(get);

        System.out.println(result.getContent());
    }

    @Test
    public void createTFKMenu() throws Exception {
        //罗凯的公众号菜单
        Map<String, Object> menu1 = Maps.newHashMap();
        menu1.put("type", "view");
        menu1.put("name", "注册");
        menu1.put("url", "http://www.tfkclass.com/wxproxy/menu/student-bound");

        Map<String, Object> menu2 = Maps.newHashMap();
        menu2.put("type", "click");
        menu2.put("name", "辅导册");
        menu2.put("key", "student_pdf_report_001");

        Map<String, Object> menu3 = Maps.newHashMap();
        menu3.put("type", "view");
        menu3.put("name", "订购");
        menu3.put("url", "http://www.tfkclass.com/wxproxy/menu/pay");

        List<Map<String, Object>> mainMenus = Lists.newArrayList();
        mainMenus.add(menu1);
        mainMenus.add(menu2);
        mainMenus.add(menu3);

        Map<String, Object> mainMenu = Maps.newHashMap();
        mainMenu.put("button", mainMenus);

        String json = Json2.toJson(mainMenu);
        System.out.println(json);
        create(json);
    }

    private void create(String json)throws Exception{
        WxConfig wxConfig = WxConfig.getInstance();
        Assert.assertTrue("AccessToken已超时", !wxConfig.isAccessTokenExpired());
        String url = WxConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN", wxConfig.getAccessToken());
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");

        HCUtils hcUtils = HCUtils.createDefault();
        RequestResult result = hcUtils.exec(post);

        System.out.println(result.getContent());
    }

    @Test
    public void createMenu() throws Exception {
        Map<String, Object> menu1 = Maps.newHashMap();
        menu1.put("type", "click");
        menu1.put("name", "测试1");
        menu1.put("key", "liuyu_001");

        List<Map<String, Object>> menuMgr2 = Lists.newArrayList();
        Map<String, Object> menu21 = Maps.newHashMap();
        menu21.put("type", "view");
        menu21.put("name", "微社区");
        menu21.put("url", "http://www.qq.com");
        menuMgr2.add(menu21);

        Map<String, Object> menu22 = Maps.newHashMap();
        menu22.put("type", "view");
        menu22.put("name", "本地服务");
        menu22.put("url", "http://ks.easytnt.com/wx/report");
        menuMgr2.add(menu22);


        Map<String, Object> menu2 = Maps.newHashMap();
        menu2.put("name", "测试2");
        menu2.put("sub_button", menuMgr2);

        List<Map<String, Object>> mainMenus = Lists.newArrayList();
        mainMenus.add(menu1);
        mainMenus.add(menu2);

        Map<String, Object> mainMenu = Maps.newHashMap();
        mainMenu.put("button", mainMenus);

        String json = Json2.toJson(mainMenu);
        System.out.println(json);

        WxConfig wxConfig = WxConfig.getInstance();
        Assert.assertTrue("AccessToken已超时", !wxConfig.isAccessTokenExpired());

        String url = WxConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN", wxConfig.getAccessToken());
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");

        HCUtils hcUtils = HCUtils.createDefault();
        RequestResult result = hcUtils.exec(post);

        System.out.println(result.getContent());

    }

    @Test
    public void createMenu2() throws Exception {
//        Map<String, Object> menu1 = Maps.newHashMap();
//        menu1.put("type", "view");
//        menu1.put("name", "查询成绩");
//        menu1.put("url", "http://ks.easytnt.com/wx/menu/student-cj");

        Map<String, Object> menu2 = Maps.newHashMap();
        menu2.put("type", "view");
        menu2.put("name", "我");
        menu2.put("url", "http://www.easytnt.com/twxproxy/menu/student-bound");


        List<Map<String, Object>> mainMenus = Lists.newArrayList();
//        mainMenus.add(menu1);
        mainMenus.add(menu2);

        Map<String, Object> mainMenu = Maps.newHashMap();
        mainMenu.put("button", mainMenus);

        String json = Json2.toJson(mainMenu);
        System.out.println(json);

        WxConfig wxConfig = WxConfig.getInstance();
        Assert.assertTrue("AccessToken已超时", !wxConfig.isAccessTokenExpired());

        String url = WxConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN", wxConfig.getAccessToken());
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");

        HCUtils hcUtils = HCUtils.createDefault();
        RequestResult result = hcUtils.exec(post);

        System.out.println(result.getContent());

    }
}
