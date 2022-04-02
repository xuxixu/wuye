package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.ProcurementRefund;
import com.zhumeijia.wuye.service.ProcurementRefundService;
import com.zhumeijia.wuye.service.RoleService;
import com.zhumeijia.wuye.service.User_Payment_RefundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class Procurement_RefundController {
    @Autowired
    ProcurementRefundService service;
    @Autowired
    RoleService rservice;
    private static final Logger LOG = LoggerFactory.getLogger(Procurement_RefundController.class);
    @GetMapping("/api/getAllProcurementRefund")
    public ResBody getAllProcurementRefunds(@RequestParam int page,
                                            @RequestParam int limit, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int count = service.getCount();
        List<ProcurementRefund> list= service.getAllProcurementRefund(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看! 非物料管理员不能查看");
        }
        return resBody;
    }
    
    
    @GetMapping("/api/findProcurementRefundByStatus")
    public ResBody findProcurementRefund(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int count = service.getCount(name);
        List<ProcurementRefund> list= service.findProcurementRefund(page, limit,Integer.valueOf(name));
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看! 非物料管理员不能查看");
        }
        return resBody;
    }

}
