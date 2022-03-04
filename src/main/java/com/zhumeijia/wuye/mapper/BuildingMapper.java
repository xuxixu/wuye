package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BuildingMapper {
    public int getCountAll();
    public List<Building> getAllBuilding(@Param("page") int page,@Param("limit") int limit);
    public int addBuilding(Building building);
    public int updateBuilding(Building building);
    public int delBuilding(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Building> findBuilding(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Building> getAllBuildings();

    Building findBuildingByBuildingId(Integer building_id);
}
