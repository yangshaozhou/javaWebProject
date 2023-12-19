package com.takeout.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page <T>{
    private int allPageNum;
    private int currentPageNum;
    private int count;
    private List<T> list;
}
