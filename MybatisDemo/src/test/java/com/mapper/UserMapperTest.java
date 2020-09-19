package com.mapper;

import com.mybatis.MybatisSpringApplication;
import com.mybatis.entity.User;
import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Description:mapper缓存测试
 * @Author: zl
 * @date: 2020/7/28
 *
 * sqlsession:类似于JDBC中的Connection 每次用完要记得关闭
 * 每个线程都应该有它自己的SqlSession实例.SqlSession的实例不能被共享,同时SqlSession也是线程不安全的
 * 一级缓存 只会在sqlsession中来共享
 * 二级缓存 同一个namespace下的所有操作语句，都影响着同一个Cache，即二级缓存被多个SqlSession共享，是一个全局的变量
 * MyBatis的二级缓存相对于一级缓存来说，实现了SqlSession之间缓存数据的共享，同时粒度更加的细，能够到namespace级别
 * MyBatis在多表查询时，极大可能会出现脏数据，有设计上的缺陷，安全使用二级缓存的条件比较苛刻
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisSpringApplication.class)
public class UserMapperTest {

    @Autowired
    private SqlSessionFactory factory;


    /**
     * 默认是 SESSION 二级缓存也被启用
     */
    @Test
    public void showDefaultCacheConfiguration() {
        System.out.println("本地缓存范围: " + factory.getConfiguration().getLocalCacheScope());
        System.out.println("二级缓存是否被启用: " + factory.getConfiguration().isCacheEnabled());
    }

    /**
     * 实际只会查询一次
     * @throws Exception
     */
    @Test
    public void testLocalCache() throws Exception {
        SqlSession sqlSession = factory.openSession(true); // 自动提交事务
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        System.out.println(mapper.selectUserById(1L));
        System.out.println(mapper.selectUserById(1L));
        System.out.println(mapper.selectUserById(1L));

        sqlSession.close();
    }

    @Test
    public void testLocalCacheClear() throws Exception {
        SqlSession sqlSession = factory.openSession(true); // 自动提交事务
        UserMapper studentMapper = sqlSession.getMapper(UserMapper.class);

        System.out.println(studentMapper.selectUserById(1L));
        User user = new User();
        user.setCreateTime(new Date());
        System.out.println("增加了" + studentMapper.saveUser(user));
        System.out.println(studentMapper.selectUserById(1L));

        sqlSession.close();
    }

    /**
     * 一次更新之后 就需要重新开始查询
     * @throws Exception
     */
    @Test
    public void addStudent() throws Exception {
        SqlSession sqlSession = factory.openSession(true); // 自动提交事务
        UserMapper studentMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(studentMapper.selectUserById(1L));
        User build = User.builder().id(3L).createTime(new Date()).build();

        System.out.println("增加了" + studentMapper.saveUser(build) + "个学生");
        System.out.println(studentMapper.selectUserById(1L));
        sqlSession.close();
    }
}
