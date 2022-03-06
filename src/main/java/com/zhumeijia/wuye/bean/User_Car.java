package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Car {
    Integer id;
    Integer uid;
    Integer cid;
    Date inTime;
    Date outTime;
    User user;
    Car car;
}
