package com.takeout.servlet.user;

import com.takeout.entity.Admin;
import com.takeout.mapper.impl.AdminDaoImpl;
import com.takeout.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class modifyAdminInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        if (StringUtil.hasEmpty(password,password2)) {
            req.setAttribute("errMsg","密码不能为空");
            req.getRequestDispatcher("modifyAdmin.jsp").forward(req,resp);
            return;
        }
        if(password.equals(password2)) {
            AdminDaoImpl dao = new AdminDaoImpl();
            try {
                Admin admin = (Admin) req.getSession().getAttribute("admin");
                dao.changePassword(admin,password);
                req.setAttribute("errMsg","修改密码成功,请重新登录");
                req.getSession().setAttribute("admin",null);
                req.getRequestDispatcher("admin.jsp").forward(req,resp);
            } catch (SQLException e) {
                req.setAttribute("errMsg", "修改密码失败");
                req.getRequestDispatcher("modifyAdmin.jsp").forward(req, resp);
                return;
            }
        } else {
            req.setAttribute("errMsg", "密码不一致");
            req.getRequestDispatcher("modifyAdmin.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
