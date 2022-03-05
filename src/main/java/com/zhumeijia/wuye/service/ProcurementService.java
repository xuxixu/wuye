package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Goods;
import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.bean.Procurement;
import com.zhumeijia.wuye.bean.Warehouse;

import com.zhumeijia.wuye.mapper.GoodsMapper;
import com.zhumeijia.wuye.mapper.GoodstypeMapper;
import com.zhumeijia.wuye.mapper.ProcurementMapper;
import com.zhumeijia.wuye.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcurementService {
    @Autowired
    ProcurementMapper dao;
    @Autowired
    WarehouseMapper wdao;
    @Autowired
    GoodstypeMapper gtdao;
    @Autowired
    GoodsMapper gdao;

    public int getCount() {
        return dao.getCountAll();
    }

    public List<Procurement> getAllProcurement(int page, int limit) {
        List<Procurement> list = dao.getAllProcurement((page - 1) * limit, limit);
        if (list!=null){
            for (Procurement procurement:list){
                List<Goodstype> goodstypes = gtdao.findGoodstypeById(procurement.getGtid());
                List<Warehouse> warehouses = wdao.findWarehouseById(procurement.getWhid());
                procurement.setGoodstype(goodstypes.get(0));
                procurement.setWarehouse(warehouses.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(int status) {
        return dao.getCountByName(String.valueOf(status));
    }

    public List<Procurement> getAllProcurement(int page, int limit, int status) {
        List<Procurement> list = dao.findProcurement((page - 1) * limit, limit, String.valueOf(status));
        if (list!=null){
            for (Procurement procurement:list){
                List<Goodstype> goodstypes = gtdao.findGoodstypeById(procurement.getGtid());
                List<Warehouse> warehouses = wdao.findWarehouseById(procurement.getWhid());
                procurement.setGoodstype(goodstypes.get(0));
                procurement.setWarehouse(warehouses.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int queren(int id) {
        Procurement p = dao.findProcurementById(id);
        Goods goods = new Goods();
        goods.setNumber(p.getNumber());
        goods.setPrices(p.getTotalmoney() / p.getNumber());
        goods.setWhid(p.getWhid());
        goods.setName(p.getGname());
        goods.setGtid(p.getGtid());
        System.out.println(goods);
        gdao.addGoods(goods);
        return dao.queren(id);
    }

}
