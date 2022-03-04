package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Warehouse;
import com.zhumeijia.wuye.dao.WarehouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    WarehouseDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Warehouse> getAllWarehouse(int page, int limit) {
        return dao.getAllWarehouse(page,limit);
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
        return dao.getCount(name);
    }

    public List<Warehouse> findWarehouse(int page, int limit, String name) {
        return dao.findWarehouse(page,limit,name);
    }

    public List<Warehouse> getAllWarehouses() {
        return dao.getAllWarehouses();
    }
}
