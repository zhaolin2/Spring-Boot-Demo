package com.orm.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.orm.entity.ThemeDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @Author: zl
 * @date: 2020/9/18
 */
@DS("slave_1")
@Mapper
public interface ThemeDetailMapper {
    List<ThemeDetail> findThemeDetailById();
}
