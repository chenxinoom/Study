package com.czx.demo.controller;

import com.czx.demo.entry.User;
import com.czx.demo.service.UserService;
import com.czx.demo.service.impl.RuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/testMain")
public class TestMain {

    @Autowired
    private UserService userService;

    @Autowired
    private RuleServiceImpl ruleService;

    @GetMapping("/test")
    public User test() {
        User user = userService.getUserById(1);
        System.out.println(user);
        return user;
//
    }

    @GetMapping("/ruleTest")
    public Object reluTest(String serviceId,String methodName,Integer id){
        HashMap<String,Object> paramMap = new HashMap<>();
        List argObjectList = new ArrayList();
        argObjectList.add(id);
        paramMap.put("serviceId",serviceId);
        paramMap.put("methodName",methodName);
        paramMap.put("argObjectList",argObjectList);
        try {
            Object rule = ruleService.rule(paramMap);
            return rule;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/insertUser")
    public Object ruleInsert(String serviceId, String methodName,@RequestBody User user){
        HashMap<String,Object> paramMap = new HashMap<>();
        List argObjectList = new ArrayList();
        argObjectList.add(user);
        paramMap.put("serviceId",serviceId);
        paramMap.put("methodName",methodName);
        paramMap.put("argObjectList",argObjectList);
        try {
            Object rule = ruleService.rule(paramMap);
            return rule;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @PostMapping
//    public Object ruleInsertTest(String service,String methodName,User user){
//
//    }


}
