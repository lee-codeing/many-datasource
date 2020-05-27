package com.example.demo.mapper.slave;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 从库用户持久
 * @date 2020/5/27 8:28
 */
@Mapper
public interface UserSlaveMapper {
    /**
     * 添加新用户
     * @param userEntity
     * @return
     */
    @Insert("insert user (username, password, datasource_type) values(#{userEntity.username}, #{userEntity.password}, #{userEntity.datasourceType})")
    int insertUser(@Param("userEntity") UserEntity userEntity);
}
