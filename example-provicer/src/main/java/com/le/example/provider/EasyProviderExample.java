package com.le.example.provider;


import com.le.example.common.service.UserService;
import com.le.lerpc.registry.LocalRegistry;
import com.le.lerpc.server.HttpServer;
import com.le.lerpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
