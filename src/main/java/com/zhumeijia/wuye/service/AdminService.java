package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    public Admin findAdmin(String username, String password) {
        return adminMapper.findAdmin(username,password);
    }

    public int updatePass(int id, String newPsw) {
        return adminMapper.updatePass(id,newPsw);
    }
}
