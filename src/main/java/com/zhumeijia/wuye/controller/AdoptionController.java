package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Adoption;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.AdoptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdoptionController {
    @Autowired
    AdoptionService service;
    private static final Logger LOG = LoggerFactory.getLogger(AdoptionController.class);

    @GetMapping("/api/getAllAdoption")
    public ResBody getAllAdoption(@RequestParam int page,
                                        @RequestParam int limit, HttpSession session) {

        int count = service.getCount();
        List<Adoption> list= service.getAllAdoption(page, limit);
        ResBody resBody = new ResBody();
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/findAdoption")
    public ResBody findAdoption(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        if(name.isEmpty())
        {
            int count = service.getCount();
            List<Adoption> list= service.getAllAdoption(page, limit);
            ResBody resBody = new ResBody();
            resBody.setCount(count);
            resBody.setData(list);
            resBody.setCode(0);
            return resBody;
        }
        int count = 0;
        List<Adoption> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        count = service.getCount(Integer.valueOf(name));
        list= service.getAllAdoption(page, limit,Integer.valueOf(name));
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }


    @GetMapping("/api/shiyong")
    public ResBody shiyong(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.shiyong(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("成功");
        }else {
            resBody.setCode(500);
            resBody.setMsg("失败");
        }
        return resBody;
    }
}
