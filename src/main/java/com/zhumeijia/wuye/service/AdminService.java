package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminDao dao;

    public Admin findAdmin(String username, String password) {
        return dao.findAdmin(username,password);
    }

    public int updatePass(int id, String newPsw) {
        return dao.updatePass(id,newPsw);
    }
}
