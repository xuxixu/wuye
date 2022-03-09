package com.zhumeijia.wuye.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_payment_refund
 */
@Data
public class UserPaymentRefund implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer upid;

    /**
     * 
     */
    private Integer rid;

    /**
     * 
     */
    private Integer status;
    private User_Payment user_payment;
    private Refund refund;
    private static final long serialVersionUID = 1L;
}