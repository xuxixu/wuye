package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.bean.Danyuan;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.BuildingService;
import com.zhumeijia.wuye.service.DanyuanService;
import com.zhumeijia.wuye.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DanyuanController {
    @Autowired
    DanyuanService service;
    @Autowired
    RoleService rservice;
    private static final Logger LOG = LoggerFactory.getLogger(DanyuanController.class);
    @GetMapping("/api/getAllDanyuans")
    public ResBody getAllDanyuans(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Danyuan> list= service.getAllDanyuans(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addDanyuan")
    public ResBody addDanyuan(@RequestBody Danyuan danyuan) {
        ResBody resBody = new ResBody();
        int i = service.addDanyuan(danyuan);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateDanyuan")
    public ResBody updateDanyuan(@RequestBody Danyuan danyuan) {
        ResBody resBody = new ResBody();
        System.out.println(danyuan);
        int i = service.updateDanyuan(danyuan);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delDanyuan")
    public ResBody delDanyuan(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delDanyuan(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findDanyuan")
    public ResBody findBuilding(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Danyuan> list= service.findDanyuan(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllDanyuans")
    public ResBody getAllDanyuans() {
        ResBody resBody = new ResBody();
        List<Danyuan> list= service.getAllDanyuans();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
