package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsTypeController {
    @Autowired
    GoodsTypeService service;

    @GetMapping("/api/getAllGoodstype")
    public ResBody getAllGoodstypes(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Goodstype> list= service.getAllGoodstype(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);

        return resBody;
    }

    @PostMapping("/api/addGoodstype")
    public ResBody addBuilding(@RequestBody Goodstype goodstype) {
        ResBody resBody = new ResBody();
        int i = service.addGoodstype(goodstype);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateGoodstype")
    public ResBody updateGoodstype(@RequestBody Goodstype goodstype) {
        ResBody resBody = new ResBody();
        int i = service.updateGoodstype(goodstype);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delGoodstype")
    public ResBody delGoodstype(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delGoodstype(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findGoodstype")
    public ResBody findGoodstype(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Goodstype> list= service.findGoodstype(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllGoodstypes")
    public ResBody getAllGoodstypes() {
        ResBody resBody = new ResBody();
        List<Goodstype> list= service.getAllGoodstypes();

        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
