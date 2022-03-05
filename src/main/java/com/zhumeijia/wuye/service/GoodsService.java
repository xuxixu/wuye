package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.*;
import com.zhumeijia.wuye.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper dao;
    @Autowired
    GoodstypeMapper gtdao;
    @Autowired
    WarehouseMapper wdao;
    @Autowired
    AdoptionMapper adao;
    @Autowired
    ProcurementMapper pdao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Goods> getAllGoods(int page, int limit) {
        List<Goods> list = dao.getAllGoods((page - 1) * limit, limit);
        if (list!=null){
            for(Goods goods : list)
            {
                List<Goodstype> lgt = gtdao.findGoodstypeById(goods.getGtid());
                if(!lgt.isEmpty()) goods.setGoodstype(lgt.get(0));
                List<Warehouse> lwh = wdao.findWarehouseById(goods.getWhid());
                if(!lwh.isEmpty()) goods.setWarehouse(lwh.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int shenqing(Goods goods) {
        System.out.println(goods);
        Procurement procurement = new Procurement();
        procurement.setNumber(goods.getNumber());
        procurement.setGname(goods.getName());
        procurement.setGtid(goods.getGtid());
        procurement.setWhid(goods.getWhid());
        procurement.setTime(new Date());
        procurement.setTotalmoney(goods.getNumber()*goods.getPrices());
        return pdao.addProcurement(procurement);
    }

    public int caiyong(Goods goods) {
        List<Goods> list = dao.findGoodsById((int) goods.getId());
        if(list.isEmpty() || list.get(0).getNumber()<goods.getNumber())
        {
            return -1;
        }else {
            System.out.println(goods);
            Adoption adoption = new Adoption();
            adoption.setNumber(goods.getNumber());
            adoption.setGname(list.get(0).getName());
            adoption.setTime(new Date());
            return adao.addAdoption(adoption);

        }
    }


    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Goods> findGoods(int page, int limit, String name) {
        List<Goods> list = dao.findGoods((page - 1) * limit, limit, name);
        if (list!=null){
            for(Goods goods : list)
            {
                List<Goodstype> lgt = gtdao.findGoodstypeById(goods.getGtid());
                if(!lgt.isEmpty()) goods.setGoodstype(lgt.get(0));
                List<Warehouse> lwh = wdao.findWarehouseById(goods.getWhid());
                if(!lwh.isEmpty()) goods.setWarehouse(lwh.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<Goods> getAllGoodss() {
        return dao.getAllGoodss();
    }
}
