package com.zhumeijia.wuye.dao;

import com.zhumeijia.wuye.bean.Goodstype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodstypeDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from goodstype", Integer.class);
        return count;
    }

    public List<Goodstype> getAllGoodstype(int page, int limit) {
        List<Goodstype> list = template.query("select * from goodstype limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Goodstype.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addGoodstype(Goodstype goodstype) {
        return template.update("insert into goodstype values(null,?,?)",
                goodstype.getName(),goodstype.getRemark());
    }

    public int updateGoodstype(Goodstype goodstype) {
        return template.update("update goodstype set name = ?, remark = ? where id = ?",
                goodstype.getName(),goodstype.getRemark(), goodstype.getId());
    }

    public int delGoodstype(int id) {
        return template.update("DELETE from goodstype where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from goodstype where name like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Goodstype> findGoodstype(int page, int limit, String name) {
        List<Goodstype> list = template.query("select * from goodstype where name like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Goodstype.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<Goodstype> getAllGoodstypes() {
        List<Goodstype> list = template.query("select * from goodstype",
                new BeanPropertyRowMapper(Goodstype.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }
}
