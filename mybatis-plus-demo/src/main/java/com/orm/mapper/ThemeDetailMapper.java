package com.orm.mapper;

import com.orm.entity.ThemeDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @Author: zl
 * @date: 2020/9/18
 */
@Mapper
public interface ThemeDetailMapper {
    List<ThemeDetail> findThemeDetailById();
}
