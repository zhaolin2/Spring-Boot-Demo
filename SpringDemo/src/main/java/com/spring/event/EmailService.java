package com.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Description: 邮件服务
 * @Author: 赵
 * @date: 2020/7/5
 */
@Service
public class EmailService {

    @EventListener
    public void listenUserRegisterEvent(UserRegisterEvent userRegisterEvent) {
        System.out.println("邮件服务接到通知，给" + userRegisterEvent.getSource() + "发送邮件...");
    }
}
