package com.build;

import java.util.Date;

public class User {
    private final String name;
    private final int age;
    private final String adrr;
    private final Date briday;

    public User(Builder builder){
        this.name = builder.name;
        this.briday = builder.briday;
        this.adrr = builder.addr;
        this.age = builder.age;
    }

    public final static class Builder{
        private String name;
        private int age;
        private String addr;
        private Date briday;

        public Builder(){
            this.name = "";
            this.addr = "";
            this.age = 0;
            this.briday = new Date();
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setAge(int age){
            this.age = age;
            return this;
        }
        public Builder setAddr(String addr){
            this.addr = addr;
            return this;
        }
        public Builder setBriday(Date briday){
            this.briday = briday;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", adrr='" + adrr + '\'' +
                ", briday=" + briday +
                '}';
    }
}
