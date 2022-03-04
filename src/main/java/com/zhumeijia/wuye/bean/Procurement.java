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
  private java.sql.Timestamp createtime;
  private long status;
  private double totalmoney;
  private Goodstype goodstype;
  private Warehouse warehouse;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }


  public String getGname() {
    return gname;
  }

  public void setGname(String gname) {
    this.gname = gname;
  }


  public long getGtid() {
    return gtid;
  }

  public void setGtid(long gtid) {
    this.gtid = gtid;
  }


  public long getWhid() {
    return whid;
  }

  public void setWhid(long whid) {
    this.whid = whid;
  }


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public double getTotalmoney() {
    return totalmoney;
  }

  public void setTotalmoney(double totalmoney) {
    this.totalmoney = totalmoney;
  }

}
