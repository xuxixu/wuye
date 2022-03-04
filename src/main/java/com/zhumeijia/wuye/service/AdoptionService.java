package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Adoption;
import com.zhumeijia.wuye.dao.AdoptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService {
    @Autowired
    AdoptionDao dao;


    public int getCount() {
        return dao.getCount();
    }

    public List<Adoption> getAllAdoption(int page, int limit) {
        return dao.getAllAdoption(page,limit);
    }

    public int getCount(int status) {
        return dao.getCount(status);
    }

    public List<Adoption> getAllAdoption(int page, int limit, int status) {
        return dao.getAllAdoptionByStatus(page,limit,status);
    }

    public int shiyong(int id) {return dao.queren(id);
    }

}
