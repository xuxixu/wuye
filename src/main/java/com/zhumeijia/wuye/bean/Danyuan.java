package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Danyuan {
    Integer id;
    String name;
    int bid;
    Building building;
}
