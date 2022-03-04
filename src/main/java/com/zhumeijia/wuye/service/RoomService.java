package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Danyuan;
import com.zhumeijia.wuye.bean.Room;
import com.zhumeijia.wuye.dao.DanyuanDao;
import com.zhumeijia.wuye.dao.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Room> getAllRooms(int page, int limit) {
        return dao.getAllRooms(page,limit);
    }

    public int addRoom(Room room) {
        return dao.addRoom(room);
    }

    public int updateRoom(Room room) {
        return dao.updateRoom(room);
    }

    public int delRoom(int id) {
        return dao.delRoom(id);
    }

    public List<Room> findRoom(int page, int limit, String name) {
        return dao.findRoom(page,limit,name);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Room> getAllFreeRooms(int danyuan_id) {
        return dao.getAllFreeRooms(danyuan_id);
    }

    public int getFreeCount() {
        return dao.getFreeCount();
    }
}
