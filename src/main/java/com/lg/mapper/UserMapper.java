package com.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.pojo.User;
import org.apache.ibatis.annotations.Param;

// BaseMapper中有一些简单的增删改查
public interface UserMapper extends BaseMapper<User> {

    public User queryById(@Param("id") Long id);

}
