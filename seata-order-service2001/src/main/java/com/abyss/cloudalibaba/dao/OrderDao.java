package com.abyss.cloudalibaba.dao;

import com.abyss.cloudalibaba.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Abyss Watchers
 * @create 2023-01-06 17:57
 */
@Mapper
public interface OrderDao {

    public void createOrder(Order order);

    public void update(@Param("id") Long id, @Param("status") Integer status);
}
