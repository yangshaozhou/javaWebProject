package com.takeout.service.impl;

import com.takeout.entity.Admin;
import com.takeout.mapper.UserMapper;
import com.takeout.service.UserService;
import com.takeout.utils.MybatisUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public Admin auth(String username, String password) {
      try(SqlSession sqlSession = MybatisUtil.getSession()) {
          UserMapper mapper = sqlSession.getMapper(UserMapper.class);
          Admin admin = mapper.getAdmin(username,password);
          return admin;
      }
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean register(String username, String password) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.insertAdmin(username,password);
            return true;
        }catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

}
