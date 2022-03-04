package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

  private long id;
  private String name;
  private long number;
  private long gtid;
  private long whid;
  private double prices;
  private Goodstype goodstype;
  private Warehouse warehouse;


}
