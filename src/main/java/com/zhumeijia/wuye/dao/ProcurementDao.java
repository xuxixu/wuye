package com.zhumeijia.wuye.dao;

import com.zhumeijia.wuye.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProcurementDao {
    @Autowired
    JdbcTemplate template;
   @Autowired
   GoodsDao goodsDao;

    public int getCount() {
        int count = template.queryForObject("select count(*) from procurement", Integer.class);
        return count;
    }

    public int getCount(int status) {
        int count = template.queryForObject("select count(*) from procurement where status = "+status, Integer.class);
        return count;
    }

    public List<Procurement> getAllProcurement(int page, int limit) {
        List<Procurement> list = template.query("select * from `procurement` limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Procurement.class));
        if (list!=null){
            for (Procurement procurement:list){
                List<Goodstype> goodstypes = template.query("select * from `goodstype` where id = ?" ,new Object[]{procurement.getGtid()},
                        new BeanPropertyRowMapper(Goodstype.class));
                List<Warehouse> warehouses = template.query("select * from `warehouse` where id = ?" ,new Object[]{procurement.getWhid()},
                        new BeanPropertyRowMapper(Warehouse.class));
                procurement.setGoodstype(goodstypes.get(0));
                procurement.setWarehouse(warehouses.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<Procurement> getAllProcurementByStatus(int page, int limit, int status) {
        List<Procurement> list = template.query("select * from `procurement` where status = "+status+" limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Procurement.class));
        if (list!=null){
            for (Procurement procurement:list){
                List<Goodstype> goodstypes = template.query("select * from `goodstype` where id = ?" ,new Object[]{procurement.getGtid()},
                        new BeanPropertyRowMapper(Goodstype.class));
                List<Warehouse> warehouses = template.query("select * from `warehouse` where id = ?" ,new Object[]{procurement.getWhid()},
                        new BeanPropertyRowMapper(Warehouse.class));
                procurement.setGoodstype(goodstypes.get(0));
                procurement.setWarehouse(warehouses.get(0));
            }
            return list;
        }else{
            return null;
        }
    }
    public List<Procurement> getProcurementById(int id) {
        return template.query("select * from `procurement` where id = ?" ,new Object[]{id},
                new BeanPropertyRowMapper(Procurement.class));
    }
    public int queren(int id) {
        List<Procurement> p = getProcurementById(id);
        Goods goods = new Goods();
        goods.setNumber(p.get(0).getNumber());
        goods.setPrices(p.get(0).getTotalmoney() / p.get(0).getNumber());
        goods.setWhid(p.get(0).getWhid());
        goods.setName(p.get(0).getGname());
        goods.setGtid(p.get(0).getGtid());
        System.out.println(goods);
        goodsDao.addGoods(goods);

        return template.update("update procurement set status = 1 where id = ?", id);
    }
}
