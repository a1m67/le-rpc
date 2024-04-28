package com.le.example.consumer;

import com.le.lerpc.config.RpcConfig;
import com.le.lerpc.utils.ConfigUtils;

/**
 * 简易服务消费者示例
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
