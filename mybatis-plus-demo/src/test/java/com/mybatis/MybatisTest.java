package com.mybatis;

import com.orm.SpringBootDemoOrmMybatisPlusApplication;
import com.orm.entity.ThemeDetail;
import com.orm.mapper.ThemeDetailMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author: zl
 * @date: 2020/9/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootDemoOrmMybatisPlusApplication.class})
public class MybatisTest {

    @Autowired
    ThemeDetailMapper themeDetailMapper;

    @Test
    public void LocalDate(){
//        ThemeDetail themeDetailById = themeDetailMapper.findThemeDetailById();
//        System.out.println(themeDetailById);

        List<ThemeDetail> themeDetailById = themeDetailMapper.findThemeDetailById();
        themeDetailById.forEach(detail ->{
            System.out.println(detail.toString());
        });
    }

}
