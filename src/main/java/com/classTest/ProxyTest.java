package com.classTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    interface AppService{
        void createApp(String username);
    }

    class AppServiceImpl implements AppService {

        @Override
        public void createApp(String username) {
            System.out.println("创建app name : " + username);
        }
    }

    public static void main(String[] args) {
        AppService service = new ProxyTest().new AppServiceImpl();
        AppService appService = (AppService) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理后的");
                method.invoke(service,args);
                System.out.println("执行结束");
                return null;
            }
        });
        appService.createApp("nba");

    }

}
