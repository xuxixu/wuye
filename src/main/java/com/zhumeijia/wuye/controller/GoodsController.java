package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.Goods;
import com.zhumeijia.wuye.service.GoodsService;
import com.zhumeijia.wuye.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    GoodsService service;
    @Autowired
    RoleService rservice;
    private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);
    @GetMapping("/api/getAllGoods")
    public ResBody getAllGoodss(@RequestParam int page,
                                @RequestParam int limit, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {

        int count = service.getCount();
        List<Goods> list= service.getAllGoods(page, limit);
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

    @PostMapping("/api/shenqing")
    public ResBody shenqing(@RequestBody Goods goods, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {

        int i = service.shenqing(goods);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("申请成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("申请失败");
        }
               }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看! 非物料管理员不能申请");
        }
        return resBody;
    }

    @PostMapping("/api/caiyong")
    public ResBody caiyong(@RequestBody Goods goods, HttpSession session) {

        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int i = service.caiyong(goods);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("申请成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("申请失败");
        }
               }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看! 非物料管理员不能申请");
        }
        return resBody;
    }


    @GetMapping("/api/findGoods")
    public ResBody findGoods(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        System.out.println(name);
        int count = service.getCount(name);
        List<Goods> list= service.findGoods(page, limit,name);
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

    @GetMapping("/ajax/getAllGoodss")
    public ResBody getAllGoodss( HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        List<Goods> list= service.getAllGoodss();
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
