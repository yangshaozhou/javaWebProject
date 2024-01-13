package com.takeout.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.takeout.entity.Admin;
import com.takeout.entity.User;
import com.takeout.mapper.UserMapper;
import com.takeout.service.UserService;
import com.takeout.utils.MybatisUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
          MD5 md5 = new MD5();
          String newPassword = md5.digestHex(password);
          Admin admin = mapper.getAdmin(username,newPassword);
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
            MD5 md5 = new MD5();
           String newPassword = md5.digestHex(password);
            mapper.insertAdmin(username,newPassword);
            return true;
        }catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 分页查找所有用户
     * @return
     */
    @Override
    public List<User> searchAllUser(int page,int num) {
       try(SqlSession sqlSession = MybatisUtil.getSession())  {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           List<User> list = mapper.findUser(page,num);
           return list;
       }catch (PersistenceException e) {
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public int getCountUser() {
       try(SqlSession sqlSession = MybatisUtil.getSession())  {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           int count = mapper.getCount();
           return count;
       }catch (PersistenceException e) {
           e.printStackTrace();
           return 0;
       }
    }

    @Override
    public boolean deleteUser(String name) {
        try(SqlSession sqlSession = MybatisUtil.getSession())  {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.deleteUser(name);
            return true;
        }catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

}
