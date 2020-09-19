package com.orm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orm.entity.User;

import java.util.List;

/**
 * <p>
 * User Service
 * </p>
 *
 * @package: com.xkcoding.orm.mybatis.plus.service
 * @description: User Service
 * @author: yangkai.shen
 * @date: Created in 2018/11/8 18:10
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface UserService extends IService<User> {

    void addUsers(List<User> userList);
}
