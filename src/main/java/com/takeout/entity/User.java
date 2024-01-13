package com.takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String name;
    private String password;
    private String address;
    private String phoneNumber;
    private String head_Photo;
}
