package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Role;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService service;

    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);
    @GetMapping("/api/getAllRoles")
    public ResBody getAllRoles(@RequestParam int page,
                                   @RequestParam int limit,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if(service.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int count = service.getCount();
        List<Role> list= service.getAllRoles(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("非管理员不能查看");
        }
        return resBody;
    }

    @PostMapping("/api/addRole")
    public ResBody addBuilding(@RequestBody Role role, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if(service.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int i = service.addRole(role);
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
            resBody.setMsg("非管理员不能添加");
        }

        return resBody;
    }

    @PostMapping("/api/updateRole")
    public ResBody updateRole(@RequestBody Role role, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if(service.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int i = service.updateRole(role);
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
            resBody.setMsg("非管理员不能修改");
        }

        return resBody;
    }

    @GetMapping("/api/delRole")
    public ResBody delRole(@RequestParam int id, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if(service.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int i = service.delRole(id);
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
            resBody.setMsg("非管理员不能删除");
        }
        return resBody;
    }

    @GetMapping("/api/findRole")
    public ResBody findRole(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if(service.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int count = service.getCount(name);
        List<Role> list= service.findRole(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
    }else
    {
        resBody.setCode(500);
        resBody.setMsg("不能查看!非管理员不能查看");
    }
        return resBody;
    }

    @GetMapping("/ajax/getAllRoles")
    public ResBody getAllRoles(HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if(service.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        List<Role> list= service.getAllRoles();
        resBody.setData(list);
        resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看!非管理员不能查看");
        }
        return resBody;
    }
}
