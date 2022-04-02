package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.GoodsTypeService;
import com.zhumeijia.wuye.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class GoodsTypeController {
    @Autowired
    GoodsTypeService service;
    @Autowired
    RoleService rservice;

    private static final Logger LOG = LoggerFactory.getLogger(GoodsTypeController.class);
    @GetMapping("/api/getAllGoodstype")
    public ResBody getAllGoodstypes(@RequestParam int page,
                                    @RequestParam int limit, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int count = service.getCount();
            List<Goodstype> list= service.getAllGoodstype(page, limit);
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

    @PostMapping("/api/addGoodstype")
    public ResBody addBuilding(@RequestBody Goodstype goodstype,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int i = service.addGoodstype(goodstype);
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

    @PostMapping("/api/updateGoodstype")
    public ResBody updateGoodstype(@RequestBody Goodstype goodstype,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int i = service.updateGoodstype(goodstype);
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
            resBody.setMsg("不能查看! 非物料管理员不能添加");
        }
        return resBody;
    }

    @GetMapping("/api/delGoodstype")
    public ResBody delGoodstype(@RequestParam int id,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        int i = service.delGoodstype(id);
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

    @GetMapping("/api/findGoodstype")
    public ResBody findGoodstype(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {

        int count = service.getCount(name);
        List<Goodstype> list= service.findGoodstype(page, limit,name);
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

    @GetMapping("/ajax/getAllGoodstypes")
    public ResBody getAllGoodstypes(HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("物料管理员") || rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {

            List<Goodstype> list= service.getAllGoodstypes();
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
