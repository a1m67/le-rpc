package com.le.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.le.example.common.model.User;
import com.le.example.common.service.UserService;
import com.le.lerpc.model.RpcRequest;
import com.le.lerpc.model.RpcResponse;
import com.le.lerpc.serializer.JdkSerializer;
import com.le.lerpc.serializer.Serializer;
import java.io.IOException;

/**
 * 静态代理
 */

public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();

        // 构造发送请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            // 序列化（Java 对象 => 字节数组）
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 发送请求
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                        .body(bodyBytes)
                        .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化 (字节数组-> 对象)
                RpcResponse response = serializer.deserialize(result,RpcResponse.class);
                return (User) response.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
