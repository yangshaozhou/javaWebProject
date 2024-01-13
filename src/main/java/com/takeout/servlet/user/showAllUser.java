package com.takeout.servlet.user;

import com.takeout.entity.User;
import com.takeout.service.UserService;
import com.takeout.service.impl.UserServiceImpl;
import com.takeout.vo.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 展示所有用户
 */
public class showAllUser extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
       userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int page = Integer.parseInt(req.getParameter("pageNum")) ;
      int num =3;

      int offset = (page - 1) * num;

        List<User> list = userService.searchAllUser(offset,num);
        System.out.println(list);

        int totalCount = userService.getCountUser();

        int totalPages = (int) Math.ceil((double) totalCount / num);
        Page<User> pageList = new Page<>();
        pageList.setAllPageNum(totalPages);
        pageList.setCurrentPageNum(page);
        pageList.setCount(totalCount);
        pageList.setList(list);
        req.setAttribute("page",pageList);
        System.out.println(pageList);
        req.getRequestDispatcher("user.jsp").forward(req,resp);
    }
}
