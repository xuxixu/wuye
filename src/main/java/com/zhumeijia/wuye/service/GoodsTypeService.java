package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.dao.GoodstypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeService {
    @Autowired
    GoodstypeDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Goodstype> getAllGoodstype(int page, int limit) {
        return dao.getAllGoodstype(page,limit);
    }

    public int addGoodstype(Goodstype goodstype) {
        return dao.addGoodstype(goodstype);
    }

    public int updateGoodstype(Goodstype goodstype) {
        return dao.updateGoodstype(goodstype);
    }

    public int delGoodstype(int id) {
        return dao.delGoodstype(id);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Goodstype> findGoodstype(int page, int limit, String name) {
        return dao.findGoodstype(page,limit,name);
    }

    public List<Goodstype> getAllGoodstypes() {
        return dao.getAllGoodstypes();
    }
}
