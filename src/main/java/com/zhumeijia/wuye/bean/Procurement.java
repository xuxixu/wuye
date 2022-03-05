package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Procurement {

  private long id;
  private long number;
  private String gname;
  private long gtid;
  private long whid;
  private Date time;
  private long status;
  private double totalmoney;
  private Goodstype goodstype;
  private Warehouse warehouse;

}
