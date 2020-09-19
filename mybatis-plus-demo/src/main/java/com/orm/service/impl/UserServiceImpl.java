package com.orm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orm.entity.User;
import com.orm.mapper.UserMapper;
import com.orm.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * User Service
 * </p>
 *
 * @package: com.xkcoding.orm.mybatis.plus.service.impl
 * @description: User Service
 * @author: yangkai.shen
 * @date: Created in 2018/11/8 18:10
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void addUsers(List<User> userList) {

    }
}
