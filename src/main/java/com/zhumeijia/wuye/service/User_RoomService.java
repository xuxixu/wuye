package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Room;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.bean.User_Room;
import com.zhumeijia.wuye.mapper.RoomMapper;
import com.zhumeijia.wuye.mapper.UserMapper;
import com.zhumeijia.wuye.mapper.User_RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class User_RoomService {
    @Autowired
    User_RoomMapper dao;
    @Autowired
    UserMapper udao;
    @Autowired
    RoomMapper rdao;
    public int getRoomRecordCount(int id) {
        return dao.getRoomRecordCount(id);
    }

    public List<User_Room> findRoomRecordById(int id, int page, int limit) {
        System.out.println(id);
        List<User_Room> list = dao.findRoomRecordById(id, (page - 1) * limit, limit);
        System.out.println(list);
        if (list!=null){
            for (User_Room user_room:list){
                List<User> user = udao.findUserById(user_room.getUid());
                List<Room> room = rdao.findRoomByid(user_room.getRid());
                user_room.setUser(user.get(0));
                user_room.setRoom(room.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int findRoom(Integer user_id) {
        return dao.findRoom(user_id);
    }

    public void outRoom(Integer user_id) {
        List<User_Room> list = dao.outRoom(user_id);
        rdao.updateRoomById(list.get(0).getRid());
        dao.updateUser_RoomByUid(new Date(),list.get(0).getUid());
    }

    public int fenpei(Integer user_id, Integer room_id) {
        System.out.println(room_id);
        rdao.updateRoomByIdI(room_id);
        return dao.fenpei(user_id,room_id,new Date());
    }

    public int stopRoomByUserId(int id) {
        List<User_Room> list = dao.findRoomByUid(id);
        if (list.isEmpty()){
            return 1;
        }
        rdao.updateRoomById(list.get(0).getRid());
        return dao.updateUser_RoomByUid(new Date(),id);
    }

}
