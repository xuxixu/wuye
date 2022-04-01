package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Role;
import com.zhumeijia.wuye.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper dao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Role> getAllRoles(int page, int limit) {
        return dao.getAllRole((page-1)*limit,limit);
    }

    public int addRole(Role payment) {

        return dao.addRole(payment);
    }

    public int updateRole(Role payment) {
        return dao.updateRole(payment);
    }

    public int delRole(int id) {
        return dao.delRole(id);
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Role> findRole(int page, int limit, String name) {
        return dao.findRole((page-1)*limit,limit,name);
    }

    public List<Role> getAllRoles() {
        return dao.getAllRoles();
    }


    public Role findRoleById(Integer rid) {
        return dao.findRoleById(rid);
    }
}
