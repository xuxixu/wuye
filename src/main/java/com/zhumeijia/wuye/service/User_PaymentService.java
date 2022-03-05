package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Payment;
import com.zhumeijia.wuye.bean.User;
import com.zhumeijia.wuye.bean.User_Payment;
import com.zhumeijia.wuye.mapper.PaymentMapper;
import com.zhumeijia.wuye.mapper.UserMapper;
import com.zhumeijia.wuye.mapper.User_PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class User_PaymentService {
    @Autowired
    User_PaymentMapper dao;
    @Autowired
    UserMapper udao;
    @Autowired
    PaymentMapper pdao;

    public int fenpei(Integer user_id, Integer payment_id, String value) {
        return dao.fenpei(user_id,payment_id,value, new Date());
    }

    public int getCount() {
        return dao.getCountAll();
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit) {
        List<User_Payment> list = dao.getAllUser_Payment((page - 1) * limit, limit);
        if (list!=null){
            for (User_Payment user_payment:list){
                List<User> user = udao.findUserById(user_payment.getUid());
                List<Payment> payment = pdao.findPaymentById(user_payment.getPid());
                user_payment.setUser(user.get(0));
                user_payment.setPayment(payment.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String name) {
        return dao.getCountByName(name);
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit, String name) {

        List<User_Payment> list = dao.findUser_Payment((page - 1) * limit, limit, name);
        if (list!=null){
            for (User_Payment user_payment:list){
                List<User> user = udao.findUserById(user_payment.getUid());
                List<Payment> payment = pdao.findPaymentById(user_payment.getPid());
                user_payment.setUser(user.get(0));
                user_payment.setPayment(payment.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit, int user_id) {
        List<User_Payment> list = dao.getAllPaymentDetailsByUid((page - 1) * limit, limit, user_id);
        if (list!=null){
            for (User_Payment user_payment:list){
                List<User> user = udao.findUserById(user_payment.getUid());
                List<Payment> payment = pdao.findPaymentById(user_payment.getPid());
                user_payment.setUser(user.get(0));
                user_payment.setPayment(payment.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getFreeCount() {
        return dao.getFreeCount();
    }

    public int getCountByUserId(Integer id) {
        return dao.getCountByUserId(id);
    }

    public int getCount(int user_id) {
        return dao.getCountByUserId(user_id);
    }

    public int jiaofei(int id) {return dao.jiaofei(id);
    }
}
