package com.czx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czx.demo.utils.Condition;
import com.czx.demo.utils.Result;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rule")
public class RuleContoller {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 根据前端ajax来对象
     * @param condition
     * @return
     */
    @RequestMapping("/jsonObject")
    public Result ruleTest(@RequestBody Condition condition) {
        //方法的在ioc容器中的class对象全类名
        String classPath = condition.getClassPath();
        //类的方法
        String method = condition.getMethod();
        //获取到数据
        Map<String, Object> params = condition.getParams();

        Result result = new Result();
        Map<String,Object> data = new HashMap<>();
        Object resultObject = null;
        try {
            //根据全类名获取class对象
            Class<?> aClass = Class.forName(classPath);
            Object service = applicationContext.getBean(aClass);
            if (params == null || params.size() == 0) {
                Method method1 = service.getClass().getMethod(method);
                resultObject = method1.invoke(service);
            } else {
                Method method1 = service.getClass().getMethod(method, Map.class);
                resultObject = method1.invoke(service, params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Result.FAIL);
            result.setMessage("执行失败");
            return result;
        }
        result.setStatus(Result.SUCCESS);
        result.setMessage("执行成功");
        data.put("resultObject",resultObject);
        result.setContentMap(data);
        return result;
    }

    @RequestMapping("/ruleTest")
    public Result ruleTest(String paramString) {

        JSONObject jsonObject = JSON.parseObject(paramString);
        Condition condition = JSONObject.toJavaObject(jsonObject, Condition.class);
        //方法的在ioc容器中的class对象全类名
        String classPath = condition.getClassPath();
        //类的方法
        String method = condition.getMethod();
        //获取到数据
        Map<String, Object> params = condition.getParams();
        //判断参数是否为空
        Result result = new Result();
        if (classPath==null&&method==null){
            result.setStatus(Result.FAIL);
            result.setMessage("类的路径和方法不能为空");
            return result;
        }

        Map<String,Object> data = new HashMap<>();
        Object resultObject = null;
        //根据全类名获取class对象
        Class<?> aClass = null;
        try {
            aClass = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result.setStatus(Result.FAIL);
            result.setMessage("类的路径错误");
        }
        try {
        Object service = applicationContext.getBean(aClass);
            if (params == null || params.size() == 0) {
                Method method1 = service.getClass().getMethod(method);
                resultObject = method1.invoke(service);
            } else {
                Method method1 = service.getClass().getMethod(method, Map.class);
                resultObject = method1.invoke(service, params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Result.FAIL);
            result.setMessage("执行失败");
            return result;
        }
        result.setStatus(Result.SUCCESS);
        result.setMessage("执行成功");
        data.put("resultObject",resultObject);
        result.setContentMap(data);
        return result;
    }

    @RequestMapping("/ruleTest01")
    public void ruleTest01(String serviceId,String method){
        System.out.println(serviceId + " " + method);
    }


}
