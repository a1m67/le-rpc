package com.le.example.common.service;

import com.le.example.common.model.User;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);
    default short getNumber() {
        return 1;
    }
}
