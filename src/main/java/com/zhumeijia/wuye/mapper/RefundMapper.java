package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Refund;

/**
* @author xuxin
* @description 针对表【refund】的数据库操作Mapper
* @createDate 2022-03-09 17:07:36
* @Entity com.zhumeijia.wuye.bean.Refund
*/
public interface RefundMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Refund record);

    int insertSelective(Refund record);

    Refund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);

}
