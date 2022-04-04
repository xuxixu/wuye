package com.zhumeijia.wuye.controller;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.service.AdminService;
import com.zhumeijia.wuye.service.RoleService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class AdminController {
    @Autowired
    AdminService service;
    @Autowired
    RoleService rservice;
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/admin/loginByPassword")
    public ResBody loginByPassword(@RequestBody Map<String, Object> params,
                                   HttpSession session) {
        ResBody resBody = new ResBody();
        String username = params.get("username").toString();
        String password = params.get("password").toString();
        Admin admin = service.findAdmin(username,password);
        admin.setRole(rservice.findRoleById(admin.getRid()));
        if (admin == null){
            resBody.setCode(500);
            resBody.setMsg("登录失败，请重新登录");
        }else {
            session.setAttribute("admin",admin);
            LOG.info(admin.toString());
            resBody.setCode(200);
            resBody.setMsg("登录成功");
        }
        return resBody;
    }

    @PostMapping("/admin/updatePass")
    public ResBody updatePass(@RequestBody Map<String, Object> params,
                              HttpSession session) {
        ResBody resBody = new ResBody();
        String newPsw = params.get("newPsw").toString();
        Admin admin = (Admin) session.getAttribute("admin");
        admin.setPassword(newPsw);
        int i = service.updatePass(admin.getId(),newPsw);
        if (i != 1){
            resBody.setCode(500);
            resBody.setMsg("修改失败，后台出错");
        }else {
            session.setAttribute("admin",admin);
            LOG.info(admin.toString());
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }
        return resBody;
    }

    @GetMapping("/api/getAllAdmins")
    public ResBody getAllAdmins(@RequestParam int page,
                                   @RequestParam int limit,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int count = service.getCount();
            List<Admin> list= service.getAllAdmins(page, limit);
            resBody.setCount(count);
            resBody.setData(list);
            resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看!非管理员不能查看");
        }
        return resBody;
    }

    @PostMapping("/api/addAdmin")
    public ResBody addAdmin(@RequestBody Admin building,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");

        if( rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int i = service.addAdmin(building);
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
            resBody.setMsg("不能修改! 非管理员不能增加");
        }
        return resBody;
    }

    @PostMapping("/api/updateAdmin")
    public ResBody updateAdmin(@RequestBody Admin building,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int i = service.updateAdmin(building);
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
            resBody.setMsg("不能修改!非管理员不能修改");
        }

        return resBody;
    }

    @GetMapping("/api/delAdmin")
    public ResBody delAdmin(@RequestParam int id,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if(id != admin.getId() && rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int i = service.delAdmin(id);
            if (i == 1){
                resBody.setCode(200);
                resBody.setMsg("查看成功");
            }else{
                resBody.setCode(500);
                resBody.setMsg("删除失败");
            }
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能删除! 不能删除自己或非管理员不能删除");
        }

        return resBody;
    }

    @GetMapping("/api/findAdmin")
    public ResBody findAdmin(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name,HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
            int count = service.getCount(name);
            List<Admin> list= service.findAdmin(page, limit,name);
            resBody.setCount(count);
            resBody.setData(list);
            resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看! 非管理员不能查看");
        }

        return resBody;
    }

    @GetMapping("/ajax/getAllAdmins")
    public ResBody getAllAdmins(HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        if( rservice.findRoleById(admin.getRid()).getName().equals("管理员"))
        {
        List<Admin> list= service.getAllAdmins();
        resBody.setData(list);
        resBody.setCode(0);
        }else
        {
            resBody.setCode(500);
            resBody.setMsg("不能查看! 非管理员不能查看");
        }
        return resBody;
    }

    @RequestMapping(value="/api/updateAdminImage",method=RequestMethod.POST)
    @ResponseBody
    public ResBody updateAdminImage(@RequestParam String base64Data, HttpServletRequest request, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");

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

                FileUtils.writeByteArrayToFile(new File("D:\\Project\\zhumeijia\\wuye\\src\\main\\resources\\static\\assets\\images", tempFileName), bs);
                admin.setImage("assets/images/"+tempFileName);
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
