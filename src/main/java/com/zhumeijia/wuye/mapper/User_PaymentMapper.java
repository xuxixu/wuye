package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.User_Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface User_PaymentMapper {
    public int getCountAll();
    public List<User_Payment> getAllUser_Payment(@Param("page") int page,@Param("limit") int limit);
    public int addUser_Payment(User_Payment user_uayment);
    public int updateUser_Payment(User_Payment user_uayment);
    public int delUser_Payment(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<User_Payment> findUser_Payment(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<User_Payment> getAllUser_Payments();
   User_Payment findUser_PaymentById(int id);

    List<User_Payment> getAllPaymentDetailsByUid(@Param("page") int page,@Param("limit") int limit,@Param("uid") int uid);
    int getCountByUserId(Integer uid);

    int jiaofei(int id);

    int getFreeCount();

    int fenpei(@Param("uid") Integer uid,@Param("pid") Integer pid,@Param("value") String value,@Param("time") Date time);

    List<User_Payment> findUser_PaymentStatus(@Param("page") int i,@Param("limit") int limit,@Param("name") String name,@Param("uid") Integer id);

    User_Payment getPaymentById(int id);
}
