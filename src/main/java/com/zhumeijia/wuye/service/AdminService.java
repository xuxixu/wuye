package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Role;
import com.zhumeijia.wuye.mapper.AdminMapper;
import com.zhumeijia.wuye.mapper.AdminMapper;
import com.zhumeijia.wuye.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper rdao;
    
    public Admin findAdmin(String username, String password) {
        return adminMapper.findAdmin(username,password);
    }

    public int updatePass(int id, String newPsw) {
        return adminMapper.updatePass(id,newPsw);
    }

    public int getCount() {
        return adminMapper.getCountAll();
    }

    public List<Admin> getAllAdmins(int page, int limit) {
        List<Admin> list = adminMapper.getAllAdmin((page - 1) * limit, limit);
        if(!list.isEmpty())
        {
            for(Admin admin : list) {
                Role role = rdao.findRoleById(admin.getRid());
                admin.setRole(role);
            }
            return list;
        }
        return null;
    }

    public int addAdmin(Admin admin) {
        admin.setId(adminMapper.getCountAll()+1);
        return adminMapper.addAdmin(admin);
    }

    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    public int delAdmin(int id) {
        return adminMapper.delAdmin(id);
    }

    public int getCount(String name) {
        return adminMapper.getCountByName(name);
    }

    public List<Admin> findAdmin(int page, int limit, String name) {
        List<Admin> list = adminMapper.findAdminByName((page - 1) * limit, limit, name);
        if(!list.isEmpty())
        {
            for(Admin admin : list) {
                Role role = rdao.findRoleById(admin.getRid());
                admin.setRole(role);
            }
            return list;
        }
        return null;
    }

    public List<Admin> getAllAdmins() {
        return adminMapper.getAllAdmins();
    }

    public void updateAdminImage(Admin admin) {
        adminMapper.updateAdminImage(admin);
    }
}
