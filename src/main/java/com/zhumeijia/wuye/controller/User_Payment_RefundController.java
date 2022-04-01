package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.UserPaymentRefund;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.RoleService;
import com.zhumeijia.wuye.service.User_Payment_RefundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class User_Payment_RefundController {
    @Autowired
    User_Payment_RefundService service;
    @Autowired
    RoleService rservice;
    private static final Logger LOG = LoggerFactory.getLogger(User_Payment_RefundController.class);
    @GetMapping("/api/getAllRefunduser")
    public ResBody getAllUserPaymentRefunds(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<UserPaymentRefund> list= service.getAllUserPaymentRefund(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
    
    
    @GetMapping("/api/findRefunduserBystatus")
    public ResBody findUserPaymentRefund(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<UserPaymentRefund> list= service.findUserPaymentRefund(page, limit,Integer.valueOf(name));
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

}
