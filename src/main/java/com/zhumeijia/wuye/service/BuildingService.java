package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.mapper.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    @Autowired
    BuildingMapper dao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Building> getAllBuildings(int page, int limit) {
        return dao.getAllBuilding((page-1)*limit,limit);
    }

    public int addBuilding(Building building) {
        return dao.addBuilding(building);
    }

    public int updateBuilding(Building building) {
        return dao.updateBuilding(building);
    }

    public int delBuilding(int id) {
        return dao.delBuilding(id);
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Building> findBuilding(int page, int limit, String name) {
        return dao.findBuilding((page-1)*limit,limit,name);
    }

    public List<Building> getAllBuildings() {
        return dao.getAllBuildings();
    }
}
