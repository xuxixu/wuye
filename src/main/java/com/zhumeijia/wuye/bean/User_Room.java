package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Room {
    Integer id;
    Integer uid;
    Integer rid;
    Date inTime;
    Date outTime;
    User user;
    Room room;
}
