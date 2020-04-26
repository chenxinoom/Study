package com.czx.demo.service;

import com.czx.demo.entry.User;

import java.util.Map;

public interface RuleTestService {

    void test01();

    void test02(Map param);

    void test03(Map<String,Object> param);

    User test04(Map<String,Object> param);

    void test05(Map<String,Object> param);

    void test06(Map<String,Object> param);
}
