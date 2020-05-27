package com.example.demo.entity;

import lombok.Data;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 用户实体
 * @date 2020/5/27 8:31
 */
@Data
public class UserEntity {
    private String username;
    private String password;
    private Integer datasourceType;
}
