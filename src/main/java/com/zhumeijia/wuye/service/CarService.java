package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Building;
import com.zhumeijia.wuye.bean.Car;
import com.zhumeijia.wuye.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Car> getAllCars(int page, int limit) {
        return dao.getAllCars(page,limit);
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
        return dao.getCount(name);
    }

    public List<Car> findCar(int page, int limit, String name) {
        return dao.findCar(page,limit,name);
    }

    public List<Car> getAllFreeCars(int type) {
        return dao.getAllFreeCars(type);
    }

    public int getFreeCount() {
        return dao.getFreeCount();
    }
}
