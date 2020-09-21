package com.mybatis.sourceCode;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.orm.SpringBootDemoOrmMybatisPlusApplication;
import com.orm.entity.ThemeDetail;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.SimpleStatementHandler;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 一级缓存 session(sqlsession)
 * 二级缓存可以使用第三方的缓存
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootDemoOrmMybatisPlusApplication.class})
public class SqlSeession {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /**
     * 查询使用 namespace+id
     */
    @Test
    public void sqlSessionFactoryTest(){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Object> objects =
                sqlSession.selectList("com.orm.mapper.ThemeDetailMapper.findThemeDetailById");

        //修改需要提交事务
        sqlSession.commit();

        sqlSession.close();

        Reflector reflector = new Reflector(ThemeDetail.class);
        DefaultReflectorFactory defaultReflectorFactory = new DefaultReflectorFactory();

        JdbcType.valueOf("");

        //type-> jdbctype -> typehandler
        new TypeHandlerRegistry();

        // Java数据类型 与 JDBC数据类型 的关系往往是一对多，
        // 所以一般会先根据 Java数据类型 获取 Map<JdbcType, TypeHandler<?>>对象
        // 再根据 JDBC数据类型 获取对应的 TypeHandler对象
        Class<JacksonTypeHandler> jacksonTypeHandlerClass = JacksonTypeHandler.class;


        Class<BaseStatementHandler> baseStatementHandlerClass = BaseStatementHandler.class;
        //给sql注入参数
        Class<DefaultParameterHandler> defaultParameterHandlerClass = DefaultParameterHandler.class;

        Class<SimpleStatementHandler> simpleStatementHandlerClass = SimpleStatementHandler.class;
        Class<PreparedStatementHandler> preparedStatementHandlerClass = PreparedStatementHandler.class;

        /**
         * 定义了一二级缓存等基本设施的实现
         */
        Class<BaseExecutor> baseExecutorClass = BaseExecutor.class;
        //使用模板方法 不用关心缓存等操作 实现四个查询方法即可
        Class<SimpleExecutor> simpleExecutorClass = SimpleExecutor.class;

        //复用statement
        Class<ReuseExecutor> reuseExecutorClass = ReuseExecutor.class;


    }


}
