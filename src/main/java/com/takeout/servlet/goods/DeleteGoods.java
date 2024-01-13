package com.takeout.servlet.goods;

import com.takeout.mapper.impl.GoodsDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteGoods extends HttpServlet {

    private GoodsDaoImpl goodsDao;

    @Override
    public void init() throws ServletException {
       goodsDao = new GoodsDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        System.out.println("id = "+idStr);
        try {
            if(goodsDao.deleteGoods(idStr)){
                resp.getWriter().write("ok");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.getWriter().write("删除失败");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
