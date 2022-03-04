package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Danyuan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DanyuanMapper {
    public int getCountAll();
    public List<Danyuan> getAllDanyuan(@Param("page") int page,@Param("limit") int limit);
    public int addDanyuan(Danyuan danyuan);
    public int updateDanyuan(Danyuan danyuan);
    public int delDanyuan(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Danyuan> findDanyuan(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Danyuan> getAllDanyuans();

    List<Danyuan> findDanyuanById(int id);
}
