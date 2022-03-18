package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    public int getCountAll();
    public List<Goods> getAllGoods(@Param("page") int page,@Param("limit") int limit);
    public int addGoods(Goods goods);
    public int updateGoods(Goods goods);
    public int delGoods(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Goods> findGoods(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Goods> getAllGoodss();
    public void updateNumberByName(long number, String name);
    List<Goods> findGoodsById(int id);

    List<Goods> getGoodsByName(String gname);

    int getCountByWhid(int whid);

}
