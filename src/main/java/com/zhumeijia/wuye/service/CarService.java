package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.bean.Car;
import com.zhumeijia.wuye.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarMapper dao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Car> getAllCars(int page, int limit) {
        return dao.getAllCar((page - 1) * limit,limit);
    }

    public int addCar(Car car) {
        return dao.addCar(car);
    }

    public int updateCar(Car car) {
        return dao.updateCar(car);
    }

    public int delCar(int id) {
        return dao.delCar(id);
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Car> findCar(int page, int limit, String name) {
        return dao.findCar((page - 1) * limit,limit,name);
    }

    public List<Car> getAllFreeCars(int type) {
        return dao.getAllFreeCar(type);
    }

    public int getFreeCount() {
        return dao.getFreeCount();
    }
}
