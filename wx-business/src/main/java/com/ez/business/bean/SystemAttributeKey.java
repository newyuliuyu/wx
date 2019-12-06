/**
 * Project Name:easydata.interface
 * File Name:SystemAttributeKey.java
 * Package Name:com.ez.system
 * Date:2017年3月21日下午4:00:20
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.ez.business.bean;

/**
 * ClassName: SystemAttributeKey <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月21日 下午4:00:20 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public enum SystemAttributeKey {
    Cas("sys.cas"),//认证服务器地址，在登录的时候使用
    CasValidationPath("sys.cas.validation"),//cas验证的地址
    ServerPath("sys.server.path"),//本系统地址
    Name("sys.name"),//系统名称
    VERSION("sys.version"),//系统版本号
    UPLOAD_PATH("sys.upload.datapath"),//文件上传存放目录
    SAVE_PATH("sys.savepath"),//文件处理完毕保存目录
    EZPath("sys.ez"), // 评卷地址,用于表头
    EzbAuth("sys.EzbAuth"), // 角色数据地址，获取用户信息地址
    manualReview("sys.manualReview"), // 手阅地址
    questionBank("sys.questionBank"),// 题库地址
    phantomjsDir("sys.phantomjs.path"),//phantomjs地址，把html转化成图片的工具地址
    tiKuPath("sys.tiku.path"),//题库地址，用于提分报告
    tiKuAppkey("sys.tiku.appKey"),//题库appkey，提分报告中链接题库需要的
    tiKuKey("sys.tiku.key"),//题库密匙，提分报告链接题库需要的
    tiKuVersion("sys.tiku.version"),//题库版本，提分报告链接题库需要的
    pdfPath("sys.pdf.path"),// pdf保存根目录，提分报告pdf生成存放的根目录
    pdfFontPath("sys.pdf.font.path"),// pdf字体文件，pdf生产的是字体文件存放的目录
    reportPath("sys.report.path"),// 报告根目录(网络),通过网路可以访问的pdf文件的根目录地址
    reportNoticeInterfacePath("sys.report.notice.interface.path"),//提分报告通知接口
    reportUploadCmd("sys.report.upload.cmd"),//提分报告上传命令
    reportInterfacePath("sys.report.interface.path"),//报告接口地址，用户扫描提分报告二维码的接口地址（该接口地址在分析报告这边）
    reportExclePath("sys.report.excle.path"),//excle报告生成的跟目录
    studentCjNoticeInterfaceURL("sys.student.cj.notice.interface.url"),//学生成绩同步接口通知url
    studentCjSavePath("sys.student.cj.save.path"),//学生成绩同步成绩生成保存目录
    studentCjFileNetUrl("sys.student.cj.file.net.url"),//学生成绩网络访问路径地址
    studentCjShell("sys.student.cj.shell"),//学生成绩同步脚本
    studentCjServerSSHUrl("sys.student.cj.server.ssh.url"),//链接学生成绩文件服务的ssh链接地址
    studentCjServerSavePath("sys.student.cj.server.save.path"),//学生成绩文件服务保存在文件目录

    wxProxyUrl("wx.proyx.url"),//微信代理程序url
    wxPublishCjUrl("wx.publish.cj.url"),//微信成绩发布程序url
    wxWebUrl("wx.web.url"),//微信web程序url
    studentReportURl("student.report.url"),//学生成绩文件服务保存在文件目录
    noPermissionUrl("no.permission.url"),//无权限提示页面
    studentPdfReportDemoUrl("student.pdf.report.demo.url"),//权限页面
    studentPdfReportUrlPrefix("student.pdf.report.url.prefix");//查看学生报告前最
    private String key;

    private SystemAttributeKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

}
