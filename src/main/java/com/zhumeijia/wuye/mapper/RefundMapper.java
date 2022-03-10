package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Refund;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author xuxin
* @description 针对表【refund】的数据库操作Mapper
* @createDate 2022-03-09 17:07:36
* @Entity com.zhumeijia.wuye.bean.Refund
*/
@Mapper
public interface RefundMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Refund record);

    int insertSelective(Refund record);

    Refund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);

    Refund findGoodstypeById(Integer rid);

    Refund findRefundByOut_Trade_NO(String outTradeNo);
}
