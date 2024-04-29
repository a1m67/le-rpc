package com.le.example.consumer;

import com.le.example.common.service.UserService;
import com.le.lerpc.config.RpcConfig;
import com.le.lerpc.proxy.ServiceProxyFactory;
import com.le.lerpc.utils.ConfigUtils;

/**
 * 简易服务消费者示例
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
        UserService userService = ServiceProxyFactory.getMockProxy(UserService.class);
        System.out.println(userService.getNumber());
    }
}
