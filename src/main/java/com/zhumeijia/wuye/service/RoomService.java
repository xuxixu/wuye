package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Danyuan;
import com.zhumeijia.wuye.bean.Room;
import com.zhumeijia.wuye.mapper.DanyuanMapper;
import com.zhumeijia.wuye.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomMapper dao;
    @Autowired
    DanyuanMapper ddao;

    public int getCount() {
        return dao.getCountAll();
    }

    public List<Room> getAllRooms(int page, int limit) {
        List<Room> list = dao.getAllRoom((page - 1) * limit, limit);
        if (list!=null){
            for (Room room:list){
                List<Danyuan> danyuan = ddao.findDanyuanById(room.getDid());
                room.setDanyuan(danyuan.get(0));
            }
            return list;
        }else{
            return null;
        }
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
        List<Room> list = dao.findRoom((page - 1) * limit, limit, name);
        if (list!=null){
            for (Room room:list){
                List<Danyuan> danyuan = ddao.findDanyuanById(room.getDid());
                room.setDanyuan(danyuan.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Room> getAllFreeRooms(int danyuan_id) {
        return dao.getAllFreeRoom(danyuan_id);
    }

    public int getFreeCount() {
        return dao.getFreeCount();
    }
}
