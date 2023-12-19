package com.takeout.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private List<OrderItem> items = new ArrayList<>();
    private int id;
    /**
     * 0 未付款
     * 1 待配送
     * 2 正在配送
     * 3 以前搜
     */
    private int state;
   private double price;
   private Timestamp time;
   private String name;
   private String address;
   private String phone;

   public void updatePrice() {
       double price = 0;
       for (OrderItem item : items) {
           price += item.getPrice();
       }
       this.price = price;
   }
}
