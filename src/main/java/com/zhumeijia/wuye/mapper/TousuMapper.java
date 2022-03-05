package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Tousu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TousuMapper {
    public int getCountAll();
    public List<Tousu> getAllTousu(@Param("page") int page,@Param("limit") int limit);
    public int addTousu(Tousu tousu);
    public int updateTousu(Tousu tousu);
    public int delTousu(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Tousu> findTousu(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Tousu> getAllTousus();
    public int getFreeCount();
    List<Tousu> getAllShowTousus();
    public int getCountByUserId(Integer uid);
    public List<Tousu> getAllToususByUser(@Param("page") int page,@Param("limit") int limit,@Param("uid") int uid);

    int getCountById(Integer id);
}
