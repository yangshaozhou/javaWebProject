package com.takeout.servlet.order;

import com.takeout.mapper.impl.OrderDaoImpl;
import com.takeout.vo.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class OrderDetail extends HttpServlet {
    private OrderDaoImpl orderDao;

    @Override
    public void init() throws ServletException {
       orderDao = new OrderDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderIdStr = req.getParameter("orderId");
        int orderId;
        try {
            orderId = Integer.parseInt(orderIdStr);
            Order order = orderDao.getOrderById(orderId);
            req.setAttribute("order",order);
            System.out.println(order.toString());
            req.getRequestDispatcher("orderDetailAdmin.jsp").forward(req,resp);
        }catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMsg","获取参数错误");
            req.getRequestDispatcher("orderDetailAdmin.jsp").forward(req, resp);
        }
    }
}
