package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Adoption;
import com.zhumeijia.wuye.bean.Goods;
import com.zhumeijia.wuye.mapper.AdoptionMapper;
import com.zhumeijia.wuye.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService {
    @Autowired
    AdoptionMapper dao;
    @Autowired
    GoodsMapper gdao;

    public int getCount() {
        return dao.getCountAll();
    }

    public List<Adoption> getAllAdoption(int page, int limit) {
        return dao.getAllAdoption((page-1)*limit,limit);
    }

    public int getCount(int status) {
        return dao.getCountByName(String.valueOf(status));
    }

    public List<Adoption> getAllAdoption(int page, int limit, int status) {
        return dao.findAdoption((page-1)*limit,limit,String.valueOf(status));
    }

    public int shiyong(int id) {
        Adoption p = getAdoptionById(id);
        List<Goods> good = gdao.getGoodsByName(p.getGname());
        if(good.get(0).getNumber()<p.getNumber()) return -1;
        if (good.get(0).getNumber()-p.getNumber() == 0) gdao.delGoods((int) good.get(0).getId());
        else {
            good.get(0).setNumber(good.get(0).getNumber() - p.getNumber());
            gdao.updateNumberByName(good.get(0).getNumber(), good.get(0).getName());
        }
        return dao.queren(id);
    }

    private Adoption getAdoptionById(int id) {
        return dao.findAdoptionById(id);
    }

}
