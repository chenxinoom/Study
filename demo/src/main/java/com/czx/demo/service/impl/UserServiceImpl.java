package com.czx.demo.service.impl;

import com.czx.demo.entry.User;
import com.czx.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("userService")
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserDao userDao;

    public void start(){
        System.out.println("servier开始");
    }

    public void end(){
        System.out.println("service结束");
    }

    public String insertById(String id){
        System.out.println("被插入的对象的id为" + id);
        return "被插入的对象的id为" + id;
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

//    @Override
//    public User getUserById(Integer id) {
//        User user = userDao.selectById(id);
//        return user;
//    }
//
//    @Override
//    public int insertUser(User user) {
//        int insert = userDao.insert(user);
//        return insert;
//    }


}
