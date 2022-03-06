package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Car;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.bean.User_Car;
import com.zhumeijia.wuye.mapper.CarMapper;
import com.zhumeijia.wuye.mapper.UserMapper;
import com.zhumeijia.wuye.mapper.User_CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class User_CarService {
    @Autowired
    User_CarMapper dao;
    @Autowired
    CarMapper cdao;
    @Autowired
    UserMapper udao;
    public int getCarRecordCount(int id) {
        return dao.getCarRecordCount(id);
    }

    public List<User_Car> findCarRecordById(int id, int page, int limit) {
        List<User_Car> list = dao.findCarRecordById(id, (page - 1) * limit, limit);
        System.out.println(list);
        if (list!=null){
            for (User_Car user_car:list){
                List<User> user = udao.findUserById(user_car.getUid());
                List<Car> car = cdao.findCarByid(user_car.getCid());
                user_car.setUser(user.get(0));
                user_car.setCar(car.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int findCar(Integer user_id) {
        return dao.findCar(user_id);
    }

    public void outCar(Integer user_id) {
        List<User_Car> list = dao.outCar(user_id);
        cdao.updateCarStatusById(list.get(0).getCid());
        dao.updateUser_CarByUid(new Date(),user_id);
    }

    public int fenpei(Integer user_id, Integer car_id) {
        cdao.updateCarFStatusById(car_id);
        return dao.fenpei(user_id,car_id,new Date());
    }

    public int stopCarByUserId(int id) {
        List<User_Car> list = dao.findCarByUid(id);
        if (list.isEmpty()){
            return 1;
        }
        cdao.updateCarStatusById(list.get(0).getCid());
        return dao.updateUser_CarByUid(new Date(),id);
    }
}
