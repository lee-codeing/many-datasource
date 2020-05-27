package com.example.demo.service.impl;

import com.example.demo.config.DataSourceKey;
import com.example.demo.datesource.TargetDataSource;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.master.UserMasterMapper;
import com.example.demo.mapper.slave.UserSlaveMapper;
import com.example.demo.service.UserService;
import com.example.demo.service.UserSlaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 用户服务接口
 * @date 2020/5/27 8:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMasterMapper userMasterMapper;

    @Resource
    private UserSlaveMapper userSlaveMapper;

    @Autowired
    private UserSlaveService userSlaveService;

    @Override
    @TargetDataSource(DataSourceKey.SLAVE)
    public int addUser(UserEntity userEntity) throws Exception {
        userSlaveMapper.insertUser(userEntity);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void masterAddUser(UserEntity userEntity) throws Exception {
        int count = userMasterMapper.insertUser(userEntity);
        if (count <= 0) {
            throw new Exception("主库添加用户异常");
        }
    }

    @TargetDataSource(DataSourceKey.SLAVE)
    public void slaveAddUser(UserEntity userEntity) {
        userSlaveMapper.insertUser(userEntity);
    }
}
