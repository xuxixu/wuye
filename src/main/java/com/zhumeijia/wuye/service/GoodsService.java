package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Goods;
import com.zhumeijia.wuye.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Goods> getAllGoods(int page, int limit) {
        return dao.getAllGoods(page,limit);
    }

    public int shenqing(Goods goods) {
        return dao.shenqing(goods);
    }

    public int caiyong(Goods goods) {
        return dao.caiyong(goods);
    }


    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Goods> findGoods(int page, int limit, String name) {
        return dao.findGoods(page,limit,name);
    }

    public List<Goods> getAllGoodss() {
        return dao.getAllGoodss();
    }
}
