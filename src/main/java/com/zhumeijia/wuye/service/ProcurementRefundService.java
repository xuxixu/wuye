package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.*;
import com.zhumeijia.wuye.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcurementRefundService {
    @Autowired
    ProcurementRefundMapper dao;
    @Autowired
    RefundMapper rdao;
    @Autowired
    ProcurementMapper pdao;

    public int getCount() {
        return dao.getCountAll();
    }

    public List<ProcurementRefund> getAllProcurementRefund(int page, int limit) {
        List<ProcurementRefund> list = dao.getAllProcurementRefund((page - 1) * limit, limit);
        if (list!=null){
            for (ProcurementRefund procurementrefund:list){
                Refund goodstypes = rdao.findGoodstypeById(procurementrefund.getRid());
                Procurement warehouses = pdao.findProcurementById(procurementrefund.getPid());
                procurementrefund.setRefund(goodstypes);
                procurementrefund.setProcurement(warehouses);
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String status) {
        return dao.getCountByName(status);
    }

    public List<ProcurementRefund> findProcurementRefund(int page, int limit, int status) {
        List<ProcurementRefund> list = dao.findProcurementRefund((page - 1) * limit, limit, status);
        if (list!=null){
            for (ProcurementRefund procurementrefund:list){
                Refund goodstypes = rdao.findGoodstypeById(procurementrefund.getRid());
                Procurement warehouses = pdao.findProcurementById(procurementrefund.getPid());
                procurementrefund.setRefund(goodstypes);
                procurementrefund.setProcurement(warehouses);
            }
            return list;
        }else{
            return null;
        }
    }


    public ProcurementRefund getProcurementRefundById(int id) {
        return dao.findProcurementRefundById(id);
    }

    public void addProcurementRefund(Integer id, Refund refund) {
        ProcurementRefund user_payment_refund = new ProcurementRefund();
        Refund refundByOut_trade_no = rdao.findRefundByOut_Trade_NO(refund.getOutTradeNo());
        user_payment_refund.setPid(id);
        user_payment_refund.setRid(refundByOut_trade_no.getId());
        dao.insert(user_payment_refund);
    }

    public void updateStatusByProcurementRefund(ProcurementRefund procurementRefund) {
        dao.updateByPrimaryKeySelective(procurementRefund);
        pdao.updateStatusById(procurementRefund.getPid());
    }
}
