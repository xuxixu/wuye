package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Procurement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProcurementMapper {
    public int getCountAll();
    public List<Procurement> getAllProcurement(@Param("page") int page,@Param("limit") int limit);
    public int addProcurement(Procurement procurement);
    public int updateProcurement(Procurement procurement);
    public int delProcurement(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Procurement> findProcurement(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Procurement> getAllProcurements();
    public int queren(int id);
   Procurement findProcurementById(int id);

    void updateStatusById(Integer pid);
}
