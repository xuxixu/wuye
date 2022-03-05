package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.Gonggao;
import com.zhumeijia.wuye.mapper.AdminMapper;
import com.zhumeijia.wuye.mapper.GonggaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GonggaoService {
    @Autowired
    GonggaoMapper dao;
    @Autowired
    AdminMapper adao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Gonggao> getAllGonggaos(int page, int limit) {
        List<Gonggao> list = dao.getAllGonggao((page - 1) * limit, limit);
        if (list!=null){
            for (Gonggao gonggao:list){
                List<Admin> admin = adao.findAdminById(gonggao.getCreateBy());
                gonggao.setCreate_admin(admin.get(0));
                if (gonggao.getUpdateBy()!=null){
                    List<Admin> admins = adao.findAdminById(gonggao.getUpdateBy());
                    gonggao.setUpdate_admin(admins.get(0));
                }
            }
            return list;
        }else{
            return null;
        }
    }

    public int addGonggao(Gonggao gonggao) {
        return dao.addGonggao(gonggao);
    }

    public int updateGonggao(Gonggao gonggao) {
        return dao.updateGonggao(gonggao);
    }

    public int delGonggao(int id) {
        return dao.delGonggao(id);
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Gonggao> findGonggao(int page, int limit, String name) {
        List<Gonggao> list = dao.findGonggao((page-1)*limit, limit, name);
        if (list!=null){
            for (Gonggao gonggao:list){
                List<Admin> admin = adao.findAdminById(gonggao.getCreateBy());
                gonggao.setCreate_admin(admin.get(0));
                if (gonggao.getUpdateBy()!=null){
                    List<Admin> admins = adao.findAdminById(gonggao.getUpdateBy());
                    gonggao.setUpdate_admin(admins.get(0));
                }
            }
            return list;
        }else{
            return null;
        }
    }

    public Gonggao getGonggao() {
        return dao.getAllGonggaos().get(0);
    }

    public List<Gonggao> getAllShowGonggaos() {
        return dao.getAllShowGonggaos();
    }
}
