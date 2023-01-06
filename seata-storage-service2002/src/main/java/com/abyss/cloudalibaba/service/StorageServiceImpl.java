package com.abyss.cloudalibaba.service;

import com.abyss.cloudalibaba.dao.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Abyss Watchers
 * @create 2023-01-06 19:16
 */
@Service
public class StorageServiceImpl implements StorageService{
    @Autowired
    StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }
}
