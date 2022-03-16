package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminService service;
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/admin/loginByPassword")
    public ResBody loginByPassword(@RequestBody Map<String, Object> params,
                                   HttpSession session) {
        ResBody resBody = new ResBody();
        String username = params.get("username").toString();
        String password = params.get("password").toString();
        Admin admin = service.findAdmin(username,password);
        if (admin == null){
            resBody.setCode(500);
            resBody.setMsg("登录失败，请重新登录");
        }else {
            session.setAttribute("admin",admin);
            LOG.info(admin.toString());
            resBody.setCode(200);
            resBody.setMsg("登录成功");
        }
        return resBody;
    }

    @PostMapping("/admin/updatePass")
    public ResBody updatePass(@RequestBody Map<String, Object> params,
                              HttpSession session) {
        ResBody resBody = new ResBody();
        String newPsw = params.get("newPsw").toString();
        Admin admin = (Admin) session.getAttribute("admin");
        admin.setPassword(newPsw);
        int i = service.updatePass(admin.getId(),newPsw);
        if (i != 1){
            resBody.setCode(500);
            resBody.setMsg("修改失败，后台出错");
        }else {
            session.setAttribute("admin",admin);
            LOG.info(admin.toString());
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }
        return resBody;
    }

    @GetMapping("/api/getAllAdmins")
    public ResBody getAllAdmins(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Admin> list= service.getAllAdmins(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addAdmin")
    public ResBody addAdmin(@RequestBody Admin building,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if(admin.getRid() == 1)
        {
            int i = service.addAdmin(building);
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
            resBody.setMsg("不能修改! 非管理员不能增加");
        }
        return resBody;
    }

    @PostMapping("/api/updateAdmin")
    public ResBody updateAdmin(@RequestBody Admin building,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if( admin.getRid() == 1)
        {
            int i = service.updateAdmin(building);
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
            resBody.setMsg("不能修改!非管理员不能修改");
        }

        return resBody;
    }

    @GetMapping("/api/delAdmin")
    public ResBody delAdmin(@RequestParam int id,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if(id != admin.getId() && admin.getRid() == 1)
        {
            int i = service.delAdmin(id);
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
            resBody.setMsg("不能删除! 不能删除自己或非管理员不能删除");
        }

        return resBody;
    }

    @GetMapping("/api/findAdmin")
    public ResBody findAdmin(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Admin> list= service.findAdmin(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllAdmins")
    public ResBody getAllAdmins() {
        ResBody resBody = new ResBody();
        List<Admin> list= service.getAllAdmins();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
