package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Warehouse;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WarehouseController {
    @Autowired
    WarehouseService service;
    private static final Logger LOG = LoggerFactory.getLogger(WarehouseController.class);
    @GetMapping("/api/getAllWarehouse")
    public ResBody getAllWarehouses(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Warehouse> list= service.getAllWarehouse(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);

        return resBody;
    }

    @PostMapping("/api/addWarehouse")
    public ResBody addBuilding(@RequestBody Warehouse warehouse) {
        ResBody resBody = new ResBody();
        int i = service.addWarehouse(warehouse);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateWarehouse")
    public ResBody updateWarehouse(@RequestBody Warehouse warehouse) {
        ResBody resBody = new ResBody();
        int i = service.updateWarehouse(warehouse);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delWarehouse")
    public ResBody delWarehouse(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delWarehouse(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findWarehouse")
    public ResBody findWarehouse(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Warehouse> list= service.findWarehouse(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllWarehouses")
    public ResBody getAllWarehouses() {
        ResBody resBody = new ResBody();
        List<Warehouse> list= service.getAllWarehouses();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
