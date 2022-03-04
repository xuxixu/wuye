package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.Goods;
import com.zhumeijia.wuye.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    GoodsService service;

    @GetMapping("/api/getAllGoods")
    public ResBody getAllGoodss(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Goods> list= service.getAllGoods(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);

        return resBody;
    }

    @PostMapping("/api/shenqing")
    public ResBody shenqing(@RequestBody Goods goods) {
        ResBody resBody = new ResBody();
        int i = service.shenqing(goods);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("申请成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("申请失败");
        }
        return resBody;
    }

    @PostMapping("/api/caiyong")
    public ResBody caiyong(@RequestBody Goods goods) {

        ResBody resBody = new ResBody();
        int i = service.caiyong(goods);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("申请成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("申请失败");
        }
        return resBody;
    }


    @GetMapping("/api/findGoods")
    public ResBody findGoods(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        System.out.println(name);
        int count = service.getCount(name);
        List<Goods> list= service.findGoods(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllGoodss")
    public ResBody getAllGoodss() {
        ResBody resBody = new ResBody();
        List<Goods> list= service.getAllGoodss();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
