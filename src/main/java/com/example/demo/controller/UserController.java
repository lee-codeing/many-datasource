package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 用户控制
 * @date 2020/5/27 8:45
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
//    @TargetDataSource(DataSourceKey.SLAVE)
    public String addUser(@RequestBody UserEntity userEntity){
        try {
            userService.addUser(userEntity);
            userService.masterAddUser(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "添加成功";
    }
}
