package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomMapper {
    public int getCountAll();
    public List<Room> getAllRoom(@Param("page") int page,@Param("limit") int limit);
    public int addRoom(Room room);
    public int updateRoom(Room room);
    public int delRoom(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Room> findRoom(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Room> getAllRooms();
    public int getFreeCount();
    List<Room> getAllFreeRoom(int danyuan_id);
}
