package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Gonggao;
import com.zhumeijia.wuye.bean.Repair;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.mapper.RepairMapper;
import com.zhumeijia.wuye.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    @Autowired
    RepairMapper dao;
    @Autowired
    UserMapper udao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Repair> getAllRepairs(int page, int limit) {
        List<Repair> list = dao.getAllRepair((page - 1) * limit, limit);
        if (list!=null){
            for (Repair repair:list){
                List<User> users = udao.findUserById(list.get(0).getUid());
                repair.setUser(users.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int addRepair(Repair repair) {
        return dao.addRepair(repair);
    }

    public int updateRepair(Repair repair) {
        return dao.updateRepair(repair);
    }

    public int delRepair(int id) {
        return dao.delRepair(id);
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Repair> findRepair(int page, int limit, String name) {
        List<Repair> list = dao.findRepair((page - 1) * limit, limit, name);
        if (list!=null){
            for (Repair repair:list){
                List<User> users = udao.findUserById(list.get(0).getUid());
                repair.setUser(users.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCountByUserId(Integer id) {
        return dao.getCountByUserId(id);
    }

    public List<Repair> getAllRepairsByUser(int page, int limit, Integer id) {
        List<Repair> list = dao.getAllRepairsByUser((page - 1) * limit, limit, id);
        return list;
    }
}
