package com.le.example.consumer;

import com.le.example.common.model.User;
import com.le.example.common.service.UserService;
import com.le.lerpc.proxy.ServiceProxyFactory;

/**
 * 简易消费者示例
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
//        // 静态代理
//        UserService userService = null;
//        userService = new UserServiceProxy();

        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        //todo 需获取UserService的实现类
        User user = new User();
        user.setName("John");

        //调用

        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(user.getName());
        } else {
            System.out.println("user == null");
        }
    }

}
