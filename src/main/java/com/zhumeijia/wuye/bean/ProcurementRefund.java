package com.zhumeijia.wuye.bean;


public class ProcurementRefund {

  private long id;
  private long pid;
  private long rid;
  private long status;
  private Procurement procurement;
  private Refund refund;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public long getRid() {
    return rid;
  }

  public void setRid(long rid) {
    this.rid = rid;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
