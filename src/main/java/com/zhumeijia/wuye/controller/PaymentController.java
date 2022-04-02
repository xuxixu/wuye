package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.bean.Payment;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.PaymentService;
import com.zhumeijia.wuye.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    PaymentService service;
    @Autowired
    RoleService rservice;
    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
    @GetMapping("/api/getAllPayments")
    public ResBody getAllPayments(@RequestParam int page,
                                   @RequestParam int limit, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("记费员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员")) {
            int count = service.getCount();
            List<Payment> list = service.getAllPayments(page, limit);
            resBody.setCount(count);
            resBody.setData(list);
            resBody.setCode(0);
        }else
            {
                resBody.setCode(500);
                resBody.setMsg("不能查看! 非记费员或非管理员不能查看");
            }
        return resBody;
    }

    @PostMapping("/api/addPayment")
    public ResBody addBuilding(@RequestBody Payment payment, HttpSession session) {
        ResBody resBody = new ResBody();
            Admin admin = (Admin) session.getAttribute("admin");
            if( rservice.findRoleById(admin.getRid()).getName().equals("记费员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
            {
        int i = service.addPayment(payment);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
            }else
            {
                resBody.setCode(500);
                resBody.setMsg("不能添加! 非记费员或非管理员不能增加");
            }
        return resBody;
    }

    @PostMapping("/api/updatePayment")
    public ResBody updatePayment(@RequestBody Payment payment, HttpSession session) {
        ResBody resBody = new ResBody();
                Admin admin = (Admin) session.getAttribute("admin");
                if( rservice.findRoleById(admin.getRid()).getName().equals("记费员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
                {
        int i = service.updatePayment(payment);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
                }else
                {
                    resBody.setCode(500);
                    resBody.setMsg("不能修改! 非记费员或非管理员不能修改");
                }
        return resBody;
    }

    @GetMapping("/api/delPayment")
    public ResBody delPayment(@RequestParam int id, HttpSession session) {
        ResBody resBody = new ResBody();
                    Admin admin = (Admin) session.getAttribute("admin");
                    if( rservice.findRoleById(admin.getRid()).getName().equals("记费员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
                    {
        int i = service.delPayment(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
                    }else
                    {
                        resBody.setCode(500);
                        resBody.setMsg("不能删除! 非记费员或非管理员不能删除");
                    }
        return resBody;
    }

    @GetMapping("/api/findPayment")
    public ResBody findPayment(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("记费员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int count = service.getCount(name);
        List<Payment> list= service.findPayment(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看! 非记费员或非管理员不能查看");
        }
        return resBody;
    }

    @GetMapping("/ajax/getAllPayments")
    public ResBody getAllPayments() {
        ResBody resBody = new ResBody();
        List<Payment> list= service.getAllPayments();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
