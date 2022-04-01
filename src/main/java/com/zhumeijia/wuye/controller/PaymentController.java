package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.bean.Payment;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.PaymentService;
import com.zhumeijia.wuye.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Payment> list= service.getAllPayments(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addPayment")
    public ResBody addBuilding(@RequestBody Payment payment) {
        ResBody resBody = new ResBody();
        int i = service.addPayment(payment);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updatePayment")
    public ResBody updatePayment(@RequestBody Payment payment) {
        ResBody resBody = new ResBody();
        int i = service.updatePayment(payment);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delPayment")
    public ResBody delPayment(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delPayment(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findPayment")
    public ResBody findPayment(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Payment> list= service.findPayment(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
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
