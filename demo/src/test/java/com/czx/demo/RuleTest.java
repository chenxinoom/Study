package com.czx.demo;

import com.alibaba.fastjson.JSON;
import com.czx.demo.controller.RuleContoller;
import com.czx.demo.controller.TestMain;
import com.czx.demo.entry.User;
import com.czx.demo.service.RuleTestService;
import com.czx.demo.service.UserService;
import com.czx.demo.service.impl.RuleTestServiceImpl;
import com.czx.demo.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleTest extends DemoApplicationTests{

    @Autowired
    private TestMain testMain;

    @Autowired
    private RuleContoller ruleContoller;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testFindById(){
        User user = new User();
        user.setId(1);
        user.setAge(11);
        user.setName("chen");
        user.setClassName("wulian");
        Assert.assertEquals("信息有误",user,testMain.test());
    }

    @Test
    public void testRule(){
        Object a = testMain.reluTest("userService", "getUserById", 2);
        System.out.println(a);
    }

    @Test
    public void ruleControllerTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("serviceId","userService");
        paramMap.put("methodName","getUserById");
        List<Object> list = new ArrayList<>();
        list.add(1);
        paramMap.put("argObjectList",list);

        String s = JSON.toJSONString(paramMap);
        System.out.println(s);
    }

    @Test
    public void testUserInsert(){
        User user = new User();
        user.setId(2);
        user.setAge(12);
        user.setName("chen");
        user.setClassName("wulian");
        Object a = testMain.ruleInsert("userService", "insertUser", user);
        System.out.println(a);
    }

    @Test
    public void testApplicationContext() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.czx.demo.service.impl.UserServiceImpl");
        Object bean = applicationContext.getBean(aClass);
        System.out.println(bean);
        User userById = ((UserService) bean).getUserById(1);
        System.out.println(userById);
//        UserServiceImpl userService = new UserServiceImpl();
//        System.out.println(userService);
//        User userById1 = userService.getUserById(1);
//        System.out.println(userById1);

    }


}
