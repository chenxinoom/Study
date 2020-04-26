package com.czx.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.czx.demo.entry.User;
import com.czx.demo.service.RuleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RuleTestServiceImpl implements RuleTestService {


    @Override
    public void test01(){
        System.out.println("执行到");
    }

    @Override
    public void test02(Map param){
        String message = (String)param.get("message");
        System.out.println(message);
    }

    @Override
    public void test03(Map<String,Object> param){
        Object user = param.get("user");
        Class<?> aClass = user.getClass();
        System.out.println(user);
    }

    @Override
    public User test04(Map<String,Object> param){
        Object userParam= param.get("user");
        User user = JSON.parseObject(JSON.toJSONString(userParam), User.class);
        return user;
    }

    //先判断空不空要不会报空指针异常
    @Override
    public void test05(Map<String,Object> param){
        if ((boolean)param.get("boolean")){
            System.out.println("前端传来的数据为真");
        }else {
            System.out.println("前端传来的数据为假");
        }
    }

    @Override
    public void test06(Map<String,Object> param){
        System.out.println(param.get("int"));
        System.out.println(param.get("int").getClass());
    }

//    @Override
//    public User test05(Map<String,Object> param){
//        JSON userParam= (JSON)param.get("user");
//        User user = userParam.toJavaObject(new TypeReference<User>() {
//        });
//        return user;
//    }
}
