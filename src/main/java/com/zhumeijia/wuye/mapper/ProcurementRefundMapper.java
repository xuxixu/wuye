package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.ProcurementRefund;

/**
* @author xuxin
* @description 针对表【procurement_refund】的数据库操作Mapper
* @createDate 2022-03-09 17:06:12
* @Entity com.zhumeijia.wuye.bean.ProcurementRefund
*/
public interface ProcurementRefundMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProcurementRefund record);

    int insertSelective(ProcurementRefund record);

    ProcurementRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProcurementRefund record);

    int updateByPrimaryKey(ProcurementRefund record);

}
