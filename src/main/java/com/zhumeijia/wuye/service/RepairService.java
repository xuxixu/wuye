package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Gonggao;
import com.zhumeijia.wuye.bean.Repair;
import com.zhumeijia.wuye.dao.RepairDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    @Autowired
    RepairDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Repair> getAllRepairs(int page, int limit) {
        return dao.getAllRepairs(page,limit);
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
        return dao.getCount(name);
    }

    public List<Repair> findRepair(int page, int limit, String name) {
        return dao.findRepair(page,limit,name);
    }

    public int getCountByUserId(Integer id) {
        return dao.getCountByUserId(id);
    }

    public List<Repair> getAllRepairsByUser(int page, int limit, Integer id) {
        return dao.getAllRepairsByUser(page,limit,id);
    }
}
