package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.User_Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface User_RoomMapper {

    int getRoomRecordCount(@Param("id") int id);

    List<User_Room> findRoomRecordById(@Param("id")int id,@Param("i") int i,@Param("limit") int limit);

    int findRoom(@Param("user_id") Integer user_id);

    List<User_Room> outRoom(@Param("user_id")Integer user_id);

    int updateUser_RoomByUid(@Param("date") Date date, @Param("uid")Integer uid);

    int fenpei(@Param("user_id")Integer user_id,@Param("room_id") Integer room_id,@Param("date") Date date);

    List<User_Room> findRoomByUid(@Param("id") int id);
}
