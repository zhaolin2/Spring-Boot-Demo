package com.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户服务
 * @Author: 赵
 * @date: 2020/7/5
 */
@Service
public class UserService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void register(String name) {
        System.out.println("用户：" + name + "已注册！");
        applicationEventPublisher.publishEvent(new UserRegisterEvent(name));
    }


}
