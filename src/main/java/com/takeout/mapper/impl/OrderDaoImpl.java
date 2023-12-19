package com.takeout.mapper.impl;

import com.takeout.utils.JdbcUtil;
import com.takeout.vo.Order;
import com.takeout.vo.OrderItem;
import com.takeout.vo.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl {
    /**
     * 分页查询 #
     */
    public Page<Order> queryOrdersByPage(int pageNum,int count) throws SQLException {
        Page<Order> page = new Page<>();
        page.setCount(count);
        System.out.println("order"+getOrdersCount());
        int allPageNum = (int) Math.ceil(getOrdersCount()/(double)count);
        if(allPageNum == 0) allPageNum = 1;
        page.setAllPageNum(allPageNum);
        if(pageNum > page.getAllPageNum()) pageNum = page.getAllPageNum();
        if(pageNum <= 0) pageNum = 1;
        page.setCurrentPageNum(pageNum);
        int start = (pageNum -1) *count;
        String sql = "select * from orders limit ?,?";
        List<Order> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, count);

            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt(1));
                order.setTime(rs.getTimestamp(2));
                order.setPrice(rs.getFloat(3));
                order.setState(rs.getInt(5));
                order.setPhone(rs.getString(6));
                order.setAddress(rs.getString(7));
                order.setName(rs.getString(8));
                list.add(order);
            }
        } finally {
            JdbcUtil.free(rs,ps,conn);
        }
        page.setList(list);
        return page;
    }

    /**
     * 订单数量
     */
    public int getOrdersCount() throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select count(*) from orders";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }
        }finally {
            JdbcUtil.free(rs,ps,conn);
        }
        return count;
    }

    /**
     * 修改订单状态
     */
    public void changeOrderStatus(int orderId,int state) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "update orders set state=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, state);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }finally {
            JdbcUtil.free(rs,ps,conn);
        }
    }

    /**
     * 获取完整的订单信息
     */
    public Order getOrderById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from orders where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setTime(rs.getTimestamp(2));
                order.setPrice(rs.getDouble(3));
                order.setState(rs.getInt(5));
                order.setPhone(rs.getString(6));
                order.setAddress(rs.getString(7));
                order.setName(rs.getString(8));
                JdbcUtil.free(rs,ps,null);
                sql = "select * from order_datail where orderId=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,order.getId());
                rs = ps.executeQuery();
                List<OrderItem> list = order.getItems();
                GoodsDaoImpl goodsDao = new GoodsDaoImpl();
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setGoods(goodsDao.findGoodsById(rs.getInt(2)));
                    item.setCount(rs.getInt(3));
                    item.setPrice(item.calPrice());
                    list.add(item);
                }
                order.updatePrice();
                System.out.println(order.toString());
                return order;
            }
        }finally {
            JdbcUtil.free(rs,ps,conn);
        }
        return null;
    }
}
