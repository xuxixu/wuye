package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Goodstype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodstypeMapper {
    public int getCountAll();
    public List<Goodstype> getAllGoodstype(@Param("page") int page,@Param("limit") int limit);
    public int addGoodstype(Goodstype goodstype);
    public int updateGoodstype(Goodstype goodstype);
    public int delGoodstype(@Param("id") int id);
    public int getCountByName(@Param("name")String name);

    public List<Goodstype> findGoodstype(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);

    public List<Goodstype> getAllGoodstypes();

    List<Goodstype> findGoodstypeById(long gtid);
}
