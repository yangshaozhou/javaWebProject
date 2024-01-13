package com.takeout.service;

import com.takeout.entity.Admin;
import com.takeout.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
//    用户登录
    Admin auth(String username, String password);

//    用户注册
    boolean register(String username,String password);

//    查看所有用户
    List<User> searchAllUser(int page,int num);

//    查询用户个数
    int getCountUser();

//    删除用户
    boolean deleteUser(String name);
}
