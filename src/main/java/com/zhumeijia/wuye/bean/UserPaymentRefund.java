package com.zhumeijia.wuye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentRefund {

  private long id;
  private long upid;
  private long rid;
  private long status;
  private User_Payment user_payment;
  private Refund refund;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUpid() {
    return upid;
  }

  public void setUpid(long upid) {
    this.upid = upid;
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
