package com.takeout.servlet.goods;

import com.takeout.entity.Goods;
import com.takeout.mapper.impl.GoodsDaoImpl;
import com.takeout.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyGoods extends HttpServlet {
    private GoodsDaoImpl goodsDao;

    @Override
    public void init() throws ServletException {
        goodsDao = new GoodsDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             req.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name") ;
            String pricesStr = req.getParameter("price");
            String description = req.getParameter("description");
            String idStr = req.getParameter("id");

            req.setAttribute("id",idStr);
            req.setAttribute("name",name);
            req.setAttribute("price",pricesStr);
            req.setAttribute("description",description);

            if(StringUtil.isEmpty(name)) {
                resp.getWriter().write("名字不能为空");
                return;
            }

            if(StringUtil.isEmpty(description)) {
                resp.getWriter().write("描述不能为空");
                return;
            }

            int id;
            double price;

            try {
                id = Integer.parseInt(idStr);
                Goods goods = goodsDao.findGoodsById(id) ;
                if(goods !=null) {
                    try {
                        price = Double.parseDouble(pricesStr);
                        goods.setName(name);
                        goods.setPrice(price);
                        goods.setDescription(description);
                        goodsDao.update(goods);
                        resp.getWriter().write("ok");
                        return;
                    }catch (Exception e) {
                        resp.getWriter().write("请输入正确的价格");
                        return;
                    }
                }else  {
                    resp.getWriter().write("修改信息失败");
                    return;
                }
            } catch (Exception e) {
                resp.getWriter().write("修改信息失败");
                return;
            }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
