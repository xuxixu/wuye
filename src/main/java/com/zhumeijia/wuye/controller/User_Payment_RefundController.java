package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.UserPaymentRefund;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.RoleService;
import com.zhumeijia.wuye.service.User_Payment_RefundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
                                            @RequestParam int limit, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("记费员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int count = service.getCount();
        List<UserPaymentRefund> list= service.getAllUserPaymentRefund(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能账单! 非记费员或非管理员不能查看账单");
        }
        return resBody;
    }
    
    
    @GetMapping("/api/findRefunduserBystatus")
    public ResBody findUserPaymentRefund(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("记费员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int count = service.getCount(name);
        List<UserPaymentRefund> list= service.findUserPaymentRefund(page, limit,Integer.valueOf(name));
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能账单! 非记费员或非管理员不能账单账单");
        }
        return resBody;
    }

}
