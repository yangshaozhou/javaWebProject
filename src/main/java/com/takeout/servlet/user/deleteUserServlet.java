package com.takeout.servlet.user;

import com.takeout.service.UserService;
import com.takeout.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class deleteUserServlet  extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
       userService =new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("id");

        System.out.println("id =" + name);
        if(userService.deleteUser(name)) {
            resp.getWriter().write("ok");
            return ;
        }
        resp.getWriter().write("删除失败");
    }
}
