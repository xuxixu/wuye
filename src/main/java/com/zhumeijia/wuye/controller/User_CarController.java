package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.bean.User_Car;
import com.zhumeijia.wuye.bean.User_Room;
import com.zhumeijia.wuye.service.User_CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class User_CarController {
    @Autowired
    User_CarService service;

    @GetMapping("/api/findCarRecordById")
    public ResBody findCarRecordById(@RequestParam int id,@RequestParam int page,@RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCarRecordCount(id);
        List<User_Car> list= service.findCarRecordById(id,page,limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/stopCarByUserId")
    public ResBody stopCarByUserId(@RequestParam int id){
        ResBody resBody = new ResBody();
        int i = service.stopCarByUserId(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("失败");
        }
        return resBody;
    }

    @PostMapping("/api/fenpeiCar")
    public ResBody fenpeiCar(@RequestBody User_Car user_car) {
        ResBody resBody = new ResBody();
        System.out.println(user_car);
        user_car.setUid(user_car.getId());
        //判断该用户当前有无房间，如果有，先退房再入住。
        int count = service.findCar(user_car.getUid());
        if (count == 1){
            service.outCar(user_car.getUid());
        }
        int i = service.fenpei(user_car.getUid(),user_car.getCid());
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }
}
