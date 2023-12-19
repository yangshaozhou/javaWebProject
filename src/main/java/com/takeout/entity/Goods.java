package com.takeout.entity;

import lombok.Data;

@Data
public class Goods {
    private int id;
    private String name;
    private double price;
    private String description;
    private String image;
    private int state;
}
