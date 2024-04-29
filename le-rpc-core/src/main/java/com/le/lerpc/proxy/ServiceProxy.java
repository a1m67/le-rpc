package com.le.lerpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.le.lerpc.RpcApplication;
import com.le.lerpc.model.RpcRequest;
import com.le.lerpc.model.RpcResponse;
import com.le.lerpc.serializer.JdkSerializer;
import com.le.lerpc.serializer.Serializer;
import com.le.lerpc.serializer.SerializerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());


        // 构造发送请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化（Java 对象 => 字节数组）
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 发送请求
            // todo注意，这里地址被硬编码了(需要使用注册中心和服务发现机制解决)
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化 (字节数组-> 对象)
                RpcResponse response = serializer.deserialize(result,RpcResponse.class);
                return response.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
