package com.takeout.servlet.order;

import com.takeout.mapper.impl.OrderDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeOrderState extends HttpServlet {
    private OrderDaoImpl orderDao;

    @Override
    public void init() throws ServletException {
        orderDao = new OrderDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String orderStr = req.getParameter("orderId");
       String stateStr = req.getParameter("state");

       int orderId,state;

       try {
           orderId = Integer.parseInt(orderStr);
           state = Integer.parseInt(stateStr);

            orderDao.changeOrderStatus(orderId,state);
            resp.getWriter().write("ok");
       }catch (Exception e) {
           e.printStackTrace();
           resp.getWriter().write("err");
       }
    }
}
