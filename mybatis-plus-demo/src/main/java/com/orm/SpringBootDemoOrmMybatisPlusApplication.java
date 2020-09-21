package com.orm;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @package: com.xkcoding.orm.mybatis.plus
 * @description: 启动器
 * @author: yangkai.shen
 * @date: Created in 2018/11/8 16:48
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@MapperScan("com.orm.mapper")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class SpringBootDemoOrmMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoOrmMybatisPlusApplication.class, args);
    }
}
