package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Warehouse;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.RoleService;
import com.zhumeijia.wuye.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
public class WarehouseController {
    @Autowired
    WarehouseService service;
    @Autowired
    RoleService rservice;
    private static final Logger LOG = LoggerFactory.getLogger(WarehouseController.class);
    @GetMapping("/api/getAllWarehouse")
    public ResBody getAllWarehouses(@RequestParam int page,
                                    @RequestParam int limit, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int count = service.getCount();
            List<Warehouse> list= service.getAllWarehouse(page, limit);
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

    @PostMapping("/api/addWarehouse")
    public ResBody addBuilding(@RequestBody Warehouse warehouse,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int i = service.addWarehouse(warehouse);
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
            resBody.setMsg("不能查看! 非物料管理员不能添加");
        }
        return resBody;
    }

    @PostMapping("/api/updateWarehouse")
    public ResBody updateWarehouse(@RequestBody Warehouse warehouse,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int i = service.updateWarehouse(warehouse);
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
            resBody.setMsg("不能查看! 非物料管理员不能修改");
        }
        return resBody;
    }

    @GetMapping("/api/delWarehouse")
    public ResBody delWarehouse(@RequestParam int id,HttpSession session) {
        ResBody resBody = new ResBody();
            Admin admin = (Admin) session.getAttribute("admin");
            if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
            {
        int i = service.delWarehouse(id);
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
                resBody.setMsg("不能查看! 非物料管理员不能删除");
            }
        return resBody;
    }

    @GetMapping("/api/findWarehouse")
    public ResBody findWarehouse(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name,HttpSession session) {
        ResBody resBody = new ResBody();
                Admin admin = (Admin) session.getAttribute("admin");
                if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
                {
        int count = service.getCount(name);
        List<Warehouse> list= service.findWarehouse(page, limit,name);
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

    @GetMapping("/ajax/getAllWarehouses")
    public ResBody getAllWarehouses(HttpSession session) {
        ResBody resBody = new ResBody();
                    Admin admin = (Admin) session.getAttribute("admin");
                    if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
                    {
        List<Warehouse> list= service.getAllWarehouses();
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
