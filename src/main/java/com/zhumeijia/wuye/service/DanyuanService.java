package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.bean.Danyuan;
import com.zhumeijia.wuye.mapper.BuildingMapper;
import com.zhumeijia.wuye.mapper.DanyuanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanyuanService {
    @Autowired
    DanyuanMapper dao;
    @Autowired
    BuildingMapper bdao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Danyuan> getAllDanyuans(int page, int limit) {
        List<Danyuan> list = dao.getAllDanyuan((page - 1) * limit, limit);
        if (!list.isEmpty()){
            for (Danyuan danyuan:list){
                Building building = bdao.findBuildingByBuildingId(danyuan.getBid());
                danyuan.setBuilding(building);
            }
            return list;
        }else{
            return null;
        }
    }

    public int addDanyuan(Danyuan danyuan) {
        return dao.addDanyuan(danyuan);
    }

    public int updateDanyuan(Danyuan danyuan) {
        return dao.updateDanyuan(danyuan);
    }

    public int delDanyuan(int id) {
        return dao.delDanyuan(id);
    }

    public List<Danyuan> findDanyuan(int page, int limit, String name) {
        List<Danyuan> list = dao.findDanyuan((page - 1) * limit, limit, name);
        if (list!=null){
            for (Danyuan danyuan:list){
                Building building = bdao.findBuildingByBuildingId(danyuan.getBid());
                danyuan.setBuilding(building);
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Danyuan> getAllDanyuans() {
        return dao.getAllDanyuans();
    }
}
