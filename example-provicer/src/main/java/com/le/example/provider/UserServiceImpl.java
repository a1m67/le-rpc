package com.le.example.provider;

import com.le.example.common.model.User;
import com.le.example.common.service.UserService;

/**
 * 服务实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名" + user.getName());
        return user;
    }
}
