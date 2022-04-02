package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.bean.Procurement;
import com.zhumeijia.wuye.service.ProcurementService;
import com.zhumeijia.wuye.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProcurementController {
    @Autowired
    ProcurementService service;
    @Autowired
    RoleService rservice;
    private static final Logger LOG = LoggerFactory.getLogger(ProcurementController.class);
    @GetMapping("/api/getAllProcurement")
    public ResBody getAllprocurement(@RequestParam int page,
                                        @RequestParam int limit, HttpSession session) {

        int count = service.getCount();
        List<Procurement> list= service.getAllProcurement(page, limit);
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
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

    @GetMapping("/api/findProcurement")
    public ResBody findProcurement(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name,HttpSession session) {
        if(name.isEmpty())
        {
            int count = service.getCount();
            List<Procurement> list= service.getAllProcurement(page, limit);
            ResBody resBody = new ResBody();
            resBody.setCount(count);
            resBody.setData(list);
            resBody.setCode(0);
            return resBody;
        }
        int count = 0;
        List<Procurement> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        count = service.getCount(Integer.valueOf(name));
        list= service.getAllProcurement(page, limit,Integer.valueOf(name));
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


    @GetMapping("/api/queren")
    public ResBody queren(@RequestParam int id,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int i = service.queren(id);
            if (i == 1){
                resBody.setCode(200);
                resBody.setMsg("成功");
            }else {
                resBody.setCode(500);
                resBody.setMsg("失败");
            }
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看! 非物料管理员不能确认");
        }
        return resBody;
    }
}
