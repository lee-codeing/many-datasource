package com.example.demo.service.impl;

import com.example.demo.config.DataSourceKey;
import com.example.demo.datesource.TargetDataSource;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.slave.UserSlaveMapper;
import com.example.demo.service.UserSlaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 从库服务接口
 * @date 2020/5/27 15:21
 */
@Service
public class UserSlaveServiceImpl implements UserSlaveService {
    @Resource
    private UserSlaveMapper userSlaveMapper;

    @Override
    @TargetDataSource(DataSourceKey.SLAVE)
    public void addSlaveUser(UserEntity userEntity) {

        userSlaveMapper.insertUser(userEntity);
    }
}
