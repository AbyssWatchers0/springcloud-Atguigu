package com.abyss.springcloud.service;

import com.abyss.springcloud.dao.PaymentMapper;
import com.abyss.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Abyss Watchers
 * @create 2022-12-10 19:33
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return  paymentMapper.add(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
