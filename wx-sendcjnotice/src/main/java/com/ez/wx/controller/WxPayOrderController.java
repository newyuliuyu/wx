package com.ez.wx.controller;

import com.ez.business.bean.WxPayOrder;
import com.ez.business.service.WxBoundStudentService;
import com.ez.business.service.WxPayOrderService;
import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: OrderController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-9-26 下午1:51 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RestController
@RequestMapping("/pay/order")
public class WxPayOrderController {
    @Autowired
    private WxPayOrderService wxPayOrderService;

    @Autowired
    private WxBoundStudentService wxBoundStudentService;


    @Autowired
    private IdGenerator idGenerator;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestBody WxPayOrder wxPayOrder,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        wxPayOrder.setOrderNum(idGenerator.nextId() + "");
        wxPayOrder.setOrderTimestamp(System.currentTimeMillis());
        wxPayOrderService.create(wxPayOrder);
        return ModelAndViewFactory.instance("").with("wxPayOrder", wxPayOrder).build();
    }

    @RequestMapping(value = "/updatepay", method = RequestMethod.POST)
    public ModelAndView updatePayOfWxPayOrder(@RequestBody WxPayOrder wxPayOrder,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        wxPayOrderService.updatePayOfWxPayOrder(wxPayOrder);
        wxBoundStudentService.addStudentInfo(wxPayOrder.getWxopenid());
        return ModelAndViewFactory.instance("").with("wxPayOrder", wxPayOrder).build();
    }
}
