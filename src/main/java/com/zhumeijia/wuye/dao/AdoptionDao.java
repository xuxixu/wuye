package com.zhumeijia.wuye.dao;

import com.zhumeijia.wuye.bean.Goods;
import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.bean.Adoption;
import com.zhumeijia.wuye.bean.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdoptionDao {
    @Autowired
    JdbcTemplate template;
   @Autowired
   GoodsDao goodsDao;

    public int getCount() {
        int count = template.queryForObject("select count(*) from adoption", Integer.class);
        return count;
    }

    public int getCount(int status) {
        int count = template.queryForObject("select count(*) from adoption where status = "+status, Integer.class);
        return count;
    }

    public List<Adoption> getAllAdoption(int page, int limit) {
       return  template.query("select * from `adoption` limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Adoption.class));
    }

    public List<Adoption> getAllAdoptionByStatus(int page, int limit, int status) {
        return template.query("select * from `adoption` where status = "+status+" limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Adoption.class));

    }
    public List<Adoption> getAdoptionById(int id) {
        return template.query("select * from `adoption` where id = ?" ,new Object[]{id},
                new BeanPropertyRowMapper(Adoption.class));
    }
    public int queren(int id) {
        List<Adoption> p = getAdoptionById(id);
        List<Goods> good = goodsDao.getGoodsByName(p.get(0).getGname());
        if(good.get(0).getNumber()<p.get(0).getNumber()) return -1;
        if (good.get(0).getNumber()-p.get(0).getNumber() == 0) goodsDao.delGoods(good.get(0).getId());
        else {
            good.get(0).setNumber(good.get(0).getNumber() - p.get(0).getNumber());
            goodsDao.updateNumberByName(good.get(0).getNumber(), good.get(0).getName());
        }

        return template.update("update adoption set status = 1 where id = ?", id);
    }
}
