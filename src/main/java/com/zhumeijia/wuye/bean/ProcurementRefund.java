package com.zhumeijia.wuye.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName procurement_refund
 */
@Data
public class ProcurementRefund implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer pid;

    /**
     * 
     */
    private Integer rid;

    /**
     * 
     */
    private Integer status;
    private Procurement procurement;
    private Refund refund;
    private static final long serialVersionUID = 1L;

}