package com.mvc.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:web转换器数据绑定器测试Controller
 * @Author: zl
 * @date: 2020/7/12
 */

@Controller
public class WelcomeController {

//    @InitBinder
//    public void iniiBinder(WebDataBinder binder){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        format.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
//    }


    @ResponseBody
    @RequestMapping("/welcome")
    public void welcome(HttpServletResponse response , Date date) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        response.getWriter().write("welcome here again !  current date is "+format.format(date));
    }

}
