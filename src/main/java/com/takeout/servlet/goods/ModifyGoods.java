package com.takeout.servlet.goods;

import com.takeout.entity.Goods;
import com.takeout.mapper.impl.GoodsDaoImpl;
import com.takeout.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

// 修改菜品
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,   // 2MB
        maxFileSize = 1024 * 1024 * 10,        // 10MB
        maxRequestSize = 1024 * 1024 * 50)     // 50MB
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
            Part imgPath = req.getPart("image");
            String fileName = Paths.get(imgPath.getSubmittedFileName()).getFileName().toString();
        System.out.println("fileName"+fileName);

            req.setAttribute("id",idStr);
            req.setAttribute("name",name);
            req.setAttribute("price",pricesStr);
            req.setAttribute("description",description);

            String uploadPath = "D:\\javabian\\javaWebProject\\src\\main\\webapp\\upload";



            if(StringUtil.isEmpty(name)) {
                resp.getWriter().write("名字不能为空");
                return;
            }

            if(StringUtil.isEmpty(description)) {
                resp.getWriter().write("描述不能为空");
                return;
            }

            String imageUrl = null;
            if(imgPath != null) {
                String imagePath = uploadPath + File.separator +fileName;
                try (InputStream input = imgPath.getInputStream();
                     OutputStream output = new FileOutputStream(imagePath)) {

                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    imageUrl = fileName;
                }
            }

            int id;
            double price;

            try {
                id = Integer.parseInt(idStr);
//                找到对应的商品
                Goods goods = goodsDao.findGoodsById(id) ;
                System.out.println(imageUrl);
                if(goods !=null) {
                    try {
                        price = Double.parseDouble(pricesStr);
                        goods.setName(name);
                        goods.setPrice(price);
                        goods.setDescription(description);
                        goods.setImage(imageUrl);
                        System.out.println(goods);
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
