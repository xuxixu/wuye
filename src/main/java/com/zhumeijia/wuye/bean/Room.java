package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    Integer id;
    String name;
    String area;
    Integer status;
    Integer did;
    Danyuan danyuan;
}
