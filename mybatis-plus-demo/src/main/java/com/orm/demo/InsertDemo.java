package com.orm.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orm.entity.User;
import com.orm.mapper.UserMapper;
import com.orm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zl
 * @date: 2020/9/6
 */
public class InsertDemo {

    @Autowired
    UserMapper userMapper;

    public void testInsert() {
        User user = new User();
        user.setLastLoginTime(new Date());
        userMapper.insert(user);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(user.getId());
    }

    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("更新测试");

        //根据id进行更新，没有传值的属性就不会更新
        userMapper.updateById(user);

        //AR方法 ActiveRecord 领域驱动 一个类代表一个一条记录
        /**
         * 需要继承model的实体类和
         * 一个继承BaseMapper的Dao类
          */
        user.insert();
        int result = user.selectCount(new QueryWrapper<User>().eq("gender",1));


        IPage<User> page =
                user.selectPage(new Page<>(1,4),
                        new QueryWrapper<User>().like("name","刘"));
        List<User> users = page.getRecords();
        System.out.println(users);


    }

}
