package com.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Description: 用户注册事件
 * @Author: 赵
 * @date: 2020/7/5
 * spring生命周期的一些event
 * {@link ContextRefreshedEvent , ContextStartedEvent , ContextStoppedEvent , ContextClosedEvent , RequestHandledEvent}
 */
public class UserRegisterEvent extends ApplicationEvent {

    private String name;
    public UserRegisterEvent(String name) {
        super(name);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
