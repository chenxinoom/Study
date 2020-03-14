package com.build;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class BuildTest {


    @Test
    public void test(){
//        System.out.println("单元测试");
        System.out.println(test01());
    }

    /**
     * json的/n问题
     * @return
     */
    public String test01(){

        StringBuffer sb = new StringBuffer();
        sb.append("单元测试" + '\n');
        sb.append("第二行");
        String s = sb.toString();
        String s1 = JSON.toJSONString(s);
        return s1;
    }
}
