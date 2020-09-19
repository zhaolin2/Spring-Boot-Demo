package com.orm.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Description:
 * @Author: zl
 * @date: 2020/9/18
 */
@Accessors(chain = true)
@Data
public class ThemeDetail {
    @DateTimeFormat(pattern="HH:ss:mm")
    LocalDate uploadDate;
}
