package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Car;
import com.zhumeijia.wuye.bean.Danyuan;
import com.zhumeijia.wuye.bean.Room;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.mapper.CarMapper;
import com.zhumeijia.wuye.mapper.DanyuanMapper;
import com.zhumeijia.wuye.mapper.RoomMapper;
import com.zhumeijia.wuye.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper dao;
    @Autowired
    CarMapper cdao;
    @Autowired
    RoomMapper rdao;
    @Autowired
    DanyuanMapper ddao;
    public int getCount() {
        return dao.getCountAll();
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<User> getUsers(int page, int limit) {
        List<User> list = dao.getAllUser((page-1)*limit, limit);
        if (list!=null){
            for (User user:list){
                List<Car> car = cdao.findCarByUid(user.getId());
                List<Room> room = rdao.findRoomByUid(user.getId());
                if (!car.isEmpty()){
                    user.setCar(car.get(0));
                }
                if (!room.isEmpty()) {
                    List<Danyuan> danyuan = ddao.findDanyuanById(room.get(0).getDid());
                    room.get(0).setDanyuan(danyuan.get(0));
                    user.setRoom(room.get(0));
                }
            }
            return list;
        }else{
            return null;
        }
    }

    public List<User> findUser(int page, int limit, String name) {
        return dao.findUser((page-1)*limit,limit,name);
    }

    public int addUser(User user) {
        int count = dao.findUserByPhone(user.getPhone());
        if (count != 0){
            return 0;
        }
        return dao.addUser(user);
    }

    public int updateUser(User user) {
        return dao.updateUser(user);
    }

    public int delUser(int id) {
        return dao.delUser(id);
    }

    public User loginByPassword(String phone, String password) {return dao.loginByPassword(phone,password);
    }

    public int updatePass(Integer id, String newPsw) {
        return dao.updatePass(id,newPsw);
    }

    public User getUserById(Integer id) {
        User user = dao.getUserById(id);
        if (user!=null){
                List<Car> car = cdao.findCarByUid(user.getId());
                List<Room> room = rdao.findRoomByUid(user.getId());
                if (!car.isEmpty()){
                    user.setCar(car.get(0));
                }
                if (!room.isEmpty()) {
                    List<Danyuan> danyuan = ddao.findDanyuanById(room.get(0).getDid());
                    room.get(0).setDanyuan(danyuan.get(0));
                    user.setRoom(room.get(0));
                }

            return user;
        }else{
            return null;
        }
    }
}
