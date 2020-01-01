package com.build;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        User user = new User.Builder()
                .setAddr("北京").setBriday(new Date()).setAge(24).setName("chen").build();
        System.out.println(user);
    }
}
