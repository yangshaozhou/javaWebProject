package com.takeout.mapper;

import com.takeout.entity.Admin;
import com.takeout.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from admin where name = #{username} and password = #{newPassword}")
    Admin getAdmin(@Param("username") String username,@Param("newPassword") String newPassword);

    @Insert("insert into admin(name, password) values (#{username}, #{newPassword})")
    void insertAdmin(@Param("username") String username, @Param("newPassword") String newPassword);

    @Select("select * from user limit #{page},#{num}")
    List<User> findUser(@Param("page") int page,@Param("num") int num);

    @Select("select count(*) from user")
    int getCount();

   @Delete("delete from user where name like #{name}")
    void deleteUser(String name);
}
