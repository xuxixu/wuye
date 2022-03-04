package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Warehouse;
import com.zhumeijia.wuye.dao.WarehouseDao;
import com.zhumeijia.wuye.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    WarehouseMapper dao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Warehouse> getAllWarehouse(int page, int limit) {
        return dao.getAllWarehouse((page-1)*limit,limit);
    }

    public int addWarehouse(Warehouse warehouse) {
        return dao.addWarehouse(warehouse);
    }

    public int updateWarehouse(Warehouse warehouse) {
        return dao.updateWarehouse(warehouse);
    }

    public int delWarehouse(int id) {
        return dao.delWarehouse(id);
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Warehouse> findWarehouse(int page, int limit, String name) {
        return dao.findWarehouse((page-1)*limit,limit,name);
    }

    public List<Warehouse> getAllWarehouses() {
        return dao.getAllWarehouses();
    }
}
