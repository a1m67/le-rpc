package com.le.example.provider;

import com.le.example.common.service.UserService;
import com.le.lerpc.RpcApplication;
import com.le.lerpc.config.RpcConfig;
import com.le.lerpc.registry.LocalRegistry;
import com.le.lerpc.server.HttpServer;
import com.le.lerpc.server.VertxHttpServer;
import com.le.lerpc.utils.ConfigUtils;

/**
 * 简易服务提供者示例
 */
public class ProviderExample {
    public static void main(String[] args) {
        // Rpc 框架初始化
        RpcApplication.init();
        System.out.println(RpcApplication.getRpcConfig());
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
