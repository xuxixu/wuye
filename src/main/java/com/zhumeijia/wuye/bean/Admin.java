package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    String email;
    String username;
    String password;
    String phone;
    Integer sex;
    Integer id;
    Integer rid;
    Role role;
    String image;
    String desc;
}
