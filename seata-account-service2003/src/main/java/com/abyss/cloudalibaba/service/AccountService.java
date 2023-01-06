package com.abyss.cloudalibaba.service;

import com.abyss.cloudalibaba.dao.AccountDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author Abyss Watchers
 * @create 2023-01-06 19:37
 */
@Service
@Slf4j
public class AccountService {
    @Autowired
    AccountDao accountDao;

    public void decrease(Long userId, BigDecimal money) {
        log.info("扣余额开始");
        // 模拟超时异常，全局事务回滚
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountDao.decrease(userId, money);
        log.info("扣余额结束");
    }
}
