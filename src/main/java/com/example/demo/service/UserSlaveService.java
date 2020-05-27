package com.example.demo.service;

import com.example.demo.entity.UserEntity;

/**
 * @author he.Lee
 * @version 1.0
 * @Description:
 * @date 2020/5/27 15:20
 */
public interface UserSlaveService {
    /**
     * 添加从库的用户
     *
     * @param userEntity
     */
    void addSlaveUser(UserEntity userEntity);
}
