package com.takeout.mapper;

import com.takeout.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from admin where name = #{username} and password = #{password}")
    Admin getAdmin(@Param("username") String username,@Param("password") String password);

    @Insert("insert into  admin(name,password) values (#{username},#{password})")
    void insertAdmin(@Param("username") String username, @Param("password") String password);
}
