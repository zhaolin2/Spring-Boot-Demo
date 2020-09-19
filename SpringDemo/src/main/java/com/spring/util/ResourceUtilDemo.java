package com.spring.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @Description:资源加载demo
 * @Author: zl
 * @date: 2020/7/17
 */
public class ResourceUtilDemo {
    public static void main(String[] args) throws FileNotFoundException, IntrospectionException {
        File file = ResourceUtils.getFile("");
        BeanInfo bi = Introspector.getBeanInfo(ResourceUtilDemo.class,Object.class);
        System.out.println(Arrays.toString(bi.getPropertyDescriptors()));
    }
}
