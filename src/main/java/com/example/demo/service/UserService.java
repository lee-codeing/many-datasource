package com.example.demo.service;

import com.example.demo.entity.UserEntity;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 用户服务接口
 * @date 2020/5/27 8:47
 */
public interface UserService {
    /**
     * 创建新用户
     * @param userEntity
     * @return
     */
    int addUser(UserEntity userEntity) throws Exception;

    /**
     * 主库添加用户
     * @param userEntity
     * @throws Exception
     */
    void masterAddUser(UserEntity userEntity) throws Exception;
}
