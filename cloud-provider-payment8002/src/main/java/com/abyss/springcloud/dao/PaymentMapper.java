package com.abyss.springcloud.dao;

import com.abyss.springcloud.entities.Payment;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Abyss Watchers
 * @create 2022-12-10 19:15
 */
@Mapper
public interface PaymentMapper {
    @MapKey("id")
    public int add(Payment payment);

    public Payment getPaymentById(Long id);
}
