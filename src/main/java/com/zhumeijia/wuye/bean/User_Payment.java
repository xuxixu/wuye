package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Payment {
    Integer id;
    Integer uid;
    Integer pid;
    String value;
    Date time;
    Integer status;
    User user;
    Payment payment;
}
