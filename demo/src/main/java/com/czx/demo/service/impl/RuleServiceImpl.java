package com.czx.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RuleServiceImpl {
    @Autowired
    public ApplicationContext applicationContext;

    public Object rule(Map<String,Object> paramMap) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //方法的在ioc容器中的id
        String serviceId = (String)paramMap.get("serviceId");
        //类的方法
        String methodName = (String)paramMap.get("methodName");
        //参数
        List<Object> argObjectList = (List)paramMap.get("argObjectList");
        //参数的类型
        Class[] argClasses = null;
        //参数对象数组
        Object[] argObjects = null;
        if (argObjectList.size() != 0){
            argClasses = new Class[argObjectList.size()];
            argObjects = new Object[argObjectList.size()];
            //获取参数类型
            for (int i = 0; i < argObjectList.size(); i++) {
                argClasses[i] = argObjectList.get(i).getClass();
                argObjects[i] = argObjectList.get(i);
            }

        }
        //获取要执行的对象
        Object service = applicationContext.getBean(serviceId);

        Method method = service.getClass().getMethod(methodName, argClasses);
        Object invoke = method.invoke(service, argObjects);
        return invoke;
    }
}
