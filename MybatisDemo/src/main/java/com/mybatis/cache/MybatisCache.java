package com.mybatis.cache;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:mybatis的一级 二级 测试类
 * @Author: zl
 * @date: 2020/7/28
 */
@Component
public class MybatisCache {

    @Autowired
    private SqlSessionFactory factory;

    @Autowired
    MybatisCache mybatisCache;

    public static void main(String[] args) {

    }

    public void showDefaultCacheConfiguration() {
        System.out.println("本地缓存范围: " + factory.getConfiguration().getLocalCacheScope());
        System.out.println("二级缓存是否被启用: " + factory.getConfiguration().isCacheEnabled());
    }

}
