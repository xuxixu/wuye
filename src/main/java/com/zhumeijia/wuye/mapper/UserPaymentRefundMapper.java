package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.UserPaymentRefund;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xuxin
* @description 针对表【user_payment_refund】的数据库操作Mapper
* @createDate 2022-03-09 17:07:42
* @Entity com.zhumeijia.wuye.bean.UserPaymentRefund
*/
@Mapper
public interface UserPaymentRefundMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserPaymentRefund record);

    int insertSelective(UserPaymentRefund record);

    UserPaymentRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPaymentRefund record);

    int updateByPrimaryKey(UserPaymentRefund record);

    int getCountAll();

    List<UserPaymentRefund> getAllUserPaymentRefund(int i, int limit);

    int getCountByName(String status);

    List<UserPaymentRefund> findUserPaymentRefund(int i, int limit, int status);

    UserPaymentRefund findUserPaymentRefundById(int id);
}
