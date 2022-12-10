package com.abyss.springcloud.service;

import com.abyss.springcloud.dao.PaymentMapper;
import com.abyss.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Abyss Watchers
 * @create 2022-12-10 19:32
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);

}
