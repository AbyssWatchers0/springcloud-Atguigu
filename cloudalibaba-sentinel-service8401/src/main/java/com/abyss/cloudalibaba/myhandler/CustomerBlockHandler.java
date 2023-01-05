package com.abyss.cloudalibaba.myhandler;

import com.abyss.springcloud.entities.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author Abyss Watchers
 * @create 2023-01-05 19:48
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(440, "my block exception handler---1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(440, "my block exception handler---2");
    }
}
