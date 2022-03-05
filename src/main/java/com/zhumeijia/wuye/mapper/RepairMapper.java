package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Repair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RepairMapper {
    public int getCountAll();
    public List<Repair> getAllRepair(@Param("page") int page,@Param("limit") int limit);
    public int addRepair(Repair repair);
    public int updateRepair(Repair repair);
    public int delRepair(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Repair> findRepair(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Repair> getAllRepairs();
    public int getFreeCount();
    List<Repair> getAllShowRepairs();
    public int getCountByUserId(Integer uid);
    public List<Repair> getAllRepairsByUser(@Param("page") int page,@Param("limit") int limit,@Param("uid") int uid);
}
