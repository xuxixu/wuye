package com.zhumeijia.wuye.dao;

import com.zhumeijia.wuye.bean.Goods;
import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.bean.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Repository
public class GoodsDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from goods", Integer.class);
        return count;
    }

    public List<Goods> getAllGoods(int page, int limit) {
        List<Goods> list = template.query("select * from goods limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Goods.class));
        if (list!=null){
            for(Goods goods : list)
            {
                List<Goodstype> lgt = template.query("select * from goodstype where id = "+goods.getGtid() ,
                        new BeanPropertyRowMapper(Goodstype.class));
                if(!lgt.isEmpty()) goods.setGoodstype(lgt.get(0));
                List<Warehouse> lwh = template.query("select * from warehouse where id = "+goods.getWhid() ,
                        new BeanPropertyRowMapper(Warehouse.class));
                if(!lwh.isEmpty()) goods.setWarehouse(lwh.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public void addGoods(Goods goods) {
        List<Goods> good = getGoodsByName(goods.getName());
        if(!good.isEmpty())
        {
            goods.setNumber(goods.getNumber()+good.get(0).getNumber());
            updateNumberByName(goods.getNumber(), goods.getName());
        }else {
            template.update("insert into goods values(null,?,?,?,?,?)",
                    goods.getName(), goods.getNumber(), goods.getGtid(), goods.getWhid(), goods.getPrices());
        }

    }

    List<Goods> getGoodsByName(String name) {
        return template.query("select * from goods where name = ?" ,new Object[]{name},
                new BeanPropertyRowMapper(Goods.class));

    }


    public int shenqing(Goods goods) {
        System.out.println(goods);
        return template.update("insert into procurement values(null,?,?,?,?,?,?,?)",
                goods.getNumber(),goods.getName(), goods.getGtid(), goods.getWhid(),new Date(),0,goods.getNumber()*goods.getPrices());
    }
    public int caiyong(Goods goods) {
        List<Goods> list = template.query("select * from goods where id = ?" ,new Object[]{goods.getId()},
                new BeanPropertyRowMapper(Goods.class));
        if(list.isEmpty() || list.get(0).getNumber()<goods.getNumber())
        {
            return -1;
        }else {
            System.out.println(goods);
            return template.update("insert into adoption values(null,?,?,?,0)",
                    list.get(0).getName(),goods.getNumber(), new Date());

        }
    }


    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from goods where name like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Goods> findGoods(int page, int limit, String name) {
        List<Goods> list = template.query("select * from goods where name like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Goods.class));
        if (list!=null){
            for(Goods goods : list)
            {
                List<Goodstype> lgt = template.query("select * from goodstype where id = "+goods.getGtid() ,
                        new BeanPropertyRowMapper(Goodstype.class));
                if(!lgt.isEmpty()) goods.setGoodstype(lgt.get(0));
                List<Warehouse> lwh = template.query("select * from warehouse where id = "+goods.getWhid() ,
                        new BeanPropertyRowMapper(Warehouse.class));
                if(!lwh.isEmpty()) goods.setWarehouse(lwh.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<Goods> getAllGoodss() {
        List<Goods> list = template.query("select * from goods",
                new BeanPropertyRowMapper(Goods.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public void updateNumberByName(long number, String name) {
         template.update("update `goods` set number = ? where name = ?", number,name);
    }

    public void delGoods(long id) {
        template.update("DELETE from goods where id=?",id);
    }
}
