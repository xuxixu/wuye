package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarMapper {
    public int getCountAll();
    public List<Car> getAllCar(@Param("page") int page,@Param("limit") int limit);
    public int addCar(Car car);
    public int updateCar(Car car);
    public int delCar(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Car> findCar(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Car> getAllCars();
    public int getFreeCount();
    List<Car> getAllFreeCar(int type);
}
