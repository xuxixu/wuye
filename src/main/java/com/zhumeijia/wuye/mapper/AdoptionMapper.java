package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Adoption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdoptionMapper {
    public int getCountAll();
    public List<Adoption> getAllAdoption(@Param("page") int page,@Param("limit") int limit);
    public int addAdoption(Adoption adoption);
    public int updateAdoption(Adoption adoption);
    public int delAdoption(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Adoption> findAdoption(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Adoption> getAllAdoptions();
    public int queren(int id);
   Adoption findAdoptionById(int id);
}
