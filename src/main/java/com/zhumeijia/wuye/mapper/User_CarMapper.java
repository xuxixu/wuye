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

    int findCar(@Param("uid") Integer uid);

    List<User_Car> outCar(@Param("uid")Integer uid);

    int updateUser_CarByUid(@Param("date") Date date, @Param("uid")Integer uid);

    int fenpei(@Param("uid")Integer uid,@Param("cid") Integer cid,@Param("date") Date date);

    List<User_Car> findCarByUid(@Param("id") int id);
}
