package com.czx.demo;

import com.alibaba.fastjson.JSON;
import com.czx.demo.entry.User;
import com.czx.demo.utils.Condition;

import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void test() {
        User user = new User();
        user.setId(3);
        user.setAge(13);
        user.setName("chen");
        user.setClassName("wulian");

        String s = JSON.toJSONString(user);
        System.out.println(s);
    }

    @org.junit.Test
    public void test01(){
        int a = 1;
        System.out.println(a == 01);
    }

    @org.junit.Test
    public void test02(){
        Condition condition = new Condition("ruleTestService", "test01");
        String s = JSON.toJSONString(condition);
        System.out.println(s);
    }

    @org.junit.Test
    public void test03(){
        Map map = new HashMap();
        map.put("char",'c');

        String s = JSON.toJSONString(map);
        System.out.println(s);

        Map t = JSON.toJavaObject( JSON.parseObject(s), Map.class);
        System.out.println(t.get("char").getClass());
    }

}
