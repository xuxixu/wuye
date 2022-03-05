package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Repair;
import com.zhumeijia.wuye.bean.Tousu;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.mapper.TousuMapper;
import com.zhumeijia.wuye.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TousuService {
    @Autowired
    TousuMapper dao;
    @Autowired
    UserMapper udao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Tousu> getAllTousus(int page, int limit) {
        List<Tousu> list = dao.getAllTousu((page - 1) * limit, limit);
        if (list!=null){
            for (Tousu tousu:list){
                List<User> users = udao.findUserById(tousu.getUid());
                tousu.setUser(users.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int addTousu(Tousu tousu) {
        return dao.addTousu(tousu);
    }

    public int updateTousu(Tousu tousu) {
        return dao.updateTousu(tousu);
    }

    public int delTousu(int id) {
        return dao.delTousu(id);
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Tousu> findTousu(int page, int limit, String name) {
        List<Tousu> list = dao.findTousu((page - 1) * limit, limit, name);
        if (list!=null){
            for (Tousu tousu:list){
                List<User> users = udao.findUserById(tousu.getUid());
                tousu.setUser(users.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCountByUserId(Integer id) {
        return dao.getCountByUserId(id);
    }

    public int getCount(Integer id) {
        return dao.getCountById(id);
    }

    public List<Tousu> getAllToususByUser(int page, int limit, Integer id) {
        return dao.getAllToususByUser((page-1)*limit,limit,id);
    }
}