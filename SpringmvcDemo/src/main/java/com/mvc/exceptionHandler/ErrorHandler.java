package com.mvc.exceptionHandler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:自定义异常处理
 * @Author: zl
 * @date: 2020/7/12
 */
public class ErrorHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "error/error-404";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
