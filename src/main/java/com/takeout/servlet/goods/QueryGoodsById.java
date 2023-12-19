package com.takeout.servlet.goods;

import com.takeout.entity.Goods;
import com.takeout.mapper.impl.GoodsDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过id获取菜品
 */
public class QueryGoodsById extends HttpServlet {
    private GoodsDaoImpl goodsDao;

    @Override
    public void init() throws ServletException {
        goodsDao = new GoodsDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        Goods goods = goodsDao.findGoodsById(id);
        req.setAttribute("goods",goods);
        req.getRequestDispatcher("updatemenu.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
