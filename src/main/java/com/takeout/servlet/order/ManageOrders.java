package com.takeout.servlet.order;

import com.takeout.mapper.impl.OrderDaoImpl;
import com.takeout.vo.Order;
import com.takeout.vo.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageOrders extends HttpServlet {
    private OrderDaoImpl orderDao;

    @Override
    public void init() throws ServletException {
        orderDao = new OrderDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("外卖");
        int pageNum = 1;
        int count = 1;
        String pageNumStr = req.getParameter("pageNum");

        if(pageNumStr != null) {
            try {
                pageNum = Integer.parseInt(pageNumStr);
                if (pageNum < 1) pageNum = 1;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        Page<Order> page = null;
        try {
            page = orderDao.queryOrdersByPage(pageNum,count);
        }catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("page",page);
        System.out.println(page.toString());
        req.getRequestDispatcher("order.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,resp);
    }
}
