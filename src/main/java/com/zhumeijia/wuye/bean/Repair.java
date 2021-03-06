package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repair {
    Integer id;
    String content;
    Integer status;
    Date time;
    Integer uid;
    User user;
    String result;
}
