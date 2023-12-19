package com.takeout.servlet.user;

import com.takeout.service.UserService;
import com.takeout.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException {
       userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("登录");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(userService.register(username,password)){
            req.getRequestDispatcher("admin.jsp").forward(req,resp);
        }
        else {
            System.out.println("注册失败");
        }
        System.out.println("注册");

    }
}
