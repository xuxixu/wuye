package com.zhumeijia.wuye.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName refund
 */
@Data
public class Refund implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String outTradeNo;

    /**
     * 
     */
    private Integer tradeNo;

    /**
     * 
     */
    private Double totalAmount;

    private static final long serialVersionUID = 1L;
}