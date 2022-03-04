package com.zhumeijia.wuye.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

  private long id;
  private String name;
  private String local;
}
