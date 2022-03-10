package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.ProcurementRefund;
import com.zhumeijia.wuye.service.ProcurementRefundService;
import com.zhumeijia.wuye.service.User_Payment_RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Procurement_RefundController {
    @Autowired
    ProcurementRefundService service;

    @GetMapping("/api/getAllProcurementRefund")
    public ResBody getAllProcurementRefunds(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<ProcurementRefund> list= service.getAllProcurementRefund(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
    
    
    @GetMapping("/api/findProcurementRefundByStatus")
    public ResBody findProcurementRefund(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<ProcurementRefund> list= service.findProcurementRefund(page, limit,Integer.valueOf(name));
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

}
