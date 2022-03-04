package com.zhumeijia.wuye.dao;

import com.zhumeijia.wuye.bean.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WarehouseDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from warehouse", Integer.class);
        return count;
    }

    public List<Warehouse> getAllWarehouse(int page, int limit) {
        List<Warehouse> list = template.query("select * from warehouse limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Warehouse.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addWarehouse(Warehouse warehouse) {
        return template.update("insert into warehouse values(null,?,?)",
                warehouse.getName(),warehouse.getLocal());
    }

    public int updateWarehouse(Warehouse warehouse) {
        return template.update("update warehouse set name = ?, local = ? where id = ?",
                warehouse.getName(),warehouse.getLocal(), warehouse.getId());
    }

    public int delWarehouse(int id) {
        return template.update("DELETE from warehouse where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from warehouse where name like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Warehouse> findWarehouse(int page, int limit, String name) {
        List<Warehouse> list = template.query("select * from warehouse where name like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Warehouse.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> list = template.query("select * from warehouse",
                new BeanPropertyRowMapper(Warehouse.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }
}
