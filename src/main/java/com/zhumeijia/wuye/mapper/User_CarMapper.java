package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.User_Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface User_CarMapper {

    int getCarRecordCount(@Param("id") int id);

    List<User_Car> findCarRecordById(@Param("id")int id,@Param("i") int i,@Param("limit") int limit);

    int findCar(@Param("user_id") Integer user_id);

    List<User_Car> outCar(@Param("user_id")Integer user_id);

    int updateUser_CarByUid(@Param("date") Date date, @Param("uid")Integer uid);

    int fenpei(@Param("user_id")Integer user_id,@Param("car_id") Integer car_id,@Param("date") Date date);

    List<User_Car> findCarByUid(@Param("id") int id);
}
