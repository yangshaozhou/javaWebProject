package com.takeout.mapper.impl;

import com.takeout.entity.Goods;
import com.takeout.utils.JdbcUtil;
import com.takeout.vo.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl  {


    /**
     * 通过id查找对应的菜品信息 #
     */
    public Goods findGoodsById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Goods goods = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from goods where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()) {
                goods = new Goods();
                goods.setId(rs.getInt(1));
                goods.setName(rs.getString(2));
                goods.setPrice(rs.getDouble(3));
                goods.setDescription(rs.getString(4));
                goods.setImage(rs.getString(5));
                goods.setState(rs.getInt(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.free(rs,ps,conn);
        }
        return goods;
    }



    /**
     * 分页查询所有商品 #
     * @param pageNum
     * @param count
     * @return
     */
    public Page<Goods> queryGoodsByPage(String name,int pageNum, int count, int state) throws SQLException {
        Page<Goods> page = new Page<>();
        page.setCount(count);
        int allPageNum = (int) Math.ceil(getGoodsCount(state) / (float) count);
        System.out.println(getGoodsCount(state));
        if (allPageNum == 0) allPageNum = 1;
        page.setAllPageNum(allPageNum);
        if (pageNum > page.getAllPageNum()) pageNum = page.getAllPageNum();
        if (pageNum <= 0) pageNum = 1;
        page.setCurrentPageNum(pageNum);

        int start = (pageNum - 1) * count;
        String sql;
        if (state != 2) {
            sql = "select * from goods where state=? and name like ? limit ?,?";
        } else {
            sql = "select * from goods where name like ? limit ?,?";
        }


        List<Goods> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Goods goods;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            if (state != 2) {
                ps.setInt(1, state);
                ps.setInt(2, start);
                ps.setInt(3, count);
            } else {
                ps.setString(1,"%"+name+"%");
                ps.setInt(2, start);
                ps.setInt(3, count);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                goods = new Goods();
                goods.setId(rs.getInt(1));
                goods.setName(rs.getString(2));
                goods.setPrice(rs.getFloat(3));
                goods.setDescription(rs.getString(4));
                goods.setImage(rs.getString(5));
                goods.setState(rs.getInt(6));
                list.add(goods);
            }

        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        page.setList(list);
        return page;
    }


    /**
     * 添加菜品 #
     */
    public void addGoods(Goods goods) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = JdbcUtil.getConnection();
            String sql = "insert into goods(name,price,description,state) values (?,?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getName());
            ps.setDouble(2, goods.getPrice());
            ps.setString(3, goods.getDescription());
            ps.setInt(4, goods.getState());
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null,ps,conn);
        }
    }

//    得到所有菜品数量  #
    public int getGoodsCount(int state) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtil.getConnection();
            String sql;
            if (state != 2) {
                sql = "select count(*) from goods where state=?";
            } else {
                sql = "select count(*) from goods";
            }

            ps = conn.prepareStatement(sql);
            if (state != 2) {
                ps.setInt(1, state);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return count;
    }

//    更新菜品
    public void update(Goods goods) throws SQLException {
        Connection conn = null;
        PreparedStatement ps =  null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "update goods set name=?,price=?,description=?,state=? where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getName());
            ps.setDouble(2, goods.getPrice());
            ps.setString(3, goods.getDescription());
            ps.setInt(4, goods.getState());
            ps.setInt(5, goods.getId());
            ps.executeUpdate();
        }finally {
            JdbcUtil.free(null,ps,conn);
        }
    }
}
