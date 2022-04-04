package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.service.RoleService;
import com.zhumeijia.wuye.service.UserService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    RoleService rservice;
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
        int count = service.getCountByName(user.getUsername());
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

        int count = service.getCountByName(user.getUsername());
        ResBody resBody = new ResBody();
        if (count == 0)
        {
            int i = service.addUser(user);
            if (i == 1) {
                resBody.setCode(200);
                resBody.setMsg("添加成功");
                return resBody;
            }

            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }else {
            resBody.setCode(500);
            resBody.setMsg("用户名已存在");
        }


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
    @RequestMapping(value="/api/updateUserImage",method=RequestMethod.POST)
    @ResponseBody
    public ResBody updateAdminImage(@RequestParam String base64Data, HttpServletRequest request, HttpSession session) {
        ResBody resBody = new ResBody();
        User admin = (User) session.getAttribute("user");

        try{
            String dataPrix = "";
            String data = "";
            if(base64Data == null || "".equals(base64Data)){
                throw new Exception("上传失败，上传图片数据为空");
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                    throw new Exception("上传失败，数据不合法");
                }
            }
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                throw new Exception("上传图片格式不合法");
            }
            String tempFileName = UUID.randomUUID().toString() + suffix;

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            try{
                //使用apache提供的工具类操作流

                FileUtils.writeByteArrayToFile(new File("D:\\Project\\zhumeijia\\wuye\\src\\main\\resources\\static\\user\\images", tempFileName), bs);
                admin.setImage("images/"+tempFileName);
                service.updateAdminImage(admin);
            }catch(Exception ee){
                throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
            }

        }catch (Exception e) {
        }
        resBody.setCode(200);
        resBody.setMsg("修改成功");

        return resBody;
    }
}
