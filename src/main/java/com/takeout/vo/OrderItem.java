package com.takeout.vo;

import com.takeout.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Goods goods;
    private int count = 1;
    private double price;

   public double calPrice() {
        return this.count * this.goods.getPrice();
    }
}
