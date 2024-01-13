package com.takeout.servlet.goods;

import com.takeout.entity.Goods;
import com.takeout.mapper.impl.GoodsDaoImpl;
import com.takeout.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,   // 2MB
        maxFileSize = 1024 * 1024 * 10,        // 10MB
        maxRequestSize = 1024 * 1024 * 50)     // 50MB
public class UpdateGoods extends HttpServlet {

    private GoodsDaoImpl goodsDao;

    @Override
    public void init() throws ServletException {
        goodsDao = new GoodsDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");

        Part imgPath = req.getPart("image");
        String fileName = Paths.get(imgPath.getSubmittedFileName()).getFileName().toString();
        System.out.println("fileName"+fileName);

        req.setAttribute("name",name);
        req.setAttribute("price",priceStr);
        req.setAttribute("description",description);

//        System.out.println("name: " + name + " price: " + priceStr + " description: " + description );
        String uploadPath = "D:\\javabian\\javaWebProject\\src\\main\\webapp\\upload";

        String imageUrl = null;
        if(imgPath != null) {
            String imagePath = uploadPath + File.separator + fileName;
            try (InputStream input = imgPath.getInputStream();
                 OutputStream output = new FileOutputStream(imagePath)) {
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while((bytesRead = input.read(buffer)) != -1 ) {
                    output.write(buffer,0,bytesRead);
                }
                imageUrl = fileName;
            }
        }

        double price;
        req.setAttribute("isOk",false);
        if(StringUtil.isEmpty(name)) {
            resp.getWriter().write("名字不能为空");
            return;
        }

        try {
            price = Double.parseDouble(priceStr);

        } catch (Exception e) {
            resp.getWriter().write("请输入正确的价格");
            return;
        }

        if(StringUtil.isEmpty(description)) {
            resp.getWriter().write("描述不能为空");
            return;
        }
        Goods goods = new Goods();
        goods.setState(1);
        goods.setDescription(description);
        goods.setPrice(price);
        goods.setName(name);
        goods.setImage(imageUrl);
        try {
            goodsDao.addGoods(goods);
        }catch (Exception e) {
            resp.getWriter().write("添加失败" + e);
            e.printStackTrace();
            return;
        }
        resp.getWriter().write("ok");



    }
}
