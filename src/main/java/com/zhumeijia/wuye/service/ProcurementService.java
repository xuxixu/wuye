package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Procurement;
import com.zhumeijia.wuye.dao.ProcurementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcurementService {
    @Autowired
    ProcurementDao dao;


    public int getCount() {
        return dao.getCount();
    }

    public List<Procurement> getAllProcurement(int page, int limit) {
        return dao.getAllProcurement(page,limit);
    }

    public int getCount(int status) {
        return dao.getCount(status);
    }

    public List<Procurement> getAllProcurement(int page, int limit, int status) {
        return dao.getAllProcurementByStatus(page,limit,status);
    }

    public int queren(int id) {return dao.queren(id);
    }

}
