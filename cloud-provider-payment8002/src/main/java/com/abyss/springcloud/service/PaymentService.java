package com.abyss.springcloud.service;

import com.abyss.springcloud.entities.Payment;

/**
 * @author Abyss Watchers
 * @create 2022-12-10 19:32
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);

}
