package com.mvc.config;

import com.mvc.customize.DateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zl
 */
public class DataBinderConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DateConverter dateConver(){
        return new DateConverter();
    }
}
