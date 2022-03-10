package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.*;
import com.zhumeijia.wuye.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_Payment_RefundService {
    @Autowired
    UserPaymentRefundMapper dao;
    @Autowired
    RefundMapper rdao;
    @Autowired
    User_PaymentMapper updao;
    @Autowired
    UserMapper udao;
    @Autowired
    PaymentMapper pdao;

    public int getCount() {
        return dao.getCountAll();
    }

    public List<UserPaymentRefund> getAllUserPaymentRefund(int page, int limit) {
        List<UserPaymentRefund> list = dao.getAllUserPaymentRefund((page - 1) * limit, limit);
        if (list!=null){
            for (UserPaymentRefund procurementrefund:list){
                Refund goodstypes = rdao.findGoodstypeById(procurementrefund.getRid());
                User_Payment user_payment = updao.getPaymentById(procurementrefund.getUpid());
                if (user_payment != null)
                {
                    List<User> user = udao.findUserById(user_payment.getUid());
                    List<Payment> payment = pdao.findPaymentById(user_payment.getPid());
                    user_payment.setUser(user.get(0));
                    user_payment.setPayment(payment.get(0));
                }
                procurementrefund.setRefund(goodstypes);
                procurementrefund.setUser_payment(user_payment);
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String status) {
        return dao.getCountByName(status);
    }

    public List<UserPaymentRefund> findUserPaymentRefund(int page, int limit, int status) {
        List<UserPaymentRefund> list = dao.findUserPaymentRefund((page - 1) * limit, limit, status);
        if (list!=null){
            for (UserPaymentRefund procurementrefund:list){
                Refund goodstypes = rdao.findGoodstypeById(procurementrefund.getRid());
                User_Payment user_payment = updao.getPaymentById(procurementrefund.getUpid());
                if (user_payment != null)
                {
                    List<User> user = udao.findUserById(user_payment.getUid());
                    List<Payment> payment = pdao.findPaymentById(user_payment.getPid());
                    user_payment.setUser(user.get(0));
                    user_payment.setPayment(payment.get(0));
                }
                procurementrefund.setRefund(goodstypes);
                procurementrefund.setUser_payment(user_payment);
            }
            return list;
        }else{
            return null;
        }
    }


    public UserPaymentRefund getUserPaymentRefundById(int id) {
        UserPaymentRefund userPaymentRefundById = dao.findUserPaymentRefundById(id);
        return userPaymentRefundById;
    }

    public void addUser_Refund(Integer id, Refund refund) {
        UserPaymentRefund user_payment_refund = new UserPaymentRefund();
        Refund refundByOut_trade_no = rdao.findRefundByOut_Trade_NO(refund.getOutTradeNo());
        user_payment_refund.setUpid(id);
        user_payment_refund.setRid(refundByOut_trade_no.getId());
        dao.insert(user_payment_refund);
    }

    public void updateStatusByUserPaymentRefund(UserPaymentRefund user_payment_refund) {
        dao.updateByPrimaryKeySelective(user_payment_refund);
        updao.updateStatusById(user_payment_refund.getUpid());
    }
}
