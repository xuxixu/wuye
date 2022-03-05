package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
    public int getCountAll();
    public List<Payment> getAllPayment(@Param("page") int page,@Param("limit") int limit);
    public int addPayment(Payment payment);
    public int updatePayment(Payment payment);
    public int delPayment(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Payment> findPayment(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Payment> getAllPayments();

    List<Payment> findPaymentById(Integer pid);
}
