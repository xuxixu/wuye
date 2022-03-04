package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Gonggao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GonggaoMapper {
    public int getCountAll();
    public List<Gonggao> getAllGonggao(@Param("page") int page,@Param("limit") int limit);
    public int addGonggao(Gonggao gonggao);
    public int updateGonggao(Gonggao gonggao);
    public int delGonggao(@Param("id") int id);
    public int getCountByName(@Param("title")String title);
    public List<Gonggao> findGonggao(@Param("page") int page,@Param("limit") int limit,@Param("title") String title);
    public List<Gonggao> getAllGonggaos();
    public int getFreeCount();
    List<Gonggao> getAllShowGonggaos();
}
