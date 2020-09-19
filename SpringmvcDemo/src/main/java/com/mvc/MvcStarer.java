package com.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zl
 */

@EnableAspectJAutoProxy
@SpringBootApplication
public class MvcStarer {

    public static void main(String[] args) {
        SpringApplication.run(MvcStarer.class,args);
    }
}
