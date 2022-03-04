package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Payment;
import com.zhumeijia.wuye.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentMapper dao;
    public int getCount() {
        return dao.getCountAll();
    }

    public List<Payment> getAllPayments(int page, int limit) {
        return dao.getAllPayment((page-1)*limit,limit);
    }

    public int addPayment(Payment payment) {
        return dao.addPayment(payment);
    }

    public int updatePayment(Payment payment) {
        return dao.updatePayment(payment);
    }

    public int delPayment(int id) {
        return dao.delPayment(id);
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<Payment> findPayment(int page, int limit, String name) {
        return dao.findPayment((page-1)*limit,limit,name);
    }

    public List<Payment> getAllPayments() {
        return dao.getAllPayments();
    }
}
