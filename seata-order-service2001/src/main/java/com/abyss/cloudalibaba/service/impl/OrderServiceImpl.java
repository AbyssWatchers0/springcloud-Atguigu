package com.abyss.cloudalibaba.service.impl;

import com.abyss.cloudalibaba.dao.OrderDao;
import com.abyss.cloudalibaba.entity.Order;
import com.abyss.cloudalibaba.service.AccountService;
import com.abyss.cloudalibaba.service.OrderService;
import com.abyss.cloudalibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Abyss Watchers
 * @create 2023-01-06 18:29
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    AccountService accountService;
    @Resource
    StorageService storageService;
    @Resource
    OrderDao orderDao;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * @param order
     */
    @Override
    @GlobalTransactional(name = "abyss-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("开始新建订单");
        orderDao.createOrder(order);

        log.info("订单微服务开始调用库存，减库存");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("订单微服务开始调用库存，减库存end");


        log.info("订单微服务开始调用账户，减账户余额");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("订单微服务开始调用账户，减账户余额end");


        log.info("修改订单状态");
        orderDao.update(order.getId(), order.getStatus() == null ? 0 : order.getStatus());
        log.info("下订单结束");
    }
}
