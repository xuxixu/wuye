package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService service;
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/api/getUsers")
    public ResBody getUsers(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<User> list= service.getUsers(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addUser")
    public ResBody addUser(@RequestBody User user,@RequestParam("newpassword") String newpassword) {
        System.out.println(user);
        ResBody resBody = new ResBody();
/*        if(newpassword.equals(user.getPassword())) {
            int i = service.addUser(user);
            if (i == 1) {
                resBody.setCode(200);
                resBody.setMsg("添加成功");
                return resBody;
            }
        }*/
        resBody.setCode(500);
        resBody.setMsg("添加失败");
        return resBody;
    }
    @PostMapping("/api/addUser1")
    public ResBody addUser1(@RequestBody User user) {

        ResBody resBody = new ResBody();
        int i = service.addUser(user);
        if (i == 1) {
            resBody.setCode(200);
            resBody.setMsg("添加成功");
            return resBody;
        }

        resBody.setCode(500);
        resBody.setMsg("添加失败");
        return resBody;
    }

    @PostMapping("/api/updateUser")
    public ResBody updateUser(@RequestBody User user) {
        ResBody resBody = new ResBody();
        int i = service.updateUser(user);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delUser")
    public ResBody delUser(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delUser(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findUser")
    public ResBody findBuilding(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<User> list= service.findUser(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/loginByPassword")
    public ResBody loginByPassword(@RequestBody Map<String, Object> params,
                                   HttpSession session) {
        ResBody resBody = new ResBody();
        String phone = params.get("username").toString();
        String password = params.get("password").toString();
        User user = service.loginByPassword(phone,password);
        if (user == null){
            resBody.setCode(500);
            resBody.setMsg("登录失败，请重新登录");
        }else {
            session.setAttribute("user",user);
            resBody.setCode(200);
            resBody.setMsg("登录成功");
        }
        return resBody;
    }

    @PostMapping("/api/updatePass")
    public ResBody updatePass(@RequestBody Map<String, Object> params,
                              HttpSession session) {
        ResBody resBody = new ResBody();
        String newPsw = params.get("newPsw").toString();
        User user = (User) session.getAttribute("user");
        user.setPassword(newPsw);
        int i = service.updatePass(user.getId(),newPsw);
        if (i != 1){
            resBody.setCode(500);
            resBody.setMsg("修改失败，后台出错");
        }else {
            session.setAttribute("user",user);
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }
        return resBody;
    }
}
