package com.takeout.servlet.goods;

import com.takeout.entity.Goods;
import com.takeout.mapper.impl.GoodsDaoImpl;
import com.takeout.vo.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 展示所有商品
 */
public class ShowAllGoods extends HttpServlet {
    private GoodsDaoImpl goodsDao;

    @Override
    public void init() throws ServletException {
        goodsDao= new GoodsDaoImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNum = 1;
        String name = "";

        String pageNumStr = req.getParameter("pageNum");
        String searchName = req.getParameter("search");

        System.out.println("searchName1" + searchName);

        if(pageNumStr != null) {
            try {
                pageNum = Integer.parseInt(pageNumStr);
                if (pageNum < 1) pageNum = 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(searchName != null) {
            name =  searchName;
            pageNum = 1;
        }

        try {
            Page<Goods> page = goodsDao.queryGoodsByPage(name,pageNum,2, 2);
            req.setAttribute("page",page);
            System.out.println(page);
            req.getRequestDispatcher("menu.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
