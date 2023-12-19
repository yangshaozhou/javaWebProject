package com.takeout.service;

import com.takeout.entity.Admin;

public interface UserService {
//    用户登录
    Admin auth(String username, String password);

//    用户注册
    boolean register(String username,String password);
}
