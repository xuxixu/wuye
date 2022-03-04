package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarehouseMapper {
    public int getCountAll();
    public List<Warehouse> getAllWarehouse(@Param("page") int page,@Param("limit") int limit);
    public int addWarehouse(Warehouse warehouse);
    public int updateWarehouse(Warehouse warehouse);
    public int delWarehouse(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Warehouse> findWarehouse(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Warehouse> getAllWarehouses();
}
